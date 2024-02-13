import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Quick quick = new Quick();
        Merge merge = new Merge();
        int[] a = {3, 3, 2, 3, 12, 1, 41, 6, 8, 7, 22};
        System.out.println("排序前");
        System.out.println(Arrays.toString(a));
        System.out.println("排序后");
        //quick.QuickSort(a, 0, a.length - 1);
        merge.MergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
