import java.io.*;
import java.util.*;

public class Baek_1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        int[] indegrees = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);

            indegrees[to]++;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 1; i <= n; i++) {
            if(indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now).append(" ");

            int size = graph[now].size();

            for(int i = 0; i < size; i++) {
                int next = graph[now].get(0);
                indegrees[next]--;

                if(indegrees[next] == 0) {
                    queue.offer(next);
                }
                graph[now].remove(0);
            }
        }

        System.out.println(sb);
    }
}
