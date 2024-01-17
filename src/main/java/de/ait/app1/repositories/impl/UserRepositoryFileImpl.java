package de.ait.app1.repositories.impl;

import de.ait.app1.models.User;
import de.ait.app1.repositories.UserRepository;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryFileImpl implements UserRepository {
    // fields
    private final String fileName; // DI
    private Long generatedId = 1L;

    public UserRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            user.setId(findAll().size()+1L); // получаем размер списка пользователей и вычисляем id
            writer.write(user.getId() + "," + user.getEmail() + "," + user.getPassword());
            writer.newLine();
            writer.flush(); // ?? - может быть тоже не нужен
            // writer.close(); - не нужен, так как используем try catch with resources

        } catch (IOException e) {
            throw new IllegalStateException("Problem with file." + e.getMessage());
        }
        generatedId++;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(User user) {
        // на вход получили пользователя, нужно его данные перезаписать в файле
        // нюанс! - нельзя переписать одну строку в файле, нужно переписывать файл полностью.
        List<User> users = findAll(); // получили всех пользователей из файла

        // находим обновляемого пользователя по его id
        User userForUpdate = users.stream()
                .filter(u -> u.getId() == user.getId())
                .findFirst()
                .orElse(null);
        // удаляем пользователя из списка по его id
        users.remove(userForUpdate);
        // добавляем пользователя в список
        users.add(user);
        // делаем цикл, который записывает всех пользователей из списка в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // начало цикла
            for (User u : users) {
                writer.write(u.getId() + "," + u.getEmail() + "," + u.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Problem with file: " + e.getMessage());
        }
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){

            return reader.lines()
                    .map(line -> line.split(","))
                    .map(parsed -> new User(Long.parseLong(parsed[0]), parsed[1], parsed[2]))
                    .collect(Collectors.toList());

        }catch (IOException e){
            throw new IllegalStateException("Problem with file.");
        }
    }

    @Override
    public User findByEmail(String email) {
        return findAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}