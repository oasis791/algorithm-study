import java.util.*;
import java.io.*;

public class Baek_1005 {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());

        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] time = new int[n + 1];
            int[] indegrees = new int[n + 1];
            int[] dp = new int[n + 1];

            Queue<Integer> queue = new LinkedList<>();
            ArrayList<Integer>[] graph = new ArrayList[n + 1];

            // 그래프 초기화
            for(int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            // 시간 세팅
            st = new StringTokenizer(bf.readLine());
            for(int i = 1; i <= n; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            // 그래프 세팅
            for(int i = 0 ; i < k; i++) {
                st = new StringTokenizer(bf.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                indegrees[to]++;
                graph[from].add(to);
            }

            for(int i = 1; i <= n; i++) {
                if(indegrees[i] == 0) {
                    queue.offer(i);
                    dp[i] = time[i];
                }
            }

            while(!queue.isEmpty()) {
                int now = queue.poll();
                int size = graph[now].size();
                for(int i = 0; i < size; i++) {
                    int node = graph[now].get(i);
                    indegrees[node]--;
                    dp[node] = Math.max(dp[node], dp[now] + time[node]);
                    if(indegrees[node] == 0) {
                        queue.offer(node);
                    }
                }
            }
            sb.append(dp[Integer.parseInt(bf.readLine())]).append("\n");
        }
        System.out.println(sb);
    }
}
