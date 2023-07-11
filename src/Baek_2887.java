import java.io.*;
import java.util.*;

public class Baek_2887 {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        parents = new int[n];
        for (int i = 1; i < n; i++) {
            parents[i] = i;
        }
        int[][] planets = new int[n][4];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            planets[i][0] = Integer.parseInt(st.nextToken());
            planets[i][1] = Integer.parseInt(st.nextToken());
            planets[i][2] = Integer.parseInt(st.nextToken());
            planets[i][3] = i;
        }

        Arrays.sort(planets, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < n - 1; i++) {
            pq.offer(new int[]{planets[i][3], planets[i + 1][3], Math.abs(planets[i][0] - planets[i + 1][0])});
        }


        Arrays.sort(planets, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        for (int i = 0; i < n - 1; i++) {
            pq.offer(new int[]{planets[i][3], planets[i + 1][3], Math.abs(planets[i][1] - planets[i + 1][1])});
        }

        Arrays.sort(planets, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        for (int i = 0; i < n - 1; i++) {
            pq.offer(new int[]{planets[i][3], planets[i + 1][3], Math.abs(planets[i][2] - planets[i + 1][2])});
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int p1 = poll[0];
            int p2 = poll[1];
            int dist = poll[2];

            if (find(p1) == find(p2)) {
                continue;
            }

            answer += dist;
            union(p1, p2);
        }

        System.out.println(answer);
    }

    static int find(int x) {
        if (parents[x] == x) {
            return x;
        } else {
            return parents[x] = find(parents[x]);
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
