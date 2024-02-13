import java.util.Scanner;
public class exercise2 {
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        System.out.print("please choose your ideal accuracy");
        int n;
        n=input.nextInt();
        double pi=4;
        int judge=0;
        for(int i=1;i<=n;i++){
            if(judge==0){
                pi-=(float)4/(2*i+1);
                judge=1;
            }
            else{
                pi+=(float)4/(2*i+1);
                judge=0;
            }
        }
        System.out.printf("pi is %.8f\n",pi);
    }
}
