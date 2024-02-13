import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

//  Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    ArrayList<TreeNode> treenodeP = new ArrayList<TreeNode>();
    ArrayList<TreeNode> treenodeQ = new ArrayList<TreeNode>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        getpathp(root, p);
        getpathq(root, q);
        TreeNode ans = null;
        if (treenodeP.size() <= treenodeQ.size()) ans = p;
        else ans = q;
        int i = 0;
        while (i < Math.min(treenodeP.size(), treenodeQ.size())) {
            if (treenodeP.get(i).val != treenodeQ.get(i).val) {
                ans = treenodeP.get(i - 1);
                break;
            } else i++;
        }
        return ans;
    }

    public void getpathp(TreeNode root, TreeNode p) {
        if (root == null) return;
        if (root.val < p.val) {
            treenodeP.add(root);
            getpathp(root.left, p);
        } else if (root.val > p.val) {
            treenodeP.add(root);
            getpathp(root.right, p);
        } else {
            treenodeP.add(root);
            return;
        }
    }

    public void getpathq(TreeNode root, TreeNode p) {
        if (root == null) return;
        if (root.val < p.val) {
            treenodeQ.add(root);
            getpathq(root.left, p);
        } else if (root.val > p.val) {
            treenodeQ.add(root);
            getpathq(root.right, p);
        } else {
            treenodeQ.add(root);
            return;
        }
    }
}

class minipath {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        else if (root.left == null && root.right == null) return 1;
        else if (root.left == null || root.right == null) return minDepth(root.left) + minDepth(root.right) + 1;
        else {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
    }
}


class PathSum {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<>(), ans);
        return ans;
    }

    public void dfs(TreeNode root, int targetSum, List<Integer> sublist, List<List<Integer>> ans) {
        if (root == null) return;
        sublist.add(root.val);
        if (root.left == null && root.right == null) {
            if (targetSum == root.val)
                ans.add(new ArrayList(sublist));//为啥一定要new一下
            sublist.remove(sublist.size() - 1);
            return;
        }
        dfs(root.left, targetSum - root.val, sublist, ans);
        dfs(root.right, targetSum - root.val, sublist, ans);
        sublist.remove(sublist.size() - 1);
    }
}