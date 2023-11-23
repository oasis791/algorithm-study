import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Integer[] weights = new Integer[n];
            Queue<int[]> queue = new LinkedList<>();
            st = new StringTokenizer(bf.readLine());

            for (int i = 0; i < n; i++) {
                int temp = Integer.parseInt(st.nextToken());
                queue.offer(new int[]{temp, i});
                weights[i] = temp;
            }

            Arrays.sort(weights, Collections.reverseOrder());
            int maxIndex = 0;
            int count = 0;
            while (!queue.isEmpty()) {
                int[] now = queue.poll();
                if (now[0] == weights[maxIndex]) {
                    maxIndex++;
                    count++;
                    if (now[1] == m) {
                        break;
                    }
                } else {
                    queue.offer(now);
                }
            }
            sb.append(count).append("\n");
        }
        System.out.print(sb);
    }
}
