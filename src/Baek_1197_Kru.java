import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1197_Kru {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        parents = new int[v + 1];

        for (int i = 1; i <= v; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{a, b, c});
        }

        long answer = 0;

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int p1 = poll[0];
            int p2 = poll[1];
            int dist = poll[2];

            if (find(p1) == find(p2)) continue;

            answer += dist;
            union(p1, p2);
        }

        System.out.println(answer);
    }

    static int find(int x) {
        if (parents[x] == x) {
            return x;
        } else {
            return find(parents[x]);
        }
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x > y) {
            parents[x] = y;
        } else {
            parents[y] = x;
        }
    }
}
