import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1647 {
    static int N, M;
    static int[] parent;
    static int answer = 0;
    static int max = -10;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[2] - o2[2];
        }
    });

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = makeSet();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{a, b, weight});
        }

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int a = now[0];
            int b = now[1];
            int weight = now[2];

            if (find(a) == find(b))
                continue;

            union(a, b);
            answer += weight;
            max = Math.max(max, weight);
        }
        bw.write(String.valueOf(answer - max));
        bw.flush();
        bw.close();
        br.close();
    }

    static int[] makeSet() {
        int[] set = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            set[i] = i;
        }
        return set;
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }
}