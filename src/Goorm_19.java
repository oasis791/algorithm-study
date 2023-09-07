import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Goorm_19 {
    static ArrayList<Integer>[] graph;
    static int n, m, s, e;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (i == s || i == e) {
                sb.append(-1).append("\n");
                continue;
            }
            sb.append(bfs(s, e, i)).append("\n");
        }

        System.out.print(sb);
    }

    static int bfs(int node, int target, int block) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        visited[node] = true;
        queue.offer(new int[]{node, 1});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == target) {
                return cur[1];
            }

            for (int i = 0; i < graph[cur[0]].size(); i++) {
                int next = graph[cur[0]].get(i);

                if (!visited[next] && next != block) {
                    visited[next] = true;
                    queue.offer(new int[]{next, cur[1] + 1});
                }
            }
        }

        return -1;
    }
}