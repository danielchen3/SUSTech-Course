public class SingleLinkedList {
    public static void main(String[] args) {
        ToysDemo toysDemo = new ToysDemo();
        Toys toy1 = new Toys(1, "blue");
        Toys toy2 = new Toys(2, "red");
        Toys toy3 = new Toys(3, "yellow");
        Toys toy4 = new Toys(4, "brown");
        Toys toy5 = new Toys(5, "green");
        toysDemo.addToys(toy1);
        toysDemo.addToys(toy2);
        toysDemo.addToys(toy3);
        toysDemo.addToys(toy4);
        toysDemo.addToys(toy5);
        toysDemo.showToysLink(toysDemo.getHead());
        toysDemo.update(new Toys(6, "purple"));
        toysDemo.update(new Toys(3, "purple"));
        toysDemo.showToysLink(toysDemo.getHead());
        toysDemo.delete(3);
        toysDemo.delete(4);
        toysDemo.showToysLink(toysDemo.getHead());
        toysDemo.delete(5);
        toysDemo.showToysLink(toysDemo.getHead());
    }
}


class ToysDemo {
    protected int size = 0;
    protected Toys head = new Toys(0, "");

    public Toys getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public void addToys(Toys toy) {
        Toys temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = toy;
        size++;
    }

    public void showToysLink(Toys toyshead) {
        if (toyshead.next == null) {
            System.out.println("链表为空");
            return;
        }
        Toys temp = toyshead;
        while (true) {
            if (temp.next == null) {
                System.out.println(size);
                break;
            }
            System.out.println(temp.next);
            temp = temp.next;
        }
    }

    public void update(Toys toy) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        Toys temp = head;
        boolean flag = false;
        while (temp.next != null) {
            if (temp.id == toy.id) {
                temp.color = toy.color;
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) System.out.println("修改已完成");
        else System.out.println("未找到该节点");
    }

    public void delete(int id) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        Toys temp = head;
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.id == id) {
                temp.next = temp.next.next;
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            size--;
            System.out.println("已成功删除！");
        } else System.out.println("未找到该节点！");
    }
}

class Toys {
    public String color;
    public int id;
    public Toys next;

    public Toys(int id, String color) {
        super();
        this.id = id;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Toys{" +
                "color='" + color + '\'' +
                ", id=" + id +
                '}';
    }
}

