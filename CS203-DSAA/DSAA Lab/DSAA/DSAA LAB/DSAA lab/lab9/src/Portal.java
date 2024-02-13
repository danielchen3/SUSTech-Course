import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Portal {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int n = input.nextInt();
        int m = input.nextInt();
        int p = input.nextInt();
        int k = input.nextInt();

        Country[][] country = new Country[n + 1][k + 2];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k + 1; j++) {
                country[i][j] = new Country();
            }
        }

        Heap heap = new Heap(100001);

        for (int i = 0; i < m; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            int w = input.nextInt();
            for (int j = 0; j <= k; j++) {
                country[u][j].next.add(country[v][j]);
                country[u][j].distance.add((long) w);
            }
        }

        for (int i = 0; i < p; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            for (int j = 0; j < k; j++) {
                country[u][j].next.add(country[v][j + 1]);
                country[u][j].distance.add((long) 0);
            }
        }

        int S = input.nextInt();
        int T = input.nextInt();

        country[S][0].dis = 0;
        heap.insert(country[S][0]);

        while (!heap.isEmpty()) {
            Country mini = heap.delete();
            if (mini.isvisited) continue;
            mini.isvisited = true;
            for (int i = 0; i < mini.next.size(); i++) {
                Country cur = mini.next.get(i);
                if (!cur.isvisited) {
                    if (cur.dis > mini.dis + mini.distance.get(i)) {
                        cur.dis = mini.dis + mini.distance.get(i);
                        heap.revise(cur.index);
                    }
                    heap.insert(cur);
                }
            }
        }

        long ans = Long.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            ans = Math.min(ans, country[T][i].dis);
        }

        output.println(ans);
        output.close();
    }

    static class Heap {
        Country[] countries;
        int size;

        Heap(int n) {
            this.countries = new Country[n + 1];

            for (int i = 0; i <= n; i++) {
                this.countries[i] = new Country();
            }

            this.size = 0;
        }


        public void insert(Country city) {
            size++;
            this.countries[size] = city;
            city.index = size;
            int temp = size;
            while (temp != 1 && countries[temp / 2].dis > countries[temp].dis) {
                swap(temp / 2, temp);
                temp /= 2;
            }
        }

        public void revise(int index_in_heap) {
            int cur = index_in_heap;
            while (cur > 1 && countries[cur / 2].dis > this.countries[cur].dis) {
                swap(cur, cur / 2);
                cur = cur / 2;
            }
        }

        public void swap(int i, int j) {
            Country tt = countries[i];
            countries[i] = countries[j];
            countries[j] = tt;
            countries[j].index = j;
            countries[i].index = i;
        }


        public void adjust() {
            if (size == 0) return;
            int start = 1;
            while (true) {
                int temp = start;
                if (temp * 2 + 1 <= size) {
                    if (countries[temp].dis >= countries[temp * 2 + 1].dis && countries[temp * 2].dis >= countries[temp * 2 + 1].dis)
                        temp = temp * 2 + 1;
                    else if (countries[temp].dis >= countries[temp * 2].dis && countries[temp * 2 + 1].dis >= countries[temp * 2].dis)
                        temp = temp * 2;
                } else if (temp * 2 <= size) {
                    if (countries[temp].dis > countries[temp * 2].dis) temp = temp * 2;
                }
                if (temp == start) break;
                swap(temp, start);
                start = temp;
            }
        }

        public Country delete() {
            if (size == 0) return null;
            Country ans = countries[1];
            countries[1] = countries[size];
            countries[1].index = 1;
            size--;
            adjust();
            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }
}

class Country {

    int index;

    ArrayList<Country> next = new ArrayList<>();

    ArrayList<Long> distance = new ArrayList<>();

    long dis = Long.MAX_VALUE;

    boolean isvisited = false;

    int use = 0;

}
