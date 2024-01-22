package de.ait.app1;

import de.ait.app1.config.AppConfig;
import de.ait.app1.controllers.UserController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;
import java.util.Scanner;

public class Main1 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Scanner scanner = applicationContext.getBean(Scanner.class); // получили объект из контекста НАШЕГО приложения
        UserController userController = applicationContext.getBean(UserController.class); // получили весь контроллер
        // вместе с UserService, a затем и с Repo и валидаторами

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

