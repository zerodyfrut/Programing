package kr.solde;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ReadExcel {
    public static void main(String[] args) {
        String filename = "bookList.xls";
        // Excel -> java Object
        List<ExcelVO> data = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream(filename)){
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet =workbook.getSheetAt(0);
            Iterator<Row> rows= sheet.rowIterator();

            rows.next();// 맨윗줄(타이틀) 제외
            String[] imsi=new String[5]; // excel 파일의 열이 5개 임

            while(rows.hasNext()){//열이 있으면 
                Row row=rows.next();//다음열을 가져옴
                Iterator<Cell> cells= row.cellIterator();// 한열에 대한 cell Iterator

                int i=0;

                while (cells.hasNext()) {//cell이 있으면, 근데 어차피 5개 돌면 되지 않나
                    Cell cell=cells.next();
                    imsi[i]=cell.toString();
                    i++;
                }
                ExcelVO vo=new ExcelVO(imsi[0], imsi[1],imsi[2],imsi[3],imsi[4]);
                data.add(vo);
            }
        showExcelData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//main

    private static void showExcelData(List<ExcelVO> data) {
        for(ExcelVO vo: data){
            System.out.println(vo);
        }
    }
}
