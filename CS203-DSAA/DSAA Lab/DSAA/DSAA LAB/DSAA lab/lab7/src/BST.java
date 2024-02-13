import java.util.Scanner;

public class BST {

    public static int count = 0;

    public static Node tru_root;

    public static int ans = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Node[] nodes = new Node[10001];

        int n = input.nextInt();

        for (int i = 1; i <= n; i++) {
            int x = input.nextInt();
            Node node = new Node(x);
            nodes[i] = node;
            BST_insert(tru_root, nodes[i]);
        }

        for (int i = n; i >= 1; i--) {

            preorder(tru_root);
            count = 0;
            ans = 0;
            get_m_index_val(tru_root, i);
            System.out.println();
            System.out.println(ans);
            System.out.println();
            BST_delete(tru_root, nodes[i]);
        }


    }

    public static void preorder(Node root) {
        if (root.left != null) preorder(root.left);
        System.out.print(root.val + " ");
        if (root.right != null) preorder(root.right);
    }

    public static void get_m_index_val(Node root, int m) {
        if (root.left != null) get_m_index_val(root.left, m);
        count++;
        if (m == count) ans = root.val;
        if (root.right != null) get_m_index_val(root.right, m);
    }

//    public static Node predecessorQuery(Node root, int target) {
//
//    }

    public static void BST_insert(Node root, Node newnode) {
        if (root == null) tru_root = newnode;
        else if (newnode.val <= root.val) {
            if (root.left != null) BST_insert(root.left, newnode);
            else {
                root.left = newnode;
                newnode.father = root;
            }
        } else {
            if (root.right != null) BST_insert(root.right, newnode);
            else {
                root.right = newnode;
                newnode.father = root;
            }
        }
    }


    //node表示即将被删除的结点
    public static void BST_delete(Node root, Node node) {
        if (root == null) return;
        else if (root.val < node.val) {
            if (root.right != null) BST_delete(root.right, node);
            else return;
        } else if (root.val > node.val) {
            if (root.left != null) BST_delete(root.left, node);
            else return;
        } else {
            //叶子结点那么直接删掉（这里对于相等的val随机选择一个删除，对于大部分case应该没有影响）
            if (root.left == null && root.right == null) {
                if (root == tru_root) tru_root = null;
                else {
                    if (root.father.left == root) root.father.left = null;
                    else root.father.right = null;
                }
            }
            //如果他只有左儿子
            else if (root.right == null) {
                if (root == tru_root) {
                    tru_root = root.left;
                    root = null;
                } else {
                    //如果他是他父亲的左儿子
                    if (root.father.left == root) {
                        root.father.left = root.left;
                        root.left.father = root.father;
                    }
                    //如果他是右儿子
                    else {
                        root.father.right = root.left;
                        root.left.father = root.father;
                    }
                }
            }
            //如果他只有右儿子(同理)
            else if (root.left == null) {
                if (root == tru_root) {
                    tru_root = root.right;
                    root = null;
                } else {
                    if (root.father.left == root) {
                        root.father.left = root.right;
                        root.right.father = root.father;
                    } else {
                        root.father.right = root.right;
                        root.right.father = root.father;
                    }
                }
            }
            //如果两边儿子都有
            else {
                Node tmp = root.right;
                while (tmp.left != null) tmp = tmp.left;
                int xx = tmp.val;
                //删掉tmp这个节点就好了
                if (tmp.right == null) {
                    if (tmp.father.left == tmp) tmp.father.left = null;
                    else tmp.father.right = null;
                } else {
                    if (tmp.father.left == tmp) {
                        tmp.father.left = tmp.right;
                        tmp.right.father = tmp.father;
                    } else {
                        tmp.father.right = tmp.right;
                        tmp.right.father = tmp.father;
                    }
                }
                root.val = xx;
            }
        }
    }

}


class Node {
    int val;
    Node father;
    Node left;
    Node right;
    int index;

    public Node(int val) {
        this.val = val;
    }
}
