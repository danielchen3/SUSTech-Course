public class student {
    public int ID,score;
    public String name;
    public static student(int ID,String name,int ID){
        this.ID=ID;
        this.name=name;
        this.score=score;
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
}
