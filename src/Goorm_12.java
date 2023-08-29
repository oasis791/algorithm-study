import java.io.*;
import java.util.*;

public class Goorm_12 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] map = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        ArrayList<int[]> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    list.add(new int[]{i, j});
                }
            }
        }

        int[] move_row = new int[]{-1, 0, 1, 0};
        int[] move_col = new int[]{0, 1, 0, -1};
        int answer = 0;

        for (int[] point : list) {
            if (visited[point[0]][point[1]]) {
                continue;
            }

            visited[point[0]][point[1]] = true;
            answer++;
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{point[0], point[1]});

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nextRow = cur[0] + move_row[i];
                    int nextCol = cur[1] + move_col[i];

                    if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
                        if (!visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
                            visited[nextRow][nextCol] = true;
                            queue.offer(new int[]{nextRow, nextCol});
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }
}