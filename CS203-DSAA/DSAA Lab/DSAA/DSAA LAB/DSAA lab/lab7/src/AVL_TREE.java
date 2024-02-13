import java.util.Scanner;


class Main {
    public static void main(String[] args) {
        AVLTree tree_pet = new AVLTree();
        AVLTree tree_master = new AVLTree();
        AVLTree tree = new AVLTree();

        Scanner input = new Scanner(System.in);

        long ans = 0;
        int n = input.nextInt();

        for (int i = 0; i < n; i++) {
            int chose = input.nextInt();
            int value = input.nextInt();

            if (chose == 0) {
                BBST_Node node = new BBST_Node(value);
                if (tree_master.is_Empty(tree_master.root_master))
                    tree_pet.root_pet = tree_pet.insert(tree_pet.root_pet, node);
                else {
                    BBST_Node temp = tree_master.findClosestValue(tree_master.root_master, value);
                    ans += Math.abs(temp.val - value);
                    tree_master.root_master = tree_master.deleteNode_master(tree_master.root_master, temp);
                }
            }

            if (chose == 1) {
                BBST_Node node = new BBST_Node(value);
                if (tree_pet.is_Empty(tree_pet.root_pet))
                    tree_master.root_master = tree_master.insert(tree_master.root_master, node);
                else {
                    BBST_Node temp = tree_pet.findClosestValue(tree_pet.root_pet, value);
                    ans += Math.abs(temp.val - value);
                    tree_pet.root_pet = tree_pet.deleteNode_pet(tree_pet.root_pet, temp);
                }
            }
        }


        System.out.println(ans);
    }
}

class AVLTree {
    protected BBST_Node root_pet;
    protected BBST_Node root_master;


    protected long height(BBST_Node node) {
        if (node == null)
            return 0;
        return node.height;
    }


    //LL
    protected BBST_Node right_rotate(BBST_Node node) {
        BBST_Node node_left = node.left;
        node.left = node_left.right;
        node_left.right = node;
        update_Height(node);
        update_Height(node_left);
        return node_left;
    }

    //RR
    protected BBST_Node left_rotate(BBST_Node node) {
        BBST_Node node_right = node.right;
        node.right = node_right.left;
        node_right.left = node;
        update_Height(node);
        update_Height(node_right);
        return node_right;
    }

    protected long get_BT(BBST_Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    protected void update_Height(BBST_Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    protected BBST_Node Find_Closest_Ele(BBST_Node node) {
        BBST_Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    protected BBST_Node deleteNode_master(BBST_Node node, BBST_Node avl_node) {

        if (avl_node.val < node.val)
            node.left = deleteNode_master(node.left, avl_node);
        else if (avl_node.val > node.val)
            node.right = deleteNode_master(node.right, avl_node);
        else {
            if (node.left == null && node.right == null && node == root_master) {
                node = null;
                root_master = null;
            } else if (node.left == null || node.right == null) {
                BBST_Node temp = null;
                if (temp == node.left)
                    temp = node.right;
                else
                    temp = node.left;

                node = temp;
            } else {
                //找到紧邻的一个元素
                BBST_Node tt = Find_Closest_Ele(node.right);
                node.val = tt.val;
                node.right = deleteNode_master(node.right, tt);
            }
        }

        if (node == null)
            return null;

        update_Height(node);

        long bt = get_BT(node);

        //LL
        if (bt > 1 && get_BT(node.left) >= 0)
            return right_rotate(node);

            //RR
        else if (bt < -1 && get_BT(node.right) <= 0)
            return left_rotate(node);

            //LR 先对左儿子左旋，再右旋
        else if (bt > 1 && get_BT(node.left) < 0) {
            node.left = left_rotate(node.left);
            return right_rotate(node);
        }

        //RL ...
        else if (bt < -1 && get_BT(node.right) > 0) {
            node.right = right_rotate(node.right);
            return left_rotate(node);
        }

        return node;
    }

    protected BBST_Node deleteNode_pet(BBST_Node node, BBST_Node avl_node) {

        if (avl_node.val < node.val)
            node.left = deleteNode_pet(node.left, avl_node);
        else if (avl_node.val > node.val)
            node.right = deleteNode_pet(node.right, avl_node);
        else {
            //BST删除
            if (node.left == null && node.right == null && node == root_pet) {
                node = null;
                root_pet = null;
            } else if (node.left == null || node.right == null) {
                BBST_Node temp = null;
                if (temp == node.left)
                    temp = node.right;
                else
                    temp = node.left;
                node = temp;
            } else {
                BBST_Node tt = Find_Closest_Ele(node.right);
                node.val = tt.val;
                node.right = deleteNode_pet(node.right, tt);
            }
        }

        if (node == null)
            return null;

        long bt = get_BT(node);

        if (bt > 1 && get_BT(node.left) >= 0)
            return right_rotate(node);

        else if (bt < -1 && get_BT(node.right) <= 0)
            return left_rotate(node);

        else if (bt > 1 && get_BT(node.left) < 0) {
            node.left = left_rotate(node.left);
            return right_rotate(node);
        } else if (bt < -1 && get_BT(node.right) > 0) {
            node.right = right_rotate(node.right);
            return left_rotate(node);
        }

        return node;
    }

    //返回当前根节点
    protected BBST_Node insert(BBST_Node node, BBST_Node avl_node) {
        //先插入节点
        if (node == null)
            return avl_node;
        if (avl_node.val < node.val)
            node.left = insert(node.left, avl_node);
        else if (avl_node.val > node.val)
            node.right = insert(node.right, avl_node);

        update_Height(node);
        //调整平衡
        long bt = get_BT(node);

        if (bt > 1 && get_BT(node.left) >= 0)
            return right_rotate(node);

        else if (bt < -1 && get_BT(node.right) <= 0)
            return left_rotate(node);

        else if (bt > 1 && get_BT(node.left) < 0) {
            node.left = left_rotate(node.left);
            return right_rotate(node);
        } else if (bt < -1 && get_BT(node.right) > 0) {
            node.right = right_rotate(node.right);
            return left_rotate(node);
        }

        return node;
    }


    protected void preOrder(BBST_Node node) {
        if (node != null) {
            preOrder(node.left);
            System.out.print(node.val + " ");
            preOrder(node.right);
        }
    }

    protected long getSize(BBST_Node node) {
        if (node == null)
            return 0;
        return node.size;
    }

    protected boolean is_Empty(BBST_Node node) {
        return node == null;
    }

    protected BBST_Node findClosestValue(BBST_Node node, int target) {
        BBST_Node closest = node;
        while (node != null) {
            if (node.val == target)
                return node;
            if (Math.abs(node.val - target) < Math.abs(closest.val - target))
                closest = node;
            if (Math.abs(node.val - target) == Math.abs(closest.val - target) && closest.val > node.val)
                closest = node;
            if (target < node.val)
                node = node.left;
            else
                node = node.right;
        }
        return closest;
    }
}

class BBST_Node {
    long val, height, size;
    BBST_Node left, right;

    BBST_Node(long val) {
        this.val = val;
        height = 1;
        size = 1;
    }
}
