public class Node {
    public Node rightChild = null;
    public Node leftChild = null;

    public Node father = null;
    public int val;

    public Node(int val) {
        this.val = val;
    }

    public String toString() {
        return val + "";
    }
}
