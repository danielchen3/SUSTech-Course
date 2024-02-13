public class zhongxu {
    public static void main(String[] args) {
        Node node1 = new Node(26);
        Node node2 = new Node(12);
        Node node3 = new Node(32);
        Node node4 = new Node(4);
        Node node5 = new Node(18);
        Node node6 = new Node(14);
        Node node7 = new Node(24);
        node1.leftChild = node2;
        node1.rightChild = node3;
        node2.leftChild = node4;
        node2.rightChild = node5;
        node5.leftChild = node6;
        node5.rightChild = node7;
        preorder(node1);
    }

    public static void preorder(Node root) {
        if (root.leftChild != null) preorder(root.leftChild);
        System.out.println(root);
        if (root.rightChild != null) preorder(root.rightChild);
    }
}
