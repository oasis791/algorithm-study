import java.util.*;
import java.io.*;

public class Baek_1774 {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        PriorityQueue<double[]> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return Double.compare(o1[2], o2[2]);
            }
        });
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n + 1][2];

        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            if (find(first) == find(second))
                continue;

            union(first, second);
        }

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                pq.offer(new double[]{(double) i, (double) j, calcDist(map[i], map[j])});
            }
        }

        double answer = 0;

        while (!pq.isEmpty()) {
            double[] poll = pq.poll();
            int p1 = (int) poll[0];
            int p2 = (int) poll[1];
            double dist = poll[2];

            if (find(p1) == find(p2)) continue;

            answer += dist;
            union(p1, p2);
        }

        System.out.printf("%.2f", answer);
    }

    static double calcDist(int[] p1, int[] p2) {
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
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
