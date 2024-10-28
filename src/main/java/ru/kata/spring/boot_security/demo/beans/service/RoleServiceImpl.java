package ru.kata.spring.boot_security.demo.beans.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.beans.models.Role;
import ru.kata.spring.boot_security.demo.beans.repositories.RolesRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RolesRepository rolesRepository;

    @Autowired
    public RoleServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public Role findById(int id) {
        return rolesRepository.findById(id).get();
    }

    @Override
    public List<Role> findAll() {
        return rolesRepository.findAll();
    }
}
