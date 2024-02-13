import java.io.*;

public class datacreator {



    public void createdata(String filename) {
        String foldername = "生成数据";
        File folder = new File(foldername);
        if (folder.mkdirs()) {
            System.out.println("文件夹已创建！");
        } else {
            System.out.println("文件夹已存在或创建失败！");
        }
        File file = new File(folder, filename + ".txt");
        try {
            boolean fileCreated = file.createNewFile();
            if (fileCreated) {
                System.out.println("文件已创建");
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            } else {
                System.out.println("文件已存在或创建失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
