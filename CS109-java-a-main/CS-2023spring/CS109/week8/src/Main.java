public class Main {
    public static void main(String[] args) {
        String input = "a2b3c10d4";
        String output = "";
        int num = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + Character.getNumericValue(c);
            } else {
                for (int j = 0; j < num; j++) {
                    output += input.charAt(i - 1);
                }
                num = 0;
            }
        }
        System.out.println(output);
    }
}
