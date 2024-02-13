import java.util.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
public class exercise {
    public int num;
    public String sub,con;
    public String get_sub(){
        return sub;
    }
    public String get_con(){
        return con;
    }
    public int get_num(){
        return num;
    }
    public exercise(int num,String sub,String con){
        this.num=num;
        this.sub=sub;
        this.con=con;
    }

}
