import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_17835 {
    static int n, m, k;
    static List<int[]>[] graph;
    static int answer = 0;
    static long answerDist = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        graph = new List[n + 1];
        PriorityQueue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                return (int) (o1[1] - o2[1]);
            }
        });

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[v].add(new int[]{u, c});
        }

        long[] dist = new long[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < k; i++) {
            int temp = Integer.parseInt(st.nextToken());
            pq.offer(new long[]{temp, 0});
            dist[temp] = 0;
        }

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            if (visited[(int) cur[0]])
                continue;

            visited[(int) cur[0]] = true;

            for (int i = 0; i < graph[(int) cur[0]].size(); i++) {
                int[] next = graph[(int) cur[0]].get(i);
                if (!visited[next[0]] && dist[next[0]] > dist[(int) cur[0]] + next[1]) {
                    dist[next[0]] = dist[(int) cur[0]] + next[1];
                    pq.offer(new long[]{next[0], dist[next[0]]});
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (dist[i] > answerDist) {
                answer = i;
                answerDist = dist[i];
            }
        }

        System.out.println(answer);
        System.out.println(answerDist);
    }
}