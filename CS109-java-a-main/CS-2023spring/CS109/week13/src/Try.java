public class Try {
    public static void main(String[] args) {
        int x=-5,y=-3;
        Try ttt=new Try();
        String s1="a";
        String s2="b";
        ttt.swap(x,y);
        ttt.swap(s1,s2);
        System.out.println(x+s1);
    }
    public void swap(int a,int b){
        int temp=a;
        a=b;
        b=temp;
    }
    public void swap(String a,String b){
        String temp=a;
        a=b;
        b=temp;
    }
}
