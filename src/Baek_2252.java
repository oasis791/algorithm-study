import java.util.*;
import java.io.*;

public class Baek_2252 {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
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
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            indegrees[to]++;
        }

        for(int i = 1; i <= n; i++) {
            if(indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now).append(" ");

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
        System.out.println(sb.toString());
    }
}
