public class shellsort {
    public static void main(String[] args) {
        int[] arr = {10,9,8,7,6,5,4,3,2,1};
        shell_sort(arr);
        print(arr);
    }

    public static void shell_sort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int tmp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > tmp; j -= gap)
                    arr[j] = arr[j - gap];
                arr[j] = tmp;
            }
        }
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++)
            System.out.printf(arr[i] + " ");
        System.out.println(arr[arr.length - 1]);
    }

}
