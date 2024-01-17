package de.ait.app1.repositories.impl;

import de.ait.app1.models.User;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserRepoFileImpl is working...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class UserRepositoryFileImplTest {
    // константа - имя файла
    private static final String TEMP_USERS_FILE_NAME = "users_test.txt";

    private UserRepositoryFileImpl userRepository; // DI step 1 (объявили, но не создали)

    @BeforeEach
    public void setup() throws IOException {
        createNewUserFileForTest(TEMP_USERS_FILE_NAME); // файл создаем
        userRepository = new UserRepositoryFileImpl(TEMP_USERS_FILE_NAME); // DI step 2
    }

    @AfterEach
    public void tearDown(){
        deleteFileAfterTest(TEMP_USERS_FILE_NAME);
    }

    @DisplayName("save user")
    @Nested
    class saveTest {
        @Test
        public void write_correct_line_to_file() throws IOException {
            //создаем пользователя
            User user = new User("leon@mail.com", "leon123");
            String expectedStr = "1,leon@mail.com,leon123";
            // пишем его в файл c помощью репозитория
            userRepository.save(user);
            // прочитаем его из файла
            BufferedReader reader = new BufferedReader(new FileReader(TEMP_USERS_FILE_NAME));
            String actualStr = reader.readLine();
            // проверим
            assertEquals(expectedStr, actualStr);
        }

    }

    @DisplayName("find all users")
    @Nested
    class findAllTest{
        @Test
        public void returns_correct_list_of_users() throws IOException {
            // пишем в файл 2 пользователя
            BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_USERS_FILE_NAME));
            writer.write("1,leon@mail.com,leon123");
            writer.newLine();
            writer.write("2,boris@mail.com,boris123");
            writer.newLine();
            writer.close();
            // ожидаемый список
            List<User> expected = Arrays.asList(
                    new User(1L, "leon@mail.com", "leon123"),
                    new User(2L, "boris@mail.com", "boris123")
            );
            // актуальный получаем из файла
            List<User> actual = userRepository.findAll();
            //сравнить
            assertEquals(expected, actual);
        }

    }

    // приватные методы для тестов
        private void deleteFileAfterTest(String fileName) {
            File file = new File(fileName);
            deleteIfExists(file);
        }

    private void createNewUserFileForTest(String fileName) throws IOException {
        File file = new File(fileName);

        deleteIfExists(file); // удалили, если уже есть такой

        boolean result = file.createNewFile(); // создаем новый файл
        if(!result) {
            throw new IllegalStateException("Problem with file. Can't create.");
        }
    }

    private void deleteIfExists(File file) {
        if(file.exists()){
            boolean result = file.delete();
            if(!result){
                throw new IllegalStateException("Problem with file. Can't delete.");
            }
        }
    }


}