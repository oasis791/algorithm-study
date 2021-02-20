//2573번 빙산
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2573 {
    private static int N;
    private static int M;
    private static int[][] iceberg;
    private static boolean[][] visited;
    private static Queue<int[]> q = new LinkedList<>();
    private static int[] move_x = {-1, 0, 1, 0};
    private static int[] move_y = {0, 1, 0, -1};
    private static int year = 0;
    private static void bfs() {
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            if (!visited[temp[0]][temp[1]]) {
                visited[temp[0]][temp[1]] = true;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = temp[0] + move_x[i];
                int nextY = temp[1] + move_y[i];
                if ((nextX >= 0 && nextX < N && nextY >= 0 && nextY < M) && !visited[nextX][nextY] && iceberg[nextX][nextY] != 0) {
                    iceberg[nextX][nextY] -= 1;
                    if (iceberg[nextX][nextY] == 0) {
                        visited[nextX][nextY] = true;
                    }
                }
            }
        }
    }
    private static void splitCheck() {
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            if (!visited[temp[0]][temp[1]]) {
                visited[temp[0]][temp[1]] = true;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = temp[0] + move_x[i];
                int nextY = temp[1] + move_y[i];
                if ((nextX >= 0 && nextX < N && nextY >= 0 && nextY < M) && !visited[nextX][nextY] && iceberg[nextX][nextY] != 0) {
                    q.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        iceberg = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < M; j++) {
                iceberg[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (true) {
            year++;
            int splitCount = 0;
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
                for (int j = 0; j < M; j++) {
                    if (iceberg[i][j] == 0) {
                        q.offer(new int[]{i, j});
                    }
                }
            }
            bfs();
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (iceberg[i][j] != 0 && !visited[i][j]) {
                        splitCount++;
                        q.offer(new int[]{i, j});
                        splitCheck();
                        if (splitCount >= 2) {
                            System.out.println(year);
                            System.exit(0);
                        }
                    }
                }
            }
            boolean check = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (iceberg[i][j] != 0) {
                        check = true;
                    }
                }
            }
            if (!check) {
                System.out.println(0);
                System.exit(0);
            }
        }
    }
}