package com.energizeglobal.shopping.service.comment;

import com.energizeglobal.shopping.exception.BadRequestException;
import com.energizeglobal.shopping.exception.BusinessException;
import com.energizeglobal.shopping.model.entity.CommentEntity;
import com.energizeglobal.shopping.model.entity.ProductEntity;
import com.energizeglobal.shopping.repository.CommentRepository;
import com.energizeglobal.shopping.service.product.ProductService;
import com.energizeglobal.shopping.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository repository;
    @Autowired
    private ProductService productService;


    @Override
    public Long createComment(CommentEntity commentEntity) {

        if (commentEntity.getId() != null) {
            throw new BadRequestException("The ID must not be provided when creating", "Id");
        }

        ProductEntity productEntity = productService.getProductById(commentEntity.getProduct().getId());
        if (Objects.nonNull(repository.findByBodyAndCreatedBy(commentEntity.getBody(), SecurityUtils.getCurrentUser()))) {
            throw new BusinessException("COMMENT.IS.DUPLICATE", "body");
        }
        commentEntity.setProduct(productEntity);
        return repository.save(commentEntity).getId();
    }

}
