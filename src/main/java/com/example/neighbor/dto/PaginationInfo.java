package com.example.neighbor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationInfo<TItems> {
    private int page;
    private int pageSize;
    private int itemsCount;
    private int pageCount;
    private List<TItems> items;
}
