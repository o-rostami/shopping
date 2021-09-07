package com.energizeglobal.shopping.model.mapper;

import com.energizeglobal.shopping.model.dto.RateDto;
import com.energizeglobal.shopping.model.entity.RateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RateMapper extends BaseMapper {

    RateEntity dtoToEntity(RateDto dto);

    RateDto entityToDto(RateEntity entity);

}
