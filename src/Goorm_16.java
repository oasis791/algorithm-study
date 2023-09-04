import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Goorm_16 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n + 1][n + 1];
        boolean[] visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph[s][e] = 1;
        }

        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if(!visited[i]) {
                answer++;
                visited[i] = true;
                dfs(i, graph, n, visited);
            }
        }
        System.out.println(answer);
    }

    static void dfs(int node, int[][] graph, int n, boolean[] visited) {
        for (int i = 1; i <= n; i++) {
            if(i == node)
                continue;
            if (graph[node][i] == 1 && !visited[i]) {
                if (graph[i][node] == 1) {
                    visited[i] = true;
                    dfs(i, graph, n, visited);
                }
            }
        }
    }
}
