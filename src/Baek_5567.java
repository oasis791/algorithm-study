import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_5567 {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        visited[1] = true;
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1, 0);
        int answer = 0;
        for (int i = 2; i <= n; i++) {
            if (visited[i]) answer++;
        }

        System.out.println(answer);
    }

    static void dfs(int node, int depth) {
        if (depth == 2) {
            return;
        }

        for (int i = 0; i < graph[node].size(); i++) {
            int temp = graph[node].get(i);
            visited[temp] = true;
            dfs(temp, depth + 1);
        }
    }
}
