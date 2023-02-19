import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_11725 {
    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static int[] answer;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        answer = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        dfs(0, 1);
        for (int i = 2; i <= N; i++) {
            System.out.println(answer[i]);
        }
    }

    static void dfs(int parent, int target) {
        visited[target] = true;
        answer[target] = parent;

        for (int i = 0; i < graph[target].size(); i++) {
            Integer next = graph[target].get(i);
            if (!visited[next]) {
                dfs(target, next);
            }
        }
    }
}