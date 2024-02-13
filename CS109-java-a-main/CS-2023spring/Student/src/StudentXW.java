public class StudentXW extends Student{
    public String zeren;
    public void get_zeren(String zeren){
        this.zeren=zeren;
    }
    public String zeren(){
        return zeren;
    }
    public void testScore(){
        testscore=eqscore+3;
    }
    public String toString(){
        return "Student学习委员 "+ ID + ", 英语成绩为" + escore + ", 数学成绩为" + mscore + ", 计算机成绩为" +cscore+ ", 总成绩为" + tscore + ", 平均成绩为" + eqscore + ",评测成绩为"+testscore+", 名字 " + name;
    }
}
