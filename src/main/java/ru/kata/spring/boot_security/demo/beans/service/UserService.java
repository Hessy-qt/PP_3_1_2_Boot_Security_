package ru.kata.spring.boot_security.demo.beans.service;


import ru.kata.spring.boot_security.demo.beans.models.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void deleteUser(int id);
    List<User> getAllUsers();
    void updateUser(int id,User user);
    User findByEmail(String email);
    User findById(int id);
}
