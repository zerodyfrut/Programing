package com.example.command;

import lombok.Data;

@Data
public class SearchCommand {
    private int type;
    private String query;

}
