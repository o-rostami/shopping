package com.energizeglobal.shopping.service.rate;

import com.energizeglobal.shopping.exception.BadRequestException;
import com.energizeglobal.shopping.exception.BusinessException;
import com.energizeglobal.shopping.model.entity.ProductEntity;
import com.energizeglobal.shopping.model.entity.RateEntity;
import com.energizeglobal.shopping.repository.RateRepository;
import com.energizeglobal.shopping.service.product.ProductService;
import com.energizeglobal.shopping.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository repository;
    @Autowired
    private ProductService productService;


    @Override
    @Transactional
    public Long createRate(RateEntity rateEntity) {

        if (rateEntity.getId() != null) {
            throw new BadRequestException("The ID must not be provided when creating", "Id");
        }
        if (rateEntity.getRate() > 5 || rateEntity.getRate() < 1) {
            throw new BusinessException("INVALID.RATE");
        }

        ProductEntity productEntity = productService.getProductById(rateEntity.getProduct().getId());
        if (Objects.nonNull(repository.findByCreatedByAndProductId(SecurityUtils.getCurrentUser(), productEntity.getId()))) {
            throw new BusinessException("RATE.IS.DUPLICATE");
        }
        rateEntity.setProduct(productEntity);
        repository.save(rateEntity);
        productEntity.setAverageRate(calculateAverageRate(productEntity.getId()));
        return rateEntity.getId();
    }

    private Double calculateAverageRate(Long productId) {
        return repository.calculateAverageRate(productId);
    }
}
