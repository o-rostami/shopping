package com.energizeglobal.shopping.controller;


import com.energizeglobal.shopping.config.SwaggerConfig;
import com.energizeglobal.shopping.exception.NotNullException;
import com.energizeglobal.shopping.model.dto.ProductDto;
import com.energizeglobal.shopping.model.mapper.ProductMapper;
import com.energizeglobal.shopping.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("category/product")
@Api(tags = {SwaggerConfig.PRODUCT_PER_CATEGORY_CONTROLLER_TAG})
public class ProductPerCategoryController {

    private final ProductService service;
    private final ProductMapper mapper;

    @Autowired
    public ProductPerCategoryController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ApiOperation(value = "Endpoint for creating the product",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Long.class,
            httpMethod = "POST")
    public Long createProduct(@RequestBody ProductDto productDto) {
        if (Objects.isNull(productDto.getCategory()) || Objects.isNull(productDto.getCategory().getId())) {
            throw new NotNullException("CATEGORY.ID.IS.NULL", "CategoryId");
        }
        if (Objects.isNull(productDto.getName())) {
            throw new NotNullException("PRODUCT.NAME.IS.NULL", "productName");
        }
        return service.createProduct(mapper.dtoToEntity(productDto));
    }

    @PutMapping
    @ApiOperation(value = "Endpoint for updating the product",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Long.class,
            httpMethod = "PUT")
    public ProductDto updateProduct(
            @RequestBody ProductDto productDto) {
        if (Objects.isNull(productDto.getCategory()) || Objects.isNull(productDto.getCategory().getId())) {
            throw new NotNullException("CATEGORY.ID.IS.NULL", "CategoryId");
        }
        if (Objects.isNull(productDto.getName())) {
            throw new NotNullException("PRODUCT.NAME.IS.NULL", "productName");
        }
        return mapper.entityToDto(service.updateProduct(mapper.dtoToEntity(productDto)));
    }


    @GetMapping(value = "{categoryId}/{id}")
    @ApiOperation(value = "Endpoint for returning the product",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = ProductDto.class, httpMethod = "GET")
    public ProductDto findByIdAndCategoryId(
            @ApiParam(value = "The id of product created by calling createProduct() method. It can't be empty or null",
                    required = true)
            @PathVariable(value = "categoryId") Long categoryId,
            @PathVariable(value = "id") Long playerId) {
        return mapper.entityToDto(service.findByIdAndCategoryId(categoryId, playerId));
    }


    @DeleteMapping(value = "{categoryId}/{id}")
    @ApiOperation(value = "Endpoint for removing the product",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = ProductDto.class, httpMethod = "DELETE")
    public void deleteByIdCategoryId(
            @ApiParam(value = "The id of product created by calling createProduct() method. It can't be empty or null",
                    required = true)
            @PathVariable(value = "categoryId") Long categoryId,
            @PathVariable(value = "id") Long playerId) {
        service.deleteByIdCategoryId(categoryId, playerId);
    }
}
