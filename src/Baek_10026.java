import java.io.*;
import java.util.*;

public class Baek_10026 {
    static int n;
    static boolean[][] visited;
    static String[][] map;
    static int[][] section;
    static int[] move_row = new int[]{-1, 0, 1, 0};
    static int[] move_col = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(bf.readLine());
        map = new String[n][n];

        for (int i = 0; i < n; i++) {
            String[] temp = bf.readLine().split("");
            for (int j = 0; j < n; j++) {
                map[i][j] = temp[j];
            }
        }

        for (int t = 0; t < 2; t++) {
            int sectionNum = 0;
            section = new int[n][n];
            visited = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        bfs(t, i, j);
                        sectionNum++;
                    }
                }
            }

            sb.append(sectionNum).append(" ");
        }

        System.out.print(sb);
    }

    static void bfs(int t, int startRow, int startCol) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol});
        String target = map[startRow][startCol];
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = cur[0] + move_row[i];
                int nextCol = cur[1] + move_col[i];

                if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
                    if (t == 0) {
                        if (!visited[nextRow][nextCol] && map[nextRow][nextCol].equals(target)) {
                            queue.offer(new int[]{nextRow, nextCol});
                            visited[nextRow][nextCol] = true;
                        }
                    } else {
                        if (target.equals("R") || target.equals("G")) {
                            if (!visited[nextRow][nextCol] && (map[nextRow][nextCol].equals("R") || map[nextRow][nextCol].equals("G"))) {
                                queue.offer(new int[]{nextRow, nextCol});
                                visited[nextRow][nextCol] = true;
                            }
                        } else {
                            if (!visited[nextRow][nextCol] && map[nextRow][nextCol].equals(target)) {
                                queue.offer(new int[]{nextRow, nextCol});
                                visited[nextRow][nextCol] = true;
                            }
                        }
                    }
                }
            }
        }
    }
}
