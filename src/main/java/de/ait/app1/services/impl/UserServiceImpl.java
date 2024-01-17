package de.ait.app1.services.impl;

import de.ait.app1.models.User;
import de.ait.app1.repositories.UserRepository;
import de.ait.app1.services.UserService;
import de.ait.app1.validation.EmailValidator;
import de.ait.app1.validation.PasswordValidator;

import java.util.List;


public class UserServiceImpl implements UserService {

    // dependency injection
    // бизнес логика
    @Override
    public User addUser(String email, String password) {
        // валидация email
        //if (email == null || email == "" || email.equals(" ")) {
        //    throw new IllegalArgumentException("Email can't be empty");
       // }

        emailValidator.validate(email);

        // валидация password
//        if (password == null || password == "" || password.equals(" ")) {
//            throw new IllegalArgumentException("Password can't be empty");
//        }

        passwordValidator.validate(password);
        // проверить что такого пользователя еще нет
        User existedUser = userRepository.findByEmail(email);
        if (existedUser != null) {
            throw new IllegalArgumentException("User is already exists.");
        }
        User user = new User(email, password);
        userRepository.save(user);
        return user;
    }

    public final UserRepository userRepository; // зависимость на UserRepository
    // добавляем зависимость на валидаторы. Сюда, так как тут часть бизнес логики.

    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;

    public UserServiceImpl(UserRepository userRepository, EmailValidator emailValidator, PasswordValidator passwordValidator) {
        this.userRepository = userRepository;
        this.emailValidator = emailValidator;
        this.passwordValidator = passwordValidator;
    }

    // 2 ой  метод
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 3ий  метод

    public void updateUser(Long id, String newEmail, String newPassword) {
        // берем весь список
        List<User> users = userRepository.findAll();
        // находим пользователя по его id
        User userForUpdate = users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
        if (userForUpdate == null) {
            throw new IllegalArgumentException("User with id " + id + " is not found.");
        }
        if(!newEmail.isBlank()){   // если не пустое
            userForUpdate.setEmail(newEmail);
        }
        if(!newPassword.isBlank()){
            userForUpdate.setPassword(newPassword);
        }
        userRepository.update(userForUpdate);
    }

    }
