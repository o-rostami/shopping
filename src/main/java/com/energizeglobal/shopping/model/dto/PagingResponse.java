package com.energizeglobal.shopping.model.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PagingResponse<D> {

    private int start;

    private int size;

    private int count;

    private List<D> data;
}
