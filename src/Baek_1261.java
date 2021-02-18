//1261번 알고스팟
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_1261 {
    private static int N;
    private static int M;
    private static int[][] maze;
    private static int result;
    private static boolean[][] visited;
    private static PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[2] < o2[2])
                return -1;
            else if (o1[2] > o2[2])
                return 1;
            else
                return 0;
        }
    });
    private static int[] move_x = {-1, 0, 1, 0};
    private static int[] move_y = {0, 1, 0, -1};
    private static void bfs() {
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            if (temp[0] == N - 1 && temp[1] == M - 1) {
                result = temp[2];
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = temp[0] + move_x[i];
                int nextY = temp[1] + move_y[i];
                if ((nextX >= 0 && nextX < N && nextY >= 0 && nextY < M) && !visited[nextX][nextY]) {
                    if (maze[nextX][nextY] == 0) {
                        visited[nextX][nextY] = true;
                        q.offer(new int[]{nextX, nextY, temp[2]});
                    } else if (maze[nextX][nextY] == 1) {
                        visited[nextX][nextY] = true;
                        q.offer(new int[]{nextX, nextY, temp[2] + 1});
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        maze = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] temp = bf.readLine().split("");
            for (int j = 0; j < M; j++) {
                maze[i][j] = Integer.parseInt(temp[j]);
            }
        }
        q.offer(new int[]{0, 0, 0});
        visited[0][0] = true;
        bfs();

        System.out.println(result);
    }
}
