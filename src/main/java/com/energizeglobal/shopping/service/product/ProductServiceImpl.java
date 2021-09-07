package com.energizeglobal.shopping.service.product;

import com.energizeglobal.shopping.exception.BusinessException;
import com.energizeglobal.shopping.exception.NotFoundException;
import com.energizeglobal.shopping.model.dto.PagingRequest;
import com.energizeglobal.shopping.model.dto.PagingResponse;
import com.energizeglobal.shopping.model.entity.CategoryEntity;
import com.energizeglobal.shopping.model.entity.ProductEntity;
import com.energizeglobal.shopping.repository.ProductRepository;
import com.energizeglobal.shopping.service.category.CategoryService;
import com.energizeglobal.shopping.specification.PaginationExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;


    @Autowired
    public ProductServiceImpl(ProductRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    @Override
    public Long createProduct(ProductEntity productEntity) {
        CategoryEntity categoryEntity = categoryService.getCategoryById(productEntity.getCategory().getId());
        if (Objects.nonNull(repository.findByNameAndCategory(productEntity.getName(), categoryEntity))) {
            throw new BusinessException("PRODUCT.NAME.IS.DUPLICATE", "productName");
        }
        return repository.save(productEntity).getId();
    }

    @Override
    public ProductEntity updateProduct(ProductEntity productEntity) {
        ProductEntity dbProduct = getProductById(productEntity.getId());
        if (!productEntity.getVersion().equals(dbProduct.getVersion())) {
            throw new BusinessException("VERSION.IS.NOT.VALID", "version");
        }
        dbProduct.setName(productEntity.getName());
        repository.save(dbProduct);
        return dbProduct;
    }

    @Override
    public ProductEntity getProductById(Long playerId) {
        return repository
                .findById(playerId)
                .orElseThrow(() -> new NotFoundException("PRODUCT.NOT.EXIST"));
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public void deleteProductById(Long playerId) {
        ProductEntity entity = getProductById(playerId);
        repository.delete(entity);
    }

    @Override
    public PagingResponse<ProductEntity> searchProduct(PagingRequest pagingRequest) {
        PaginationExecutor<ProductEntity> paginationExecutor = new PaginationExecutor<>(pagingRequest, repository);
        return paginationExecutor.execute();
    }

    @Override
    public ProductEntity rateProduct(ProductEntity entity) {
        ProductEntity dbProduct = getProductById(entity.getId());
        dbProduct.setRate(entity.getRate());
        return repository.save(dbProduct);
    }
}
