import java.util.Scanner;
public class exercise9 {
    private String Owner_Name;
    private int Account_Number;
    private float interest;
    public String setOwner_Name(){
        return Owner_Name;
    }
    public int setAccount_Number(){
        return Account_Number;
    }
    public float setinterest(){
        return interest;
    }
    public void getOwner_Name(String Name){
        Owner_Name=Name;
    }
    public void getAccount_Number(int Number){
        Account_Number=Number;
    }
    public void getinterest(float new_interest){
        interest=new_interest;
    }
    public void displayMessage(){
        System.out.printf("Your Account Number is: %d\n",setAccount_Number());
        System.out.printf("Your Bank Interest is: %.2f\n",setinterest());
    }
    public static void main(String[] args) {
        exercise9 get_information=new exercise9();
        Scanner input = new Scanner(System.in);
        String Name;
        int Account_Number;
        float inter;
        System.out.println("Please enter your personal information:");
        System.out.print("Name:");Name=input.nextLine();
        System.out.print("ID:");Account_Number=input.nextInt();
        System.out.print("Your ideal interest:");inter= input.nextFloat();
        get_information.getOwner_Name(Name);
        get_information.getAccount_Number(Account_Number);
        get_information.getinterest(inter);
        System.out.printf("You have the following bank account:\n");
        System.out.printf("Your Name is: %s\n",get_information.setOwner_Name());
        get_information.displayMessage();
        System.out.println();
    }
}
