package com.energizeglobal.shopping.model.mapper;

import com.energizeglobal.shopping.model.dto.ProductDto;
import com.energizeglobal.shopping.model.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper{

    ProductEntity dtoToEntity(ProductDto dto);

    ProductDto entityToDto(ProductEntity entity);

}
