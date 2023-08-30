import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_14567 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Integer>[] grpah = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            grpah[i] = new ArrayList<>();
        }
        int[] indegrees = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            grpah[v1].add(v2);
            indegrees[v2]++;
        }

        int[] answer = new int[n + 1];

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
                answer[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i : grpah[cur]) {
                indegrees[i]--;
                answer[i] = Math.max(answer[i], answer[cur] + 1);

                if (indegrees[i] == 0) {
                    queue.offer(i);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }
}
