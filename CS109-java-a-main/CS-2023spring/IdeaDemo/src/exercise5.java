import java.util.Scanner;
public class exercise5 {
    public static void main(String[] args) {
        /*int x, y, z,answer;
        Scanner input = new Scanner(System.in);
        x = input.nextInt();
        y = input.nextInt();
        z=input.nextInt();
        answer = MAX(x, y,z);
        System.out.printf("The biggest number is %d\n", answer);*/
        Scanner input= new Scanner (System.in);
        System.out.print("Please input the size of your queue:");
        int n;
        n=input.nextInt();
        System.out.printf("Please input your initial queue:");
        int [] ini= new int[1000];
        for(int i=1;i<=n;i++)
            ini[i]=input.nextInt();
        //queue_operate(ini);
        System.out.print("Your output queue is:");
        for(int j=1;j<=n;j++)
        System.out.printf("%d ",ini[j]*8+4);
    }
    /*public static void queue_operate(int[] a){
        for(int i=1;i<=a.length;i++)
            a[i]*=8;
       return a;
    }*/
    public static int MAX(int a,int b,int c){
        int temp=a;
        if(a>=b&&a>=c) temp=a;
        else if(a<b&&a>=c)temp=b;
        else temp=c;
        return temp;
    }
}
