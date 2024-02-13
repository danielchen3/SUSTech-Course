import java.io.*;
import java.util.Scanner;

public class student {
    public static student [] stu=new student [1000];
    private int ID,grade;
    private String name;
    public student(int ID,String name,int grade){
        this.ID=ID;
        this.name=name;
        this.grade=grade;
    }
    public int getID(){
        return ID;
    }
    public String getname(){
        return name;
    }
    public int getgrade(){
        return grade;
    }
    public String get_information(){
        return "学号："+ID+",姓名："+name+",成绩："+grade;
    }
    public void WriteFile (FileWriter FW) throws IOException {
        String str1=Integer.toString(this.ID);
        String str2=Integer.toString(this.grade);
        String str_total=str1+" "+name+" "+str2+"\n";
        FW.write(str_total);
    }
    public static void ReadFile() throws IOException{
        try {
            int i=0;
            File myObj = new File("Students.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String [] tokens=data.split(" ");
                int a=Integer.valueOf(tokens[0]);
                int b=Integer.valueOf(tokens[2]);
                stu[i]=new student(a,tokens[1],b);
                //System.out.printf("%d %s %d\n",a,tokens[1],b);
                System.out.printf("%d %s %d\n",stu[i].getID(),stu[i].getname(),stu[i].getgrade());
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void add_stu() throws IOException{

    }
}//类方法
