public class ExTest {
    public static void main(String[] args) {
        MergeInterval mergeInterval = new MergeInterval();
        int[][] nums = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        mergeInterval.Merge(nums);
        for (int i = 0; i < nums.length; i++)
            System.out.printf("%d %d\n", nums[i][0], nums[i][1]);
    }
}
