import java.util.*;
import java.io.*;

public class Baek_14500 {
    static int N, M;
    static int[][] map;
    static int answer = 0;
    static int[] move_row = new int[]{-1, 0, 1, 0};
    static int[] move_col = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tetromino(i, j, 1, map[i][j], -1);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static void tetromino(int x, int y, int depth, int sum, int dir) {
        if(depth == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        if(depth == 2) {
            if(isOk(x + move_row[dir], y + move_col[dir])) {
                if(isOk(x + move_row[(dir + 1) % 4], y + move_col[(dir + 1) % 4]))
                    tetromino(x + move_row[(dir + 1) %4], y + move_col[(dir + 1) % 4], depth + 2,
                            sum + map[x + move_row[dir]][y + move_col[dir]] +
                            map[x + move_row[(dir + 1) % 4]][y + move_col[(dir + 1) % 4]] , (dir + 1) % 4);

                if(isOk(x + move_row[(dir + 3) % 4], y + move_col[(dir + 3) % 4]))
                    tetromino(x + move_row[(dir + 3) %4], y + move_col[(dir + 3) % 4], depth + 2,
                            sum + map[x + move_row[dir]][y + move_col[dir]] +
                            map[x + move_row[(dir + 3) % 4]][y + move_col[(dir + 3) % 4]] , (dir + 3) % 4);
            }
        }

        for(int i = 0; i < 4; i++) {
            if(i != (dir + 2) % 4 ) {
                int nextRow = x + move_row[i];
                int nextCol = y + move_col[i];
                if(isOk(nextRow, nextCol)) {
                    tetromino(nextRow, nextCol, depth + 1, sum + map[nextRow][nextCol], i);
                }
            }
        }
    }

    static boolean isOk(int x, int y) {
        if(x >= 0 && x < N && y >= 0 && y < M) {
            return true;
        } else {
            return false;
        }
    }
}
