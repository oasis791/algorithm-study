//1018번 체스판 다시 칠하기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Baek_1018 {
    public static boolean[][] check;
    public static int min = 64;

    public static void find(int x, int y) {
        int end_x = x + 8;
        int end_y = y + 8;
        int count = 0;

        boolean firstColor = check[x][y];    // 첫 번째 칸의 색

        for (int i = x; i < end_x; i++) {
            for (int j = y; j < end_y; j++) {
                if (check[i][j] != firstColor) {
                    count++;
                }
                firstColor = (!firstColor);
            }
            firstColor = !firstColor;
        }
        count = Math.min(count, 64 - count);
        min = Math.min(min, count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        check = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String str = bf.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == 'W') {
                    check[i][j] = true;
                } else {
                    check[i][j] = false;
                }
            }
        }
        int chessRow = N - 7;
        int chessCol = M - 7;
        for (int i = 0; i < chessRow; i++) {
            for (int j = 0; j < chessCol; j++) {
                find(i, j);
            }
        }
        System.out.println(min);
    }
}