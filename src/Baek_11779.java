import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_11779 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());
        ArrayList<int[]>[] graph = new ArrayList[n + 1];
        StringBuilder[] route = new StringBuilder[n + 1];
        int[] dist = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            route[i] = new StringBuilder();
            dist[i] = Integer.MAX_VALUE;
        }


        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start].add(new int[]{end, weight});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        pq.offer(new int[]{start, 0});
        dist[start] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(dist[cur[0]] < cur[1])
                continue;

            route[cur[0]].append(cur[0]).append(",");
            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int[] next = graph[cur[0]].get(i);
                if (dist[next[0]] > dist[cur[0]] + next[1]) {
                    dist[next[0]] = dist[cur[0]] + next[1];
                    route[next[0]].delete(0, route[next[0]].length());
                    route[next[0]].append(route[cur[0]]);
                    pq.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dist[end]).append("\n");
        String[] answer = route[end].toString().split(",");
        sb.append(answer.length).append("\n");
        for (String s : answer) {
            sb.append(Integer.parseInt(s)).append(" ");
        }

        System.out.println(sb);
    }
}
