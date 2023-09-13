import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_7569 {
    // 위, 아래, 왼쪽, 오른쪽 ,앞, 뒤
    static int[] move_row = new int[]{0, 0, 0, 0, 1, -1};
    static int[] move_col = new int[]{0, 0, -1, 1, 0, 0};
    static int[] move_hei = new int[]{1, -1, 0, 0, 0, 0};
    static int m, n, h;
    static int[][][] map;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[n][m][h];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(bf.readLine());
                for (int k = 0; k < m; k++) {
                    map[j][k][i] = Integer.parseInt(st.nextToken());
                    if(map[j][k][i] == 1)
                        queue.offer(new int[]{j, k, i});
                }
            }
        }

        int[][][] dist = new int[n][m][h];
        bfs(dist, queue);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (map[j][k][i] == 0) {
                        if (dist[j][k][i] == 0) {
                            System.out.println(-1);
                            return;
                        } else {
                            answer = Math.max(answer, dist[j][k][i]);
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static void bfs(int[][][] dist, Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 6; i++) {
                int nextRow = cur[0] + move_row[i];
                int nextCol = cur[1] + move_col[i];
                int nextHei = cur[2] + move_hei[i];

                if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m
                        && nextHei >= 0 && nextHei < h) {
                    if (map[nextRow][nextCol][nextHei] == 0) {
                        if (dist[nextRow][nextCol][nextHei] == 0) {
                            dist[nextRow][nextCol][nextHei] = dist[cur[0]][cur[1]][cur[2]] + 1;
                            queue.offer(new int[]{nextRow, nextCol, nextHei});
                        } else if (dist[nextRow][nextCol][nextHei] > dist[cur[0]][cur[1]][cur[2]] + 1) {
                            dist[nextRow][nextCol][nextHei] = dist[cur[0]][cur[1]][cur[2]] + 1;
                            queue.offer(new int[]{nextRow, nextCol, nextHei});
                        }
                    }
                }
            }
        }
    }
}
