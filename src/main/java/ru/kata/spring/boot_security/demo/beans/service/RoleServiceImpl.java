package ru.kata.spring.boot_security.demo.beans.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.beans.models.Role;
import ru.kata.spring.boot_security.demo.beans.repositories.RolesRepository;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RolesRepository rolesRepository;

    @Autowired
    public RoleServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public List<Role> findAll() {
        return rolesRepository.findAll();
    }
}
