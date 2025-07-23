package spring.chap01_anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("writeArticleService")
public class WriteArticleServiceImpl implements WriteArticleService {
  @Autowired
  @Qualifier("Oracle")
  private ArticleDao articleDao; 
  
  //해당 인터페이스를 구현한 객체를 변수로 받음

  public void write(Article article) {
    System.out.println("WriteArticleServiceImpl.write() 메서드 실행");
    articleDao.insert(article); //오버라이딩 된 메소드(sub에 있는) 실행
  }
}
