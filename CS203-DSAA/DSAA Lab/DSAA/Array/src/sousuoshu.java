public class sousuoshu {
    public static void main(String[] args) {
        Node node1 = new Node(40);
        Node node2 = new Node(15);
        Node node3 = new Node(73);
        Node node4 = new Node(10);
        Node node5 = new Node(30);
        Node node6 = new Node(60);
        Node node7 = new Node(80);
        Node node8 = new Node(3);
        Node node9 = new Node(20);
        node1.leftChild = node2;
        node2.father = node1;
        node1.rightChild = node3;
        node3.father = node1;
        node2.leftChild = node4;
        node4.father = node2;
        node2.rightChild = node5;
        node5.father = node2;
        node3.leftChild = node6;
        node6.father = node3;
        node3.rightChild = node7;
        node7.father = node3;
        node4.leftChild = node8;
        node8.father = node4;
        node5.leftChild = node9;
        node9.father = node5;
        preorder(node1);
        System.out.println();
        System.out.println(predecessorQuery(node1, 23));
        System.out.println(predecessorQuery(node1, 15));
        System.out.println(predecessorQuery(node1, 2));
        Node node10 = new Node(22);
        BSTinsert(node1, node10);
        System.out.println(predecessorQuery(node1, 23));
        preorder(node1);
        System.out.println("-------");
        BSTdelete(node1,15);
        BSTdelete(node1, 3);
        BSTdelete(node1, 10);
        System.out.println("删除之后");
        preorder(node1);
        System.out.println();
        System.out.println(predecessorQuery(node1, 23));
        System.out.println(predecessorQuery(node1, 15));
        preorder(node1);
    }

    public static void preorder(Node root) {
        if (root.leftChild != null) preorder(root.leftChild);
        System.out.print(root + " ");
        if (root.rightChild != null) preorder(root.rightChild);
    }

    public static Node predecessorQuery(Node root, int target) {
        if (root == null) return null;
        else if (root.val == target) return root;
        else if (root.val > target) return predecessorQuery(root.leftChild, target);
        else {
            if (predecessorQuery(root.rightChild, target) == null) return root;
            else return predecessorQuery(root.rightChild, target);
        }
    }

    public static void BSTinsert(Node root, Node newnode) {
        if (root == null) root = newnode;
        else if (newnode.val <= root.val) {
            if (root.leftChild != null) BSTinsert(root.leftChild, newnode);
            else {
                root.leftChild = newnode;
                newnode.father = root;
                System.out.println("添加完成！");
            }
        } else {
            if (root.rightChild != null) BSTinsert(root.rightChild, newnode);
            else {
                root.rightChild = newnode;
                newnode.father = root;
                System.out.println("添加完成");
            }
        }
    }

    public static void BSTdelete(Node root, int val) {
        if (root == null) return;
        else if (val < root.val) {
            if (root.leftChild != null)
                BSTdelete(root.leftChild, val);
            else System.out.println("未找到该节点！");
        } else if (val > root.val) {
            if (root.rightChild != null)
                BSTdelete(root.rightChild, val);
            else System.out.println("未找到该节点！");
        } else {
            if (root.leftChild == null && root.rightChild == null) {
                if (root.val == root.father.leftChild.val) root.father.leftChild = null;
                else root.father.rightChild = null;
            } else if (root.rightChild == null) {
                root.father.rightChild = root.leftChild;
                root.leftChild.father = root.father;
            } else if (root.leftChild == null) {
                root.father.leftChild = root.rightChild;
                root.rightChild.father = root.father;
            } else {
                Node tmp = root.rightChild;
                while (tmp.leftChild != null) tmp = tmp.leftChild;
                int vall = tmp.val;
                BSTdelete(root.rightChild, vall);
                root.val = vall;
                System.out.println();
            }
        }
    }
}

