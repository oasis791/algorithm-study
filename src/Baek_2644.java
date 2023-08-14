import java.io.*;
import java.util.*;

public class Baek_2644 {
    static int n, t1, t2;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        t1 = Integer.parseInt(st.nextToken());
        t2 = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(bf.readLine());

        map = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            // 자식은 1, 부모는 2
            map[parent][child] = 1;
            map[child][parent] = 1;
        }

        boolean[] visited = new boolean[n + 1];
        visited[t1] = true;
        dfs(t1, visited, 0);

        if(answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    static void dfs (int node, boolean[] visited, int depth) {
        if(node == t2) {
            answer = Math.min(answer, depth);
            return;
        }

        for(int i = 1; i <= n; i++) {
            if(map[node][i] == 1 && !visited[i]) {
                visited[node] = true;
                dfs(i, visited, depth + 1);
                visited[node] = false;
            }
        }
    }
}
