//7576 토마토
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_7576 {
    private static int M;
    private static int N;
    private static int[][] box;
    private static Queue<int[]> queue = new LinkedList<>();
    private static int[] moveX = {-1, 0, 1, 0}; //상,우,하,좌
    private static int[] moveY = {0, 1, 0, -1};//상,우,하,좌

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < M; j++) {
                int temp = Integer.parseInt(st.nextToken());
                box[i][j] = temp;
                if (temp == 1)
                    queue.offer(new int[]{i, j});
            }
        }
        bfs();

        int max = -1;
        boolean zeroCheck = false;

        for (int i = 0; i < N; i++) {
            if (!zeroCheck) {
                for (int j = 0; j < M; j++) {
                    if (box[i][j] == 0) {
                        zeroCheck = true;
                        break;
                    }
                    max = Math.max(max, box[i][j]);
                }
            } else {
                break;
            }
        }

        if (zeroCheck) {
            System.out.println(-1);
        } else {
            System.out.println(max - 1);
        }
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int x = pair[0];
            int y = pair[1];

            for (int i = 0; i < 4; i++) {
                int nextX = x + moveX[i];
                int nextY = y + moveY[i];
                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && box[nextX][nextY] == 0) {
                    box[nextX][nextY] = box[x][y] + 1;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }
    }
}