package com.example.neighbor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationInfo<TItems> {
    private int Page;
    private int PageSize;
    private int ItemsCount;
    private int PageCount;
    private List<TItems> Items;
}
