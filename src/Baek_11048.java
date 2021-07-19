//11048번 이동하기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_11048 {
    private static int N;
    private static int M;
    private static int[][] room;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    int temp = Math.max(room[i - 1][j], room[i][j - 1]);
                    room[i][j] += temp;
                } else if (i - 1 < 0) {
                    room[i][j] += room[i][j - 1];
                } else if (j - 1 < 0) {
                    room[i][j] += room[i - 1][j];
                }
            }
        }
        System.out.println(room[N - 1][M - 1]);
    }
}
