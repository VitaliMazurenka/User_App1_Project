package de.ait.app1.repositories.services.impl;

import de.ait.app1.models.User;
import de.ait.app1.repositories.UserRepository;
import de.ait.app1.services.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("UserServiceImpl is working...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class UserServiceImplTest {
    // тестируем в сервисе бизнес-логику
    // выдача сообщений на пустые email и password

    // создадим необходимые константы
    private static final String EXISTED_USER_EMAIL = "leon@mail.com";
    private static final String NOT_EXISTED_USER_EMAIL = "leonid@mail.com";
    private static final String DEFAULT_PASSWORD = "qwerty001";
    private static final User EXISTED_USER = new User(EXISTED_USER_EMAIL, DEFAULT_PASSWORD);
    private static final User NOT_EXISTED_USER = new User(NOT_EXISTED_USER_EMAIL, DEFAULT_PASSWORD);

    private UserServiceImpl userService; // DI step 1
    private UserRepository userRepository; // DI step 1

    @BeforeEach
    public void setUp(){
        // userService = new UserServiceImpl(null);

        // заглушка для репозитория
        userRepository = Mockito.mock(UserRepository.class);

        // прописываем явно поведение методов из сервиса
        when(userRepository.findByEmail(EXISTED_USER_EMAIL)).thenReturn(EXISTED_USER);
        when(userRepository.findByEmail(NOT_EXISTED_USER_EMAIL)).thenReturn(null);

        this.userService = new UserServiceImpl(userRepository);

    }
    // два теста ниже проверят правильное поведение при пустых email и password
    @Test
    public void addUser_on_incorrect_email_throws_exception(){
        assertThrows(IllegalArgumentException.class, () -> userService.addUser(null, "123"));
    }

    @Test
    public void addUser_on_incorrect_password_throws_exception(){
        assertThrows(IllegalArgumentException.class, () -> userService.addUser(EXISTED_USER_EMAIL, null));
    }

    // проверим логику на уже существующего пользователя и создаваемого
    @Test
    public void on_existed_user_throw_exception (){
        assertThrows(IllegalArgumentException.class, () -> userService.addUser(EXISTED_USER_EMAIL, DEFAULT_PASSWORD));
    }

    @Test
    public void return_created_user (){
        User actual = userService.addUser(NOT_EXISTED_USER_EMAIL, DEFAULT_PASSWORD);
        verify(userRepository).save(NOT_EXISTED_USER); // просим репозиторий сохранить нового пользователя

        assertNotNull(actual); // addUser вернет нам не null, а это значит, что метод отработал
    }

}