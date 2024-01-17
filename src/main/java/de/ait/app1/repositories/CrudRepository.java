package de.ait.app1.repositories;

import java.util.List;

public interface CrudRepository <T> {
    void save(T model); // сохраняем, создаем объект
    void deleteById(Long id); // удаляем
   // void updateById(Long id); // обновляем

    void update(T model);
    T findById(Long id); // читаем 1 конкретный обхект
    List<T> findAll(); // читаем все объекты
}
