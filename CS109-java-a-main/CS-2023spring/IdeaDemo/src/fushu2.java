public class fushu2 {
    //fushu cal2=new fushu();
    private int a,b;
    public fushu2(int x,int y){
        a=x;b=y;
    }
    public fushu2(){
        this(1,2);
    }
    public void get_add(int c,int d){
        a+=c;
        b+=d;
    }
    public void get_minus(int c,int d){
        a-=c;
        b-=d;
    }
    public void get_mul(int c,int d){
        int temp=a;
        a=a*c-b*d;
        b=temp*d+b*c;
    }
    public int get_result1(){
        return a;
    }
    public int get_result2(){
        return b;
    }
    public void displayMessage(int c,int d){
        System.out.printf("The answer is: ");
        if(c==0) System.out.printf("%di\n",d);
        else if(c==0&&d!=0) System.out.printf("%di\n",d);
        else if(d>0) System.out.printf("%d+%di",c,d);
        else System.out.printf("%d%di",c,d);
    }
}
