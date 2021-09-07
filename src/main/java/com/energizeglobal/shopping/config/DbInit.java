package com.energizeglobal.shopping.config;

import com.energizeglobal.shopping.model.entity.CategoryEntity;
import com.energizeglobal.shopping.model.entity.RoleEntity;
import com.energizeglobal.shopping.model.entity.UserEntity;
import com.energizeglobal.shopping.repository.CategoryRepository;
import com.energizeglobal.shopping.repository.RoleRepository;
import com.energizeglobal.shopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbInit {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Value("${role.admin}")
    private String roleAdmin;
    @Value("${role.user}")
    private String roleUser;
    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;


    @Autowired
    public DbInit(CategoryRepository categoryRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    private void populateCategory() {
        CategoryEntity categoryA = new CategoryEntity();
        categoryA.setName("Category A");
        categoryRepository.save(categoryA);

        CategoryEntity categoryB = new CategoryEntity();
        categoryB.setName("Category B");
        categoryRepository.save(categoryB);

        CategoryEntity categoryC = new CategoryEntity();
        categoryC.setName("Category C");
        categoryRepository.save(categoryC);

        CategoryEntity categoryD = new CategoryEntity();
        categoryD.setName("Category D");
        categoryRepository.save(categoryD);


    }

    @PostConstruct
    private void populateUserRole() {
        RoleEntity adminRole = new RoleEntity();
        adminRole.setName(roleAdmin);
        roleRepository.save(adminRole);
        RoleEntity userRole = new RoleEntity();
        userRole.setName(roleUser);
        roleRepository.save(userRole);

        UserEntity adminUser = new UserEntity();
        adminUser.setUserName(adminUsername);
        adminUser.setPassword(adminPassword);
        adminUser.setEnabled(Boolean.TRUE);
        adminUser = userRepository.save(adminUser);

        adminUser.getRoles().add(adminRole);
        userRepository.save(adminUser);
    }

}

