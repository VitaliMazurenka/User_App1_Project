package de.ait.app1.repositories.impl;

import de.ait.app1.models.User;
import de.ait.app1.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryListImpl implements UserRepository {

    private final List<User> users = new ArrayList<>();
    private Long generatedId = 1L;


    @Override
    public void save(User user) {
        users.add(user);
        user.setId(generatedId);
        generatedId++;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User findByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
