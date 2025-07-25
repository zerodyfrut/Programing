package kr.solde;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExcelVO {

    private String title;
    private String author;
    private String company;
    private String isbn;
    private String imgurl;

    public ExcelVO(String title, String author, String company){
        super();
        this.title=title;
        this.author=author;
        this.company=company;
    }
    
}
