import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_2206 {
    static int n, m;
    static int[][] map, dist;
    static int[] move_row = new int[]{-1, 0, 1, 0};
    static int[] move_col = new int[]{0, 1, 0, -1};
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        if (n == 1 && m == 1) {
            System.out.println(1);
            return;
        }

        map = new int[n + 1][m + 1];
        dist = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1][2];

        for (int i = 1; i <= n; i++) {
            String[] input = bf.readLine().split("");
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(input[j - 1]);
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 1, 0});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = now[0] + move_row[i];
                int nextCol = now[1] + move_col[i];

                if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= m) {
                    if (map[nextRow][nextCol] == 1) {
                        if (now[2] == 0 && !visited[nextRow][nextCol][1]) {
                            visited[nextRow][nextCol][now[2]] = true;
                            dist[nextRow][nextCol] = dist[now[0]][now[1]] + 1;
                            queue.offer(new int[]{nextRow, nextCol, 1});
                        }
                    } else {
                        if (!visited[nextRow][nextCol][now[2]]) {
                            visited[nextRow][nextCol][now[2]] = true;
                            dist[nextRow][nextCol] = dist[now[0]][now[1]] + 1;
                            queue.offer(new int[]{nextRow, nextCol, now[2]});
                        }
                    }

                    if (nextRow == n && nextCol == m) {
                        System.out.println(dist[nextRow][nextCol] + 1);
                        return;
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
