package com.example.julspringcrawling.dto;

import lombok.Data;

@Data
public class FileInfo {
    private int fileid;
    private String name;
    private String path;
    private long filesize;
    private String description;
}
