package com.energizeglobal.shopping.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateDto extends BaseDto {

    private Short rate;
    private ProductDto product;
}
