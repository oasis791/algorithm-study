//1260번 DFS와 BFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_1260 {
    private static int N, M, V;
    private static ArrayList<Integer>[] vertex;
    private static boolean[] visited;
    private static Queue<Integer> queue = new LinkedList<>();
    private static StringBuilder sb = new StringBuilder();
    private static void dfs(int node) {
        if (!visited[node]) {
            visited[node] = true;
            sb.append(node).append(" ");
            for (int i = 0; i < vertex[node].size(); i++) {
                if(!visited[vertex[node].get(i)])
                    dfs(vertex[node].get(i));
            }
        }
    }
    private static void bfs() {
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            if (!visited[temp]) {
                visited[temp] = true;
                sb.append(temp).append(" ");
                for (int i = 0; i < vertex[temp].size(); i++) {
                    if (!visited[vertex[temp].get(i)]) {
                        queue.offer(vertex[temp].get(i));
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        vertex = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            vertex[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int input1 = Integer.parseInt(st.nextToken());
            int input2 = Integer.parseInt(st.nextToken());
            vertex[input1].add(input2);
            vertex[input2].add(input1);
        }
        for (int i = 0; i < N + 1; i++) {
            Collections.sort(vertex[i]);
        }
        dfs(V);
        System.out.println(sb);
        sb.delete(0, sb.length());
        visited = new boolean[N + 1];
        queue.offer(V);
        bfs();
        System.out.println(sb);
    }
}
