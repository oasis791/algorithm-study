import java.io.*;
import java.util.*;

public class Goorm_14 {
    static int count = 0;
    static int lastNode = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        boolean[] visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        dfs(k, visited, graph);

        System.out.println(count + " " + lastNode);
    }

    static void dfs(int node, boolean[] visited, List<Integer>[] graph) {
        visited[node] = true;
        count++;
        lastNode = node;

        Collections.sort(graph[node]);

        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next, visited, graph);
                break;
            }
        }
    }
}