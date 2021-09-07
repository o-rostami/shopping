package com.energizeglobal.shopping.model.dto;

import com.energizeglobal.shopping.model.enums.SearchOperation;
import com.energizeglobal.shopping.model.enums.SortOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;
    private SortOperation sortOperation;


}
