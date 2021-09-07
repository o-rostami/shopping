package com.energizeglobal.shopping.controller;


import com.energizeglobal.shopping.config.SwaggerConfig;
import com.energizeglobal.shopping.exception.NotNullException;
import com.energizeglobal.shopping.model.dto.RateDto;
import com.energizeglobal.shopping.model.mapper.RateMapper;
import com.energizeglobal.shopping.service.rate.RateService;
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
@RequestMapping("/product/rate/")
@Api(tags = {SwaggerConfig.RATE_CONTROLLER_TAG})
public class RateController {

    private final RateService service;
    private final RateMapper mapper;

    @Autowired
    public RateController(RateService service, RateMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ApiOperation(value = "Endpoint for rating the product",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Long.class,
            httpMethod = "POST")
    public Long createRate(@RequestBody RateDto rateDto) {
        if (Objects.isNull(rateDto.getProduct()) || Objects.isNull(rateDto.getProduct().getId())) {
            throw new NotNullException("PRODUCT.ID.IS.NULL", "productId");
        }
        if (Objects.isNull(rateDto.getRate())) {
            throw new NotNullException("COMMENT.BODY.IS.NULL", "body");
        }
        return service.createRate(mapper.dtoToEntity(rateDto));
    }

}
