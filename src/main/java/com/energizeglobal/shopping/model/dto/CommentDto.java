package com.energizeglobal.shopping.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto extends BaseDto {

    private String body;
    private ProductDto productDto;
}
