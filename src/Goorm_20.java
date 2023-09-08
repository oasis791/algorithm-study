import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Goorm_20 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        String[][] map = new String[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String[] input = bf.readLine().split("");
            for (int j = 1; j <= n; j++) {
                map[i][j] = input[j - 1];
            }
        }

        int[] move_row = new int[]{-1, 0, 1, 0};
        int[] move_col = new int[]{0, 1, 0, -1};

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(bf.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            String s = st.nextToken();

            Queue<int[]> queue = new LinkedList<>();
            ArrayList<int[]> points = new ArrayList<>();
            boolean[][] visited = new boolean[n + 1][n + 1];
            queue.offer(new int[]{row, col});
            visited[row][col] = true;
            points.add(new int[]{row, col});
            map[row][col] = s;
            int count = 1;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int nextRow = cur[0] + move_row[j];
                    int nextCol = cur[1] + move_col[j];

                    if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
                        if (!visited[nextRow][nextCol] && map[nextRow][nextCol].equals(s)) {
                            visited[nextRow][nextCol] = true;
                            count++;
                            points.add(new int[]{nextRow, nextCol});
                            queue.offer(new int[]{nextRow, nextCol});
                        }
                    }
                }
            }

            if (count >= k) {
                for (int[] point : points) {
                    map[point[0]][point[1]] = ".";
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
