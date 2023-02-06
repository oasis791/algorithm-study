import java.io.*;
import java.util.*;

public class Baek_11266 {

    static int V, E, depth, ans;
    static ArrayList<Integer>[] graph;
    static int[] discover;
    static boolean[] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        int start, end;
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }

        // 2. visit 배열, 단절점 여부 초기화 후 V 모두 DFS
        discover = new int[V + 1];
        check = new boolean[V + 1];

        depth = 1;
        for (int i = 1; i <= V; i++) {
            if (discover[i] == 0) {
                dfs(i, true, 0);
            }
        }

        // 3. 단절점 개수 count 및 출력
        ans = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (check[i]) {
                ans++;
                sb.append(i + " ");
            }
        }
        bw.write(ans + "\n" + sb);
        bw.flush();
        bw.close();
        br.close();
    }

    static int dfs(int node, boolean isRoot, int parent) {
        discover[node] = depth++;

        int fastest = discover[node];
        int child = 0;

        // 2. 자식 DFS
        for (int i = 0; i < graph[node].size(); i++) {
            int nextNode = graph[node].get(i);
            // 왔던길 돌아가기 방지
            if (nextNode == parent) continue;

            if (discover[nextNode] == 0) {
                child++;
                // 자식 정점 중 방문순서가 빠른 값
                int childFastest = dfs(nextNode, false, node);
                if (!isRoot && childFastest >= discover[node]) {
                    check[node] = true;
                }
                fastest = Math.min(fastest, childFastest);
            } else {
                fastest = Math.min(fastest, discover[nextNode]);
            }
        }

        // 3. 루트 정점인 경우 자식 개수가 2개 이상이면 단절점
        if (isRoot && child >= 2) {
            check[node] = true;
        }
        return fastest;
    }
}