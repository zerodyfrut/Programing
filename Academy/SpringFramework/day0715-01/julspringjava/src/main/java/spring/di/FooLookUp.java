package spring.di;

public class FooLookUp {

    Baz baz;

    public void doFooLookUp(){
        System.out.println("doFooLookUp() 실행");
        baz=getBaz();
        baz.doBaz();
    }

    //lookup 메서드 : 컨테이너가 Baz 타입의 bean 반환
    public Baz getBaz(){
        return null;// 오버라이딩
    }
}
