public class B {
    public A take(){
        return new A(){
            public void take() {
                System.out.println("B类声明实现A接口并返回接口的作用--dare you!");
            }
        };
    }
}
