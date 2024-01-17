package de.ait.app1.controllers;

import de.ait.app1.models.User;
import de.ait.app1.services.impl.UserServiceImpl;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Scanner;

public class UserController {

    // внедрение зависимостей
    private final Scanner scanner;

    private final UserServiceImpl userService;

    public UserController(Scanner scanner, UserServiceImpl userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    public void addUser(){
        // считываем с клавиатуры
        System.out.println("Input e-mail: ");
        String email = scanner.nextLine();

        System.out.println("Input password: ");
        String password = scanner.nextLine();

        User user = userService.addUser(email, password); // вызов сервиса
        System.out.println(user);
    }

    public void getAllUsers(){
        List<User> users = userService.getAllUsers(); // вызов сервиса
        System.out.println(users);
    }

    public void updateUser() {
        System.out.println("Input ID: ");
        Long idForUpdate = scanner.nextLong();
        // считаем пустую строку
        scanner.nextLine();

        System.out.println("Input email: ");
        String newEmail = scanner.nextLine();

        System.out.println("Input password: ");
        String newPassword = scanner.nextLine();

        userService.updateUser(idForUpdate, newEmail, newPassword);

    }
}
