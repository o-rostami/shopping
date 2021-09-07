package com.energizeglobal.shopping.service.category;

import com.energizeglobal.shopping.exception.NotFoundException;
import com.energizeglobal.shopping.model.entity.CategoryEntity;
import com.energizeglobal.shopping.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public CategoryEntity getCategoryById(Long playerId) {
        return repository
                .findById(playerId)
                .orElseThrow(() -> new NotFoundException("CATEGORY.NOT.EXIST"));
    }

}
