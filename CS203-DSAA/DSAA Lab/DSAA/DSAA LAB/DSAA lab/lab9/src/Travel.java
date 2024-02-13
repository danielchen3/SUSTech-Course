import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Travel {


    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int m = input.nextInt();

        City[] node = new City[n + 1];

        for (int i = 1; i <= n; i++) node[i] = new City();

        Heap heap = new Heap(n);

        for (int i = 0; i < m; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            int w = input.nextInt();
            node[u].next.add(node[v]);
            node[u].distance.add((long) w);
        }

        node[1].dis = 0;
        heap.insert(node[1]);

        while (!heap.isEmpty()) {
            City mini = heap.delete();
            if (mini.isvisited) continue;
            mini.isvisited = true;
            for (int i = 0; i < mini.next.size(); i++) {
                City cur = mini.next.get(i);
                if (!cur.isvisited) {
                    if (cur.dis > mini.dis + mini.distance.get(i)){
                        cur.dis = mini.dis + mini.distance.get(i);
                        heap.revise(cur.index);
                    }
                    heap.insert(cur);
                }
            }
        }

        if (node[n].dis == Long.MAX_VALUE) System.out.println(-1);

        else System.out.println(node[n].dis);

        System.out.close();
    }


}


class City {

    int index;

    ArrayList<City> next = new ArrayList<>();

    ArrayList<Long> distance = new ArrayList<>();

    long dis = Long.MAX_VALUE;

    boolean isvisited = false;
}

class Heap {
    City[] cities;
    int size;

    Heap(int n) {
        this.cities = new City[n + 1];

        for (int i = 0; i <= n; i++){
            this.cities[i] = new City();
        }

        this.size = 0;
    }


    public void insert(City city) {
        size++;
        this.cities[size] = city;
        city.index = size;
        int temp = size;
        while (temp != 1 && cities[temp / 2].dis > cities[temp].dis) {
            swap(temp / 2, temp);
            temp /= 2;
        }
    }

    public void revise(int index_in_heap) {
        int cur = index_in_heap;
        while (cur > 1 && cities[cur / 2].dis > this.cities[cur].dis) {
            swap(cur, cur / 2);
            cur = cur / 2;
        }
    }

    public void swap(int i, int j) {
        City tt = cities[i];
        cities[i] = cities[j];
        cities[j] = tt;
        cities[j].index = j;
        cities[i].index = i;
    }


    public void adjust() {
        if (size == 0) return;
        int start = 1;
        while (true) {
            int temp = start;
            if (temp * 2 + 1 <= size) {
                if (cities[temp].dis >= cities[temp * 2 + 1].dis && cities[temp * 2].dis >= cities[temp * 2 + 1].dis)
                    temp = temp * 2 + 1;
                else if (cities[temp].dis >= cities[temp * 2].dis && cities[temp * 2 + 1].dis >= cities[temp * 2].dis)
                    temp = temp * 2;
            } else if (temp * 2 <= size) {
                if (cities[temp].dis > cities[temp * 2].dis) temp = temp * 2;
            }
            if (temp == start) break;
            swap(temp, start);
            start = temp;
        }
    }

    public City delete() {
        if (size == 0) return null;
        City ans = cities[1];
        cities[1] = cities[size];
        cities[1].index = 1;
        size--;
        adjust();
        return ans;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
