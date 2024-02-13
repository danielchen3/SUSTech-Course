import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Preinpost {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int n = input.nextInt();

            int[] pre = new int[n + 1];
            int[] in = new int[n + 1];
            for (int j = 1; j <= n; j++) pre[j] = input.nextInt();
            for (int j = 1; j <= n; j++) in[j] = input.nextInt();
            TreeNode root = buildTree(pre, in, 1, n, 1, n);
            postorder(root);
            System.out.println();
        }
    }

    public static TreeNode buildTree(int[] pre, int[] in, int l_pre, int r_pre, int l_in, int r_in) {

        if (r_pre < l_pre) return null;

        TreeNode root = new TreeNode();
        root.key = pre[l_pre];
        //System.out.println(root.key);
        int ii;
        for (ii = l_in; ii <= r_in; ii++) {
            if (in[ii] == root.key) {
                break;
            }
        }

        root.left = buildTree(pre, in, l_pre + 1, l_pre + ii - l_in, l_in, ii - 1);
        root.right = buildTree(pre, in, l_pre + ii - l_in + 1, r_pre, ii + 1, r_in);

        return root;
    }

    public static void postorder(TreeNode root) {
        if (root.left != null) postorder(root.left);
        if (root.right != null) postorder(root.right);
        System.out.print(root.key + " ");
    }

}

class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;
}
