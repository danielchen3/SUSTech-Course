public class B extends A{
    public int b;
    public B(int b) {
        super();
        System.out.println("调用父类");
    }
    public B() {
        System.out.println("非默认方法B");
    }
}