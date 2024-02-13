import java.util.Scanner;
public class Student {
    public int ID;
    public String name;
    public double escore,mscore,cscore,tscore,eqscore,testscore;//this 的用法
    public Student(){}
    public void Student(int ID,String name,double escore,double mscore,double cscore){
        this.ID=ID;
        this.name=name;
        this.escore=escore;
        this.mscore=mscore;
        this.cscore=cscore;
    }
    public double dototal(){
        return (this.escore+this.mscore+this.cscore);
    }
    public double equal(){
        return this.tscore/3;
    }
    public int get_ID(){
        return ID;
    }
    public String get_name(){
        return name;
    }
    public double get_escore(){
        return escore;
    }
    public double get_mscore(){
        return mscore;
    }
    public double get_cscore(){
        return cscore;
    }
    public double get_tscore(){
        return tscore;
    }
    public double get_equalscore(){
        return eqscore;
    }
    public void set_ID(int ID){
        this.ID=ID;
    }
    public void set_name(String name){
        this.name=name;
    }
    public void set_escore(double escore){
        this.escore=escore;
    }
    public void set_mscore(double mscore){
        this.mscore=mscore;
    }
    public void set_cscore(double cscore){
        this.cscore=cscore;
    }
    public void set_tscore(double tscore){
        this.tscore=tscore;
    }
    public void dosum(){
        tscore=mscore+escore+cscore;
    }
    public void doequal(){
        eqscore=tscore/3;
    }
    public void Compare(Student p) {
        if(this.tscore>p.tscore)
            System.out.println(this.name+"同学成绩高");
        else if(this.tscore==p.tscore)
            System.out.println("两位同学成绩相同");
        else System.out.println(p.name+"同学成绩高");
    }
    public String toString() {
        return "Student "+ ID + ", 英语成绩为" + escore + ", 数学成绩为" + mscore + ", 计算机成绩为" +cscore+ ", 总成绩为" + tscore + ", 平均成绩为" + eqscore + ", 名字 " + name;
    }
}