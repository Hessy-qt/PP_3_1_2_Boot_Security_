package ru.kata.spring.boot_security.demo.beans.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.beans.models.User;

@Repository
public interface UsersRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}