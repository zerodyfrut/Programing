package kr.solde;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteImageExcel {
    public static void main(String[] args) {
        try {

            XSSFWorkbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("My Sample Excel");
            InputStream is = new FileInputStream("pic.jpg");
            byte[] bytes = IOUtils.toByteArray(is);
            int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            is.close();

            CreationHelper helper = wb.getCreationHelper();
            Drawing drawing = sheet.createDrawingPatriarch();

            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setRow1(2);
            anchor.setCol1(1);
            anchor.setRow2(3);
            anchor.setCol2(2);

            Picture pict = drawing.createPicture(anchor, pictureIdx); // 이미지 추가

            Cell cell1=sheet.createRow(0).createCell(0);
            cell1.setCellValue("excel");

            Cell cell = sheet.createRow(2).createCell(2);
            int w = 20 * 256;
            sheet.setColumnWidth(1, w);

            short h = 120 * 20;
            cell.getRow().setHeight(h);

            FileOutputStream fileOut = new FileOutputStream("myFile.xlsx");
            // HSSFWorkbook : xls
            // 만약 xlsx확장자를 쓰고 싶다면 XSSFWorkbook로 사용
            wb.write(fileOut);
            fileOut.close();;
            System.out.println("이미지 생성 성공");


        } catch (Exception e) {
        }

    }
}
