public class DoubleLinkedList {
    public static void main(String[] args) {
        DoubleToysDemo toysDemo = new DoubleToysDemo();
        DoubleToys toy1 = new DoubleToys(1, 01, "blue");
        DoubleToys toy2 = new DoubleToys(2, 02, "red");
        DoubleToys toy3 = new DoubleToys(3, 03, "yellow");
        DoubleToys toy4 = new DoubleToys(4, 04, "brown");
        DoubleToys toy5 = new DoubleToys(5, 05, "green");
        toysDemo.addToys(toy1);
        toysDemo.addToys(toy2);
        toysDemo.addToys(toy3);
        toysDemo.addToys(toy4);
        toysDemo.addToys(toy5);
        toysDemo.showToysLink(toysDemo.getHead());
        toysDemo.showToysLink2(toysDemo.getTail());
        toysDemo.update(new DoubleToys(6, 06, "purple"));
        toysDemo.update(new DoubleToys(3, 03, "purple"));
        toysDemo.showToysLink(toysDemo.getHead());
        toysDemo.delete(3);
        toysDemo.delete(4);
        toysDemo.showToysLink(toysDemo.getHead());
        toysDemo.delete(5);
        toysDemo.showToysLink(toysDemo.getHead());
    }
}


class DoubleToysDemo {
    protected int size = 0;
    protected DoubleToys head = new DoubleToys(0, 0, "");

    protected DoubleToys tail = new DoubleToys(100, 100, "");


    public DoubleToys getHead() {
        return head;
    }

    public DoubleToys getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    public void addToys(DoubleToys toy) {
        DoubleToys temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = toy;
        toy.prev = temp;
        tail.prev = toy;
        size++;
    }

    public void showToysLink(DoubleToys toyshead) {
        if (toyshead.next == null) {
            System.out.println("链表为空");
            return;
        }
        DoubleToys temp = toyshead;
        while (true) {
            if (temp.next == null) {
                System.out.println(size);
                break;
            }
            System.out.println(temp.next);
            temp = temp.next;
        }
    }

    public void showToysLink2(DoubleToys toystail) {
        if (toystail.prev == null) {
            System.out.println("链表为空");
            return;
        }
        DoubleToys temp = toystail;
        while (true) {
            if (temp.prev == null) {
                System.out.println(size);
                break;
            }
            System.out.println(temp.prev);
            temp = temp.prev;
        }
    }

    public void update(DoubleToys toy) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        DoubleToys temp = head;
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

    public void delete(int index) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        boolean flag = false;
        if (index < size / 2) {
            DoubleToys temp = head;
            while (temp.next != null) {
                if (temp.next.index == index) {
                    temp.next = temp.next.next;
                    if (temp.next != null)
                        temp.next.prev = temp;
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
        } else {
            DoubleToys temp = tail;
            while (temp.prev != null) {
                if (temp.prev.index == index) {
                    temp.prev = temp.prev.prev;
                    if (temp.prev.next != null)
                        temp.prev.next = temp;
                    flag = true;
                    break;
                }
                temp = temp.prev;
            }
        }
        if (flag) {
            size--;
            System.out.println("已成功删除！");
        } else System.out.println("未找到该节点！");
    }
}

class DoubleToys {

    public String color;
    public int id;
    public int index;
    public DoubleToys next;
    public DoubleToys prev;

    public DoubleToys(int index, int id, String color) {
        super();
        this.index = index;
        this.id = id;
        this.color = color;
    }

    @Override
    public String toString() {
        return "DoubleToys{" +
                "color='" + color + '\'' +
                ", id=" + id +
                ", index=" + index +
                '}';
    }
}


