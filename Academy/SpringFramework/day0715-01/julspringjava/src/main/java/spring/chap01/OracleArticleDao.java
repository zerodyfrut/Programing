package spring.chap01;

public class OracleArticleDao implements ArticleDao{

    @Override
    public void insert(Article article) {
        System.out.println("OracleArticleDao.insert() 실행");
    }
    
    
}
