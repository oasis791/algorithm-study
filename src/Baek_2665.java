import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_2665 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        if (n - 1 == 0) {
            System.out.println(0);
            return;
        }

        boolean[][] map = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String input = bf.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = input.charAt(j) == '1';
            }
        }

        int[] move_row = new int[]{-1, 0, 1, 0};
        int[] move_col = new int[]{0, 1, 0, -1};

        Queue<int[]> queue = new LinkedList<>();
        int[][] visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        visited[0][0] = 0;
        queue.offer(new int[]{0, 0, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = cur[0] + move_row[i];
                int nextCol = cur[1] + move_col[i];

                if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
                    if (!map[nextRow][nextCol]) {
                        if (visited[nextRow][nextCol] > cur[2] + 1) {
                            visited[nextRow][nextCol] = cur[2] + 1;
                            queue.offer(new int[]{nextRow, nextCol, cur[2] + 1});
                        }
                    } else {
                        if (visited[nextRow][nextCol] > cur[2]) {
                            visited[nextRow][nextCol] = cur[2];
                            queue.offer(new int[]{nextRow, nextCol, cur[2]});
                        }
                    }
                }
            }
        }

        System.out.println(visited[n - 1][n - 1]);
    }
}