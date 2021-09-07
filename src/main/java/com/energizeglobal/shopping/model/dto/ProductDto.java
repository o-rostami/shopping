package com.energizeglobal.shopping.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto extends BaseDto {

    private String name;
    private short rate;
    private CategoryDto category;
}
