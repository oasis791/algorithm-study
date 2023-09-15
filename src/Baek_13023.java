import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baek_13023 {
    static int n, m;
    static List<Integer>[] relation;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        relation = new List[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            relation[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            relation[v1].add(v2);
            relation[v2].add(v1);
        }

        for (int i = 0; i < n; i++) {
            dfs(i, 1);
        }
        System.out.println(0);
    }

    static void dfs(int node, int depth) {
        if (depth == 5) {
            System.out.println(1);
            System.exit(0);
        }

        visited[node] = true;
        for (int v : relation[node]) {
            if (!visited[v]) {
                dfs(v, depth + 1);
            }
        }
        visited[node] = false;
    }
}