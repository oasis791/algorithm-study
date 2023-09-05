import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Goorm_17 {
    static int n, m, nodeCount, edgeCount;
    static double density = 0;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

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

        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            List<Integer> nodes = new ArrayList<>();
            nodeCount = 0;
            edgeCount = 0;
            bfs(i, nodes);

            double temp = (double) edgeCount / nodeCount;

            if (temp > density) {
                density = temp;
                answer = new ArrayList<>();
                answer.addAll(nodes);
            } else if (temp == density) {
                if (answer.size() > nodes.size()) {
                    answer = new ArrayList<>();
                    answer.addAll(nodes);
                }
            }
        }

        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();
        for (int i : answer) {
            sb.append(i).append(" ");
        }
        System.out.print(sb);
    }

    static void bfs(int node, List<Integer> nodes) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if(visited[cur])
                continue;
            nodeCount++;
            visited[cur] = true;
            nodes.add(cur);

            for (int i = 0; i < graph[cur].size(); i++) {
                int next = graph[cur].get(i);
                if (!visited[next]) {
                    edgeCount++;
                    queue.offer(next);
                }
            }
        }
    }
}

// 1 - 3 - 5
//  \  |
//     7
// 2 - 4 - 6