import java.util.*;
import java.io.*;

public class Baek_1238 {
    static int answer = 0;
    static int n, m, x;
    static ArrayList<int[]>[] graph;
    static int[] distX;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start].add(new int[]{end, weight});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        pq.offer(new int[]{x, 0});
        distX = new int[n + 1];
        Arrays.fill(distX, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n + 1];
        distX[x] = 0;
        dijkstra(distX, visited, pq);

        for (int i = 1; i <= n; i++) {
            if (i == x) {
                continue;
            }

            int[] dist = new int[n + 1];
            visited = new boolean[n + 1];
            pq.clear();
            pq.offer(new int[]{i, 0});
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[i] = 0;
            dijkstra(dist, visited, pq);
            answer = Math.max(answer, dist[x] + distX[i]);
        }
        System.out.println(answer);
    }

    static void dijkstra(int[] dist, boolean[] visited, PriorityQueue<int[]> pq) {

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int index = poll[0];

            if (visited[index]) {
                continue;
            }

            visited[index] = true;

            for (int i = 0; i < graph[index].size(); i++) {
                int[] get = graph[index].get(i);
                int end = get[0];
                int time = get[1];

                if (!visited[end]) {
                    if (dist[end] > dist[index] + time) {
                        dist[end] = dist[index] + time;
                        pq.offer(new int[]{end, dist[end]});
                    }
                }
            }
        }
    }
}
