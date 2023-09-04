import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_14442 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[n][m];
        boolean[][][] visited = new boolean[n][m][k + 1];
        int[][] dist = new int[n][m];

        if (n - 1 == 0 && m - 1 == 0) {
            System.out.println(1);
            return;
        }

        for (int i = 0; i < n; i++) {
            String input = bf.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = input.charAt(j) == '1';
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        int[] move_row = new int[]{-1, 0, 1, 0};
        int[] move_col = new int[]{0, 1, 0, -1};

        dist[0][0] = 1;
        visited[0][0][0] = true;
        queue.offer(new int[]{0, 0, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = cur[0] + move_row[i];
                int nextCol = cur[1] + move_col[i];

                if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
                    if (map[nextRow][nextCol]) {
                        if (cur[2] < k && !visited[nextRow][nextCol][cur[2]]) {
                            visited[nextRow][nextCol][cur[2]] = true;
                            dist[nextRow][nextCol] = dist[cur[0]][cur[1]] + 1;
                            queue.offer(new int[]{nextRow, nextCol, cur[2] + 1});
                        }
                    } else {
                        if (!visited[nextRow][nextCol][cur[2]]) {
                            visited[nextRow][nextCol][cur[2]] = true;
                            dist[nextRow][nextCol] = dist[cur[0]][cur[1]] + 1;
                            queue.offer(new int[]{nextRow, nextCol, cur[2]});
                        }
                    }

                    if (nextRow == n - 1 && nextCol == m - 1) {
                        System.out.println(dist[nextRow][nextCol]);
                        return;
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
