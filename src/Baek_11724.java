import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_11724 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int count = 0;
        boolean[][] matrix = new boolean[N + 1][N + 1];
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            matrix[start][end] = true;
            matrix[end][start] = true;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                count++;
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int target = queue.poll();
                    if (!visited[target]) {
                        visited[target] = true;
                        for (int j = 1; j <= N; j++) {
                            if (matrix[target][j] && !visited[j]) {
                                queue.offer(j);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}
