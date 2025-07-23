package spring.chap01_anno;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("MySQL")
public class MySQLArticleDao implements ArticleDao {
  public void insert(Article article) {
    System.out.println("MySQLArticleDao.insert() 실행");
  }
}