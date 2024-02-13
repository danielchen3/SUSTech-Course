public class StudentBZ extends Student{
    public String zeren;
    public StudentBZ(){super();}
    public StudentBZ(int ID,String name,double escore,double mscore,double cscore,String zeren){
        super(ID,name,escore,mscore,cscore);
        this.zeren=zeren;
    }
    public void get_zeren(String zeren){
        this.zeren=zeren;
    }
    public String zeren(){
        return zeren;
    }
    public void testScore(){
        testscore=eqscore+5;
    }
    public String toString(){
        return "Student "+ ID + ", 英语成绩为" + escore + ", 数学成绩为" + mscore + ", 计算机成绩为" +cscore+ ", 名字 " + name+", 职位为"+zeren;
    }
}