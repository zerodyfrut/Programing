package spring.di;

public class Foo {

    private Bar bar;
    Baz baz;

    int i;
    String str;

    public Foo() {

    }

    public Foo(Bar bar, Baz baz) {
        this.bar = bar;
        this.baz = baz;
    }

    public Foo(Bar bar, Baz baz, int i, String str) {
        this.bar = bar;
        this.baz = baz;
        this.i = i;
        this.str = str;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public void setBaz(Baz baz) {
        this.baz = baz;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void doFoo() {
        System.out.println("Foo.doFoo() 실행");
        bar.doBar();// 의존 관계
        System.out.println(i + " : " + str);
    }

    

}
