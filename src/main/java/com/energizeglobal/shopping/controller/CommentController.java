package com.energizeglobal.shopping.controller;


import com.energizeglobal.shopping.config.SwaggerConfig;
import com.energizeglobal.shopping.exception.NotNullException;
import com.energizeglobal.shopping.model.dto.CommentDto;
import com.energizeglobal.shopping.model.mapper.CommentMapper;
import com.energizeglobal.shopping.service.comment.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("comment/")
@Api(tags = {SwaggerConfig.COMMENT_CONTROLLER_TAG})
public class CommentController {

    private final CommentService service;
    private final CommentMapper mapper;

    @Autowired
    public CommentController(CommentService service, CommentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("rate")
    @ApiOperation(value = "Endpoint for rating the product",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Long.class,
            httpMethod = "POST")
    public Long createComment(
            @RequestBody CommentDto commentDto) {
        if (Objects.isNull(commentDto.getProductDto()) || Objects.isNull(commentDto.getProductDto().getId())) {
            throw new NotNullException("PRODUCT.ID.IS.NULL", "productId");
        }
        if (Objects.isNull(commentDto.getBody())) {
            throw new NotNullException("COMMENT.BODY.IS.NULL", "body");
        }
        return service.createComment(mapper.dtoToEntity(commentDto));
    }

}
