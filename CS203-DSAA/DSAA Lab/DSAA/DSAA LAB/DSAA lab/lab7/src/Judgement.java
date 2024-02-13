import com.sun.source.tree.Tree;

import java.io.*;
import java.util.StringTokenizer;

public class Judgement {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int T = input.nextInt();

        for (int i = 0; i < T; i++) {
            boolean judge = true;
            int n = input.nextInt();
            TreeNode[] nodes = new TreeNode[1000001];
            for (int j = 1; j <= n; j++) {
                nodes[j] = new TreeNode();
                nodes[j].index = j;
                nodes[j].val = input.nextInt();
            }
            for (int j = 0; j < n - 1; j++) {
                int x = input.nextInt();
                int y = input.nextInt();
                if (nodes[x].left == null) {
                    nodes[x].left = nodes[y];
                    nodes[y].father = nodes[x];
                } else if (nodes[x].right == null) {
                    nodes[x].right = nodes[y];
                    nodes[y].father = nodes[x];
                } else {
                    judge = false;
                    break;
                }
            }
            if (judge) {
                boolean is_smallheap = true;
                boolean is_bigheap = true;
                TreeNode tmp = nodes[1];
                while (tmp.father != null) {
                    tmp = tmp.father;
                }
                TreeNode root = tmp;
                Queueue queue1 = new Queueue();
                queue1.enqueue(root.index);
                boolean is_full = true;
                //判断是否完整
                while (!queue1.isEmpty()) {
                    int ss = queue1.dequeue();
                    TreeNode fuck = nodes[ss];
                    int num = 0;
                    if (fuck.left != null) {
                        if (!is_full) {
                            judge = false;
                            break;
                        }
                        queue1.enqueue(fuck.left.index);
                        num++;
                    }
                    if (fuck.right != null) {
                        if (!is_full) {
                            judge = false;
                            break;
                        }
                        queue1.enqueue(fuck.right.index);
                        num++;
                    }
                    if (num != 2) is_full = false;
                }
                //这里进来的一定是完整的
                //判断小顶堆
                if (judge) {
                    Queueue queue2 = new Queueue();
                    queue2.enqueue(root.index);
                    while (!queue2.isEmpty()) {
                        int ss = queue2.dequeue();
                        TreeNode fuck = nodes[ss];
                        if (fuck.left != null) {
                            if (fuck.val > fuck.left.val) {
                                is_smallheap = false;
                                break;
                            }
                            queue2.enqueue(fuck.left.index);
                        }
                        if (fuck.right != null) {
                            if (fuck.val > fuck.right.val) {
                                is_smallheap = false;
                                break;
                            }
                            queue2.enqueue(fuck.right.index);
                        }
                    }
                }

                //判断大顶堆

                if (judge && !is_smallheap) {
                    Queueue queue3 = new Queueue();
                    queue3.enqueue(root.index);
                    while (!queue3.isEmpty()) {
                        int ss = queue3.dequeue();
                        TreeNode fuck = nodes[ss];
                        if (fuck.left != null) {
                            if (fuck.val < fuck.left.val) {
                                is_bigheap = false;
                                break;
                            }
                            queue3.enqueue(fuck.left.index);
                        }
                        if (fuck.right != null) {
                            if (fuck.val < fuck.right.val) {
                                is_bigheap = false;
                                break;
                            }
                            queue3.enqueue(fuck.right.index);
                        }
                    }
                }

                //如果都不是
                if (!is_smallheap && !is_bigheap) judge = false;
            }


            if (judge) {
                output.println("Case #" + (i + 1) + ": " + "YES");
            } else output.println("Case #" + (i + 1) + ": " + "NO");
        }

        output.close();
    }
}

class TreeNode {
    int index;
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode father;
}

class Queueue {
    int rear = 0;
    int front = 0;

    int maxlength = 5000001;

    int[] s = new int[5000001];

    public void enqueue(int val) {
        if (rear == maxlength) {
            //System.out.println("Queue is full!");
            return;
        }
        s[rear] = val;
        rear++;
    }

    public int dequeue() {
        int newnode = s[front];
        if (front < rear) {
            s[front] = 0;
            front++;
        }
        return newnode;
    }

    public boolean isEmpty() {
        return front == rear;
    }
}

