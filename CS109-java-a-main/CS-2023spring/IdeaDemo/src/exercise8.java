import java.util.Scanner;
public class exercise8 {
    public static void main(String[] args) {
        System.out.print('\u5357');
        System.out.print('\u65B9');
        System.out.print('\u79D1');
        System.out.print('\u6280');
        System.out.print('\u5927');
        System.out.print('\u5B66');
        System.out.println();
        Scanner input = new Scanner(System.in);
        String s1="welcome_you";
        String s2=s1.concat("!");
        String s3=new String(s2);
        char[] ARR={'h','e','l','l'};
        String s4=new String(ARR);
        //String s5;
        String s5=new String(ARR,2,2);
        String s6="WELCOME_YOU";
        if(!s1.equalsIgnoreCase(s6)) System.out.println("NO");
        else System.out.println("YES");
        if(!s1.equals(s6)) System.out.println("NO");
        else System.out.println("YES");
        int result=s1.compareTo(s6);
        System.out.printf("%s %s %s %s %s %d %s %d\n",s1,s2,s3,s4,s5,s1.length(),s6,result);
        if(s1.startsWith("el",1)) System.out.println("YES"); else System.out.println("NO");
        if(s6.endsWith("OME")) System.out.println("YES"); else System.out.println("NO");
        boolean answer=s1.regionMatches(true,0,s6,0,5);
        if(answer) System.out.println("YES"); else System.out.println("NO");
        System.out.println(s1.indexOf('o',5));
        System.out.println(s1.substring(3));
        for(int count=s2.length()-1;count>=0;count--){
            System.out.printf("%c",s2.charAt(count));
        }
        System.out.println();
        System.out.printf("%s\n",new String(s1.toCharArray(),0,5));
        System.out.println("Please enter a sentence and press Enter:");
        String sentence = input.nextLine();
        String[] tokens = sentence.split(" ");
        System.out.printf("Number of tokens: %d\n", tokens.length);
        for(int count=0;count<tokens.length;count++) System.out.println(tokens[count]);
    }
}
