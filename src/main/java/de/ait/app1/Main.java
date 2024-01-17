package de.ait.app1;

import de.ait.app1.controllers.UserController;
import de.ait.app1.repositories.UserRepository;
import de.ait.app1.repositories.impl.UserRepositoryFileImpl;
import de.ait.app1.repositories.impl.UserRepositoryListImpl;
import de.ait.app1.services.impl.UserServiceImpl;
import de.ait.app1.validation.EmailValidator;
import de.ait.app1.validation.PasswordValidator;
import de.ait.app1.validation.impl.EmailNotEmptyValidatorImpl;
import de.ait.app1.validation.impl.PasswordNotEmptyValidatorImpl;

import java.util.Scanner;

public class Main {

    private static final String FILE_NAME = "C:\\Users\\AIT TR Student\\Desktop\\App1-1\\src\\main\\java\\de\\ait\\app1\\users.txt";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // DI
        // внедряем зависимости
        EmailValidator emailValidator = new EmailNotEmptyValidatorImpl();
        PasswordValidator passwordValidator = new PasswordNotEmptyValidatorImpl();
        // UserRepository userRepository = new UserRepositoryListImpl(); // DI на список пользователей
        UserRepository userRepository = new UserRepositoryFileImpl(FILE_NAME); // DI на репозиторий на файле
        UserServiceImpl userService = new UserServiceImpl(userRepository, emailValidator, passwordValidator); // зависимость на сервис
        UserController userController = new UserController(scanner, userService); // зависимость на контроллер

        // userController.addUser();

        boolean isRun = true;

        while (isRun) {
            System.out.println("Input command (address): ");
            String command = scanner.nextLine();

            switch (command) {
                case "/addUser" -> userController.addUser(); // вызов сервиса
                case "/users" -> userController.getAllUsers(); // вызов сервиса
                case "/updateUser" -> userController.updateUser(); // вызов сервиса
                case "/exit" -> isRun = false;
            }
        }
    }
}
