package com.example.julspringcrawling.command;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BibleCommand {
    private String bible_text;
    private String bibleinfo_box;

    private List<String> liList = new ArrayList<>();
    
    private String filename;
}
