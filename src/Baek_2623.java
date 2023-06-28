import java.util.*;
import java.io.*;

public class Baek_2623 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indegrees = new int[n + 1];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int peoples = Integer.parseInt(st.nextToken());

            int from = 0;
            for(int j = 0; j < peoples; j++) {
                if(from == 0) {
                    from = Integer.parseInt(st.nextToken());
                    continue;
                }

                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
                indegrees[to]++;
                from = to;
            }
        }

        for(int i = 1; i <= n; i++) {
            if(indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        while(!queue.isEmpty()) {
            count++;
            int now = queue.poll();
            sb.append(now).append("\n");

            int size = graph[now].size();
            for(int i = 0; i < size; i++) {
                int node = graph[now].get(0);
                indegrees[node]--;
                if(indegrees[node] == 0) {
                    queue.offer(node);
                }
                graph[now].remove(0);
            }
        }

        if(count != n) {
            System.out.println(0);
        } else {
            System.out.println(sb);
        }
    }
}
