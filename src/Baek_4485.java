import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_4485 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] move_row = new int[]{-1, 0, 1, 0};
        int[] move_col = new int[]{0, 1, 0, -1};
        int tc = 1;

        while (true) {
            int n = Integer.parseInt(bf.readLine());
            if (n == 0)
                break;

            int[][] map = new int[n][n];
            int[][] dist = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                Arrays.fill(dist[i], Integer.MAX_VALUE);
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Queue<int[]> queue = new LinkedList<>();
            dist[0][0] = map[0][0];
            queue.offer(new int[]{0, 0});

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nextRow = cur[0] + move_row[i];
                    int nextCol = cur[1] + move_col[i];

                    if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
                        if (dist[nextRow][nextCol] > dist[cur[0]][cur[1]] + map[nextRow][nextCol]) {
                            dist[nextRow][nextCol] = dist[cur[0]][cur[1]] + map[nextRow][nextCol];
                            queue.offer(new int[]{nextRow, nextCol});
                        }
                    }
                }
            }

            sb.append("Problem ").append(tc++).append(": ").append(dist[n - 1][n - 1]).append("\n");
        }

        System.out.print(sb);
    }
}
