package com.energizeglobal.shopping.service.role;

import com.energizeglobal.shopping.model.entity.RoleEntity;
import com.energizeglobal.shopping.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;


    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoleEntity getRoleByName(String roleName) {
        return repository.findByName(roleName);
    }

}
