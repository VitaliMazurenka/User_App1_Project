package de.ait.app1.repositories;

import de.ait.app1.models.User;

public interface UserRepository extends CrudRepository<User>{
    User findByEmail(String email); // ищем пользователя по email

}
