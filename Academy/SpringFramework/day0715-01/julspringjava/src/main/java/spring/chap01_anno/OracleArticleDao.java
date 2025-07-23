package spring.chap01_anno;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Oracle")
public class OracleArticleDao implements ArticleDao {

    @Override
    public void insert(Article article) {
        System.out.println("OracleArticleDao.insert() 실행");
    }

}
