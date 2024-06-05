package com.quyennv.datn.courseservice.presenter.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String price;
    private Integer stock;
    private Integer id;
    private String name;
}