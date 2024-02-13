interface A {
    void take();//接口的方法？
}//放在主函数里面就不用public?
public class Main {
    public static void main(String[] args) {
        new B().take().take();//接口调用使用？
    }
}