package de.ait.app1.config;

import de.ait.app1.controllers.UserController;
import de.ait.app1.repositories.UserRepository;
import de.ait.app1.repositories.impl.UserRepositoryFileImpl;
import de.ait.app1.repositories.impl.UserRepositoryListImpl;
import de.ait.app1.services.UserService;
import de.ait.app1.services.impl.UserServiceImpl;
import de.ait.app1.validation.EmailValidator;
import de.ait.app1.validation.PasswordValidator;
import de.ait.app1.validation.impl.EmailNotEmptyValidatorImpl;
import de.ait.app1.validation.impl.EmailRegexValidatorImpl;
import de.ait.app1.validation.impl.PasswordNotEmptyValidatorImpl;
import de.ait.app1.validation.impl.PasswordRegexValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

// аннотация говорит Spring что в этом классе лежит конфигурация приложения
@Configuration
public class AppConfig {

    private static final String FILE_NAME = "C:\\Users\\AIT TR Student\\Desktop\\App1-1\\src\\main\\java\\de\\ait\\app1\\users.txt";
    // пишем метод который создает объект на базе интерфейса PasswordValidator
    // который обращается к конструктору класса PasswordNotEmptyValidatorImpl
    // который имплементирует  этот интерфейс и написали над ним аннотацию @Bean

    @Bean
    public PasswordValidator passwordValidatorNotEmpty() {
        return new PasswordNotEmptyValidatorImpl();
    }

    @Bean
    public PasswordValidator passwordValidatorRegex() {
        return new PasswordRegexValidatorImpl();
    }

    @Bean
    public EmailValidator emailValidatorNotEmpty(){
        return new EmailNotEmptyValidatorImpl();
    }
    @Bean
    public EmailValidator emailValidatorRegex() {
        return new EmailRegexValidatorImpl();
    }
    @Bean
    public UserRepository userRepositoryList(){
        return new UserRepositoryListImpl();
    }
    @Bean
    public UserRepository userRepositoryFile(){
        return new UserRepositoryFileImpl(FILE_NAME);
    }
    @Bean
    public UserService userService(UserRepository userRepositoryFile, EmailValidator emailValidatorNotEmpty, PasswordValidator passwordValidatorNotEmpty){
        return new UserServiceImpl(userRepositoryFile,emailValidatorNotEmpty, passwordValidatorNotEmpty);
    }
    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }
    @Bean
    public UserController userController(Scanner scanner, UserService userService){
        return new UserController(scanner, userService);
    }
}
