package kr.solde;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class DownloadBroker implements Runnable{
    private String address;
    private String fileName;
   public DownloadBroker(String address, String fileName) {
      super();
      this.address = address; // 이미자 다운 받을 사이트 주소
      this.fileName = fileName; // 서버에 저장할 파일(경로)
   }
   @Override
   public void run() {
      try {
         FileOutputStream fos=new FileOutputStream(fileName);
         BufferedOutputStream bos=new BufferedOutputStream(fos);
         
         URL url=new URL(address);
         InputStream is=url.openStream();
         BufferedInputStream input=new BufferedInputStream(is);
         
         int data;
         while((data=input.read())!=-1) {// 읽을게 없을때 까지 읽기
            bos.write(data);
         }
         bos.close();
         input.close();
         System.out.println("download complete...");
         System.out.println(fileName);//다운로드 된 파일 경로
      } catch (Exception e) {
         e.printStackTrace();
      }      
   }    
}
