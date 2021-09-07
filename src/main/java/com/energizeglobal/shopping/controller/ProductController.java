package com.energizeglobal.shopping.controller;


import com.energizeglobal.shopping.config.SwaggerConfig;
import com.energizeglobal.shopping.exception.NotNullException;
import com.energizeglobal.shopping.model.dto.PagingRequest;
import com.energizeglobal.shopping.model.dto.PagingResponse;
import com.energizeglobal.shopping.model.dto.ProductDto;
import com.energizeglobal.shopping.model.entity.ProductEntity;
import com.energizeglobal.shopping.model.mapper.ProductMapper;
import com.energizeglobal.shopping.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product/")
@Api(tags = {SwaggerConfig.PRODUCT_CONTROLLER_TAG})
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @Autowired
    public ProductController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @GetMapping(value = "{id}")
    @ApiOperation(value = "Endpoint for returning the product",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = ProductDto.class, httpMethod = "GET")
    public ProductDto getProductById(
            @ApiParam(value = "The id of product created by calling createProduct() method. It can't be empty or null",
                    required = true)
            @PathVariable(value = "id") Long playerId) {
        return mapper.entityToDto(service.getProductById(playerId));
    }


    @GetMapping
    @ApiOperation(value = "Endpoint for returning the product list",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = ProductDto.class, httpMethod = "GET")
    public List<ProductDto> getAllProducts() {
        return service.getAllProducts().stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @PostMapping("/search")
    @ApiOperation(value = "Endpoint for searching the product",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Long.class,
            httpMethod = "POST")
    public PagingResponse<ProductDto> searchProduct(
            @RequestBody PagingRequest pagingRequest) {

        if (pagingRequest.getSize() == 0) {
            throw new NotNullException("SIZE.IS.ZERO", "Size");
        }

        PagingResponse<ProductEntity> response = service.searchProduct(pagingRequest);
        PagingResponse<ProductDto> pagingResponse = new PagingResponse<>();
        pagingResponse.setCount(response.getCount());
        pagingResponse.setSize(response.getSize());
        pagingResponse.setStart(response.getStart());
        pagingResponse.setData(response.getData().stream().map(mapper::entityToDto).collect(Collectors.toList()));
        return pagingResponse;
    }
}
