import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sign {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        Chess[] chess = new Chess[1000001];
        int n = input.nextInt();
        int m = input.nextInt();

        for (int i = 1; i <= n; i++) chess[i] = new Chess();

        long minn = Long.MAX_VALUE;
        int uu = 0;
        int vv = 0;
        long tot = 0;

        Heapp heap = new Heapp(1000001);
        for (int i = 0; i < m; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            int w = input.nextInt();

            chess[u].next.add(chess[v]);
            chess[v].next.add(chess[u]);
            chess[u].distance.add((long) w);
            chess[v].distance.add((long) w);

            if (w < minn) {
                minn = w;
                uu = u;
                vv = v;
            }

            if (w > 0)
                tot = tot + (long) w;

        }

        chess[uu].extend_road = 0;
        heap.insert(chess[uu]);


        long ans = 0;

        long fake = 0;

        while (!heap.isEmpty()) {
            Chess now = heap.delete();
            if (now.extend_road > 0) tot = tot - now.extend_road;
            //ans = ans + now.extend_road;
            now.isvisited = true;
            for (int i = 0; i < now.next.size(); i++) {
                if (!now.next.get(i).isvisited) {
                    //如果在堆中的话，就看是否更新
                    if (now.next.get(i).index != 0) {
                        if (heap.chess[now.next.get(i).index].extend_road == Long.MAX_VALUE) {
                            heap.chess[now.next.get(i).index].extend_road = now.distance.get(i);
                            heap.insert(now.next.get(i));
                        } else if (heap.chess[now.next.get(i).index].extend_road > now.distance.get(i)) {
//                            if (heap.chess[now.next.get(i).index].extend_road > 0)
//                                fake = fake + heap.chess[now.next.get(i).index].extend_road;
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

        output.println(tot);

        //output.println(ans);

        output.close();
    }


    static class Heapp {
        Chess[] chess;
        int size;

        Heapp(int n) {
            this.chess = new Chess[n + 1];

            for (int i = 0; i <= n; i++) {
                this.chess[i] = new Chess();
            }

            this.size = 0;
        }


        public void insert(Chess city) {
            size++;
            this.chess[size] = city;
            city.index = size;
            int temp = size;
            while (temp != 1 && chess[temp / 2].extend_road > chess[temp].extend_road) {
                swap(temp / 2, temp);
                temp /= 2;
            }
        }

        public void revise(int index_in_heap) {
            int cur = index_in_heap;
            while (cur > 1 && chess[cur / 2].extend_road > chess[cur].extend_road) {
                swap(cur, cur / 2);
                cur = cur / 2;
            }
        }

        public void swap(int i, int j) {
            Chess tt = chess[i];
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
                    if (chess[temp].extend_road >= chess[temp * 2 + 1].extend_road && chess[temp * 2].extend_road >= chess[temp * 2 + 1].extend_road)
                        temp = temp * 2 + 1;
                    else if (chess[temp].extend_road >= chess[temp * 2].extend_road && chess[temp * 2 + 1].extend_road >= chess[temp * 2].extend_road)
                        temp = temp * 2;
                } else if (temp * 2 <= size) {
                    if (chess[temp].extend_road > chess[temp * 2].extend_road) temp = temp * 2;
                }
                if (temp == start) break;
                swap(temp, start);
                start = temp;
            }
        }

        public Chess delete() {
            if (size == 0) return null;
            Chess ans = chess[1];
            chess[1] = chess[size];
            chess[1].index = 1;
            chess[size] = new Chess();
            size--;
            adjust();
            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }
}

class Chess {

    int index;

    ArrayList<Chess> next = new ArrayList<>();

    ArrayList<Long> distance = new ArrayList<>();

    long extend_road = Long.MAX_VALUE;

    boolean isvisited = false;



}


