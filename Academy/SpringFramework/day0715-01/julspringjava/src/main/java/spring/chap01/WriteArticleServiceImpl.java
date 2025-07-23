package spring.chap01;

public class WriteArticleServiceImpl implements WriteArticleService {
  private ArticleDao articleDao; 
  
  //해당 인터페이스를 구현한 객체를 변수로 받음

  public WriteArticleServiceImpl(ArticleDao articleDao) { //자손 타입 빈 주입
    this.articleDao = articleDao;
  }

  public void write(Article article) {
    System.out.println("WriteArticleServiceImpl.write() 메서드 실행");
    articleDao.insert(article); //오버라이딩 된 메소드(sub에 있는) 실행
  }
}
