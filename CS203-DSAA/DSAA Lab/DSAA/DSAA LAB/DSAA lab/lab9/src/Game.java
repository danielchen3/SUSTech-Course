import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Game {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int n = input.nextInt();
        int m = input.nextInt();
        int[] grid = new int[n * m + 2];
        Suck[] games = new Suck[100001];

        for (int i = 1; i <= n * m + 2; i++) games[i] = new Suck();

        for (int i = 1; i <= n * m; i++)
            grid[i] = input.nextInt();

        Heapp heap = new Heapp(100001);

        for (int i = 1; i <= n * m; i++) {
            if (i <= m && i != m) {
                games[i].next.add(games[i + 1]);
                games[i + 1].next.add(games[i]);
                games[i].distance.add((long) grid[i + 1] * (long) grid[i]);
                games[i + 1].distance.add((long) grid[i + 1] * (long) grid[i]);
            } else if (i - m >= 1 && i % m != 0) {
                games[i].next.add(games[i + 1]);
                games[i + 1].next.add(games[i]);
                games[i].distance.add((long) grid[i + 1] * (long) grid[i]);
                games[i + 1].distance.add((long) grid[i + 1] * (long) grid[i]);
                games[i].next.add(games[i - m]);
                games[i - m].next.add(games[i]);
                games[i].distance.add((long) grid[i] * (long) grid[i - m]);
                games[i - m].distance.add((long) grid[i - m] * (long) grid[i]);
            } else if (i - m >= 1) {
                games[i].next.add(games[i - m]);
                games[i - m].next.add(games[i]);
                games[i].distance.add((long) grid[i] * (long) grid[i - m]);
                games[i - m].distance.add((long) grid[i - m] * (long) grid[i]);
            }
        }

        long ans = 0;
        games[1].extend_road = 0;
        heap.insert(games[1]);


        while (!heap.isEmpty()) {
            Suck now = heap.delete();
            ans = ans + now.extend_road;
            now.isvisited = true;
            for (int i = 0; i < now.next.size(); i++) {
                if (!now.next.get(i).isvisited) {
                    //如果在堆中的话，就看是否更新
                    if (now.next.get(i).index != 0) {
                        if (heap.chess[now.next.get(i).index].extend_road == -Long.MAX_VALUE) {
                            heap.chess[now.next.get(i).index].extend_road = now.distance.get(i);
                            heap.insert(now.next.get(i));
                        } else if (heap.chess[now.next.get(i).index].extend_road < now.distance.get(i)) {
                            heap.chess[now.next.get(i).index].extend_road = now.distance.get(i);
                            heap.revise(now.next.get(i).index);
                        }
                    } else {
                        heap.insert(now.next.get(i));
                        heap.chess[heap.size].extend_road = now.distance.get(i);
                        heap.revise(now.next.get(i).index);
                    }
                }
            }
        }

        output.println(ans);
        output.close();
    }

    static class Heapp {
        Suck[] chess;
        int size;

        Heapp(int n) {
            this.chess = new Suck[n + 1];

            for (int i = 0; i <= n; i++) {
                this.chess[i] = new Suck();
            }

            this.size = 0;
        }


        public void insert(Suck city) {
            size++;
            this.chess[size] = city;
            city.index = size;
            int temp = size;
            while (temp != 1 && chess[temp / 2].extend_road < chess[temp].extend_road) {
                swap(temp / 2, temp);
                temp /= 2;
            }
        }

        public void revise(int index_in_heap) {
            int cur = index_in_heap;
            while (cur > 1 && chess[cur / 2].extend_road < chess[cur].extend_road) {
                swap(cur, cur / 2);
                cur = cur / 2;
            }
        }

        public void swap(int i, int j) {
            Suck tt = chess[i];
            chess[i] = chess[j];
            chess[j] = tt;
            chess[j].index = j;
            chess[i].index = i;
        }


        public void adjust() {
            if (size == 0) return;
            int start = 1;
            while (true) {
                int temp = start;
                if (temp * 2 + 1 <= size) {
                    if (chess[temp].extend_road <= chess[temp * 2 + 1].extend_road && chess[temp * 2].extend_road <= chess[temp * 2 + 1].extend_road)
                        temp = temp * 2 + 1;
                    else if (chess[temp].extend_road <= chess[temp * 2].extend_road && chess[temp * 2 + 1].extend_road <= chess[temp * 2].extend_road)
                        temp = temp * 2;
                } else if (temp * 2 <= size) {
                    if (chess[temp].extend_road < chess[temp * 2].extend_road) temp = temp * 2;
                }
                if (temp == start) break;
                swap(temp, start);
                start = temp;
            }
        }

        public Suck delete() {
            if (size == 0) return null;
            Suck ans = chess[1];
            chess[1] = chess[size];
            chess[1].index = 1;
            chess[size] = new Suck();
            size--;
            adjust();
            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }
}

class Suck {

    int index;

    ArrayList<Suck> next = new ArrayList<>();

    ArrayList<Long> distance = new ArrayList<>();

    long extend_road = -Long.MAX_VALUE;

    boolean isvisited = false;

}


