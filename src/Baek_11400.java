import java.io.*;
import java.util.*;

public class Baek_11400 {
    static int V, E;
    static ArrayList<Integer>[] graph;
    static int[] discover;
    static int depth;
    static PriorityQueue<int[]> answer = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[0] > o2[0]) {
                return 1;
            } else if (o1[0] < o2[0]) {
                return -1;
            } else {
                if (o1[1] > o2[1]) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    });
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        int start, end;
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(bf.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }

        discover = new int[V + 1];

        depth = 1;
        for (int i = 1; i <= V; i++) {
            if (discover[i] == 0) {
                dfs(i, 0);
            }
        }
        bw.write(answer.size() + "\n");
        while (!answer.isEmpty()) {
            int[] poll = answer.poll();
            bw.write(poll[0] + " " + poll[1] + "\n");
        }
        bw.flush();
        bw.close();
        bf.close();
    }

    static int dfs(int node, int parent) {
        discover[node] = depth++;
        int fastest = discover[node];

        for (int i = 0; i < graph[node].size(); i++) {
            int nextNode = graph[node].get(i);

            if(nextNode == parent) continue;

            if (discover[nextNode] == 0) {
                int childFastest = dfs(nextNode, node);
                if (childFastest > discover[node]) {
                    if (node < nextNode) {
                        answer.add(new int[]{node, nextNode});
                    } else {
                        answer.add(new int[]{nextNode, node});
                    }
                }
                fastest = Math.min(fastest, childFastest);
            } else {
                fastest = Math.min(fastest, discover[nextNode]);
            }
        }

        return fastest;
    }
}
