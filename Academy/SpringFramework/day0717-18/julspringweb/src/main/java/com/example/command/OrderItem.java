package com.example.command;

import lombok.Data;

@Data
public class OrderItem {

    private Integer itemId;
    private Integer number;
    private String remark;
}