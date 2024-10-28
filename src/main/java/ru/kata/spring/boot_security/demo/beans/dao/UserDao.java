package ru.kata.spring.boot_security.demo.beans.dao;


import ru.kata.spring.boot_security.demo.beans.models.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void deleteUser(int id);
    List<User> getAllUsers();
    void updateUser(int id,User user);
}
