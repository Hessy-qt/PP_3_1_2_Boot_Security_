package ru.kata.spring.boot_security.demo.beans.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.beans.models.Role;
import ru.kata.spring.boot_security.demo.beans.models.User;
import ru.kata.spring.boot_security.demo.beans.repositories.RolesRepository;
import ru.kata.spring.boot_security.demo.beans.repositories.UsersRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UsersRepository usersRepository;
    private final RolesRepository roleRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, RolesRepository roleRepository) {
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void addUser(User user) {
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(roleRepository.findById(role.getId()).orElseThrow());
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(roles);
        usersRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        usersRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }


    @Override
    public void updateUser(int id, User user,Set<Role> roles) {
        user.getRoles().clear();
        user.getRoles().addAll(roles);
        User userToUpdate = usersRepository.findById(id).get();
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setRoles(user.getRoles());
        usersRepository.save(userToUpdate);
    }

    @Transactional(readOnly = true)
    @Override
    public User findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(int id) {
        return usersRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        return user;
    }
}
