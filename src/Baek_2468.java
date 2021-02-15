//2468번 안전 영역
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2468 {
    private static int N;
    private static int[][] land;
    private static boolean visited[][];
    private static Queue<int[]> q = new LinkedList<>();
    private static int[] move_x = {-1, 0, 1, 0};
    private static int[] move_y = {0, 1, 0, -1};
    private static int max = 0;
    private static void flood() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    q.offer(new int[]{i, j});
                    while (!q.isEmpty()) {
                        int[] temp = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nextX = temp[0] + move_x[k];
                            int nextY = temp[1] + move_y[k];
                            if ((nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) && !visited[nextX][nextY]) {
                                visited[nextX][nextY] = true;
                                q.offer(new int[]{nextX, nextY});
                            }
                        }
                    }
                    count++;
                }
            }
        }
        max = Math.max(max, count);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        land = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < N; j++) {
                land[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int height = 0; height <= 100; height++) {
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (land[i][j] <= height) {
                        visited[i][j] = true;
                    }
                }
            }
            flood();
        }
        System.out.println(max);
    }
}