import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_13418 {
    static int n, m;
    static int[] parents;
    static int best = 0;
    static int worst = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        PriorityQueue<int[]> bestPq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return -(o1[2] - o2[2]);
            }
        });

        PriorityQueue<int[]> worstPq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        for (int i = 0; i <= m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            bestPq.offer(new int[]{a, b, c});
            worstPq.offer(new int[]{a, b, c});
        }

        int count = 0;
        while (!bestPq.isEmpty()) {
            int[] poll = bestPq.poll();
            int start = poll[0];
            int end = poll[1];
            int slope = poll[2];

            if (find(start) == find(end)) {
                continue;
            }

            if(slope == 0)
                count++;
            union(start, end);
        }

        best = count * count;

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        count = 0;
        while (!worstPq.isEmpty()) {
            int[] poll = worstPq.poll();
            int start = poll[0];
            int end = poll[1];
            int slope = poll[2];

            if (find(start) == find(end)) {
                continue;
            }

            if(slope == 0)
                count++;
            union(start, end);
        }
        worst = count * count;

        System.out.println(worst - best);
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
