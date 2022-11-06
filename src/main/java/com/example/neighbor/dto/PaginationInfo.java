package com.example.neighbor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationInfo<TItems> {
    public int Page;
    public int PageSize;
    public int ItemsCount;
    public int PageCount;
    public List<TItems> Items;
}
