package com.energizeglobal.shopping.model.mapper;

import com.energizeglobal.shopping.model.dto.CommentDto;
import com.energizeglobal.shopping.model.entity.CommentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper extends BaseMapper {

    CommentEntity dtoToEntity(CommentDto dto);

    CommentDto entityToDto(CommentEntity entity);

}
