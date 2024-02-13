import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class teacher {
    public void WriteFile(FileWriter FW) throws IOException {
        String str1 = Integer.toString(this.ID);
        String str_tot = str1 + " " + this.sub + "\n";
        FW.write(str_tot);
    }

    public static void ReadFile() throws IOException {
        try {
            File myobj = new File("teachers.txt");
            Scanner myReader = new Scanner(myobj);
            int flag = 0;
            teacher T = first_teacher;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] tokens = data.split(" ");
                int a = Integer.valueOf(tokens[0]);
                System.out.printf("%d %s\n", a, tokens[1]);
                //T=T.next_teacher;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Wrong!");
            e.printStackTrace();
        }
    }

    LinkedList<teacher> teachers = new LinkedList<>();//这个链表创建就是自己构造的吗？

    public static void add_teacher(teacher t) {
        int flag = 0;
        if (first_teacher == null) first_teacher = t;
        else {
            teacher T = first_teacher;
            while (flag == 0) {
                if (T.next_teacher == null) {
                    T.next_teacher = t;
                    flag = 1;
                } else T = T.next_teacher;
            }
        }
    }

    public static void cut_teacher(int ID) {
        int flag = 0;
        if (first_teacher.get_ID() == ID) {
            first_teacher = first_teacher.next_teacher;
        } else {
            teacher T = first_teacher;
            while (flag == 0) {
                if (T.next_teacher.get_ID() == ID) {
                    flag = 1;
                    T.next_teacher = T.next_teacher.next_teacher;
                } else T = T.next_teacher;
            }
        }
    }

    public static teacher find_teacher(int ID) {
        int flag = 0;
        teacher T = first_teacher;
        while (flag == 0) {
            if (T.get_ID() == ID) {
                flag = 1;
            } else if (T.next_teacher == null) {
                flag = 1;
                T = null;
            } else T = T.next_teacher;
        }
        return T;
    }

    public static void refresh_teacher(int ID, String newsub) {
        int flag = 0;
        teacher T = first_teacher;
        while (flag == 0) {
            if (T.get_ID() == ID) {
                flag = 1;
                T.sub = newsub;
            } else if (T.next_teacher == null) {
                flag = 1;
                System.out.println("查无此人！");
            } else T = T.next_teacher;
        }
    }

    public static teacher first_teacher;
    public int ID;
    public String sub;
    public teacher next_teacher;

    public teacher(int ID, String sub) {
        this.ID = ID;
        this.sub = sub;
        add_teacher(this);
    }

    public int get_ID() {
        return ID;
    }

    public String get_sub() {
        return sub;
    }

    public void set_ID(int ID) {
        this.ID = ID;
    }

    public void set_sub(String sub) {
        this.sub = sub;
    }

    public static void output() {
        int flag = 0;
        teacher T = first_teacher;
        while (flag == 0) {
            if (T.next_teacher == null) {
                System.out.printf("%d %s\n", T.get_ID(), T.get_sub());
                flag = 1;
            } else {
                System.out.printf("%d %s\n", T.get_ID(), T.get_sub());
                T = T.next_teacher;
            }
        }
    }
}
