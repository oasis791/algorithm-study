import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        int[] time = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indegrees = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int from = Integer.parseInt(st.nextToken());
                if(from == -1) {
                    break;
                }

                graph[from].add(i);
                indegrees[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int[] answer = new int[n + 1];
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            answer[cur] += time[cur];

            for (int i = 0; i < graph[cur].size(); i++) {
                int next = graph[cur].get(i);
                indegrees[next]--;
                answer[next] = Math.max(answer[next], answer[cur]);
                if (indegrees[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            sb.append(answer[i]).append("\n");
        }

        System.out.print(sb);
    }
}
