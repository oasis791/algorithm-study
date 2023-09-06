import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Goorm_18 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] ver = new int[n + 1][n + 1];
        int[][] hor = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());

            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();

            switch (dir) {
                case "R":
                    while (col <= n) {
                        hor[row][col++] += 1;
                    }
                    break;
                case "L":
                    while (col >= 1) {
                        hor[row][col--] += 1;
                    }
                    break;
                case "D":
                    while (row <= n) {
                        ver[row++][col] += 1;
                    }
                    break;
                case "U":
                    while (row >= 1) {
                        ver[row--][col] += 1;
                    }
                    break;
                default:
                    break;
            }
        }

        long answer = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                answer += (long) ver[i][j] * hor[i][j];
            }
        }

        System.out.println(answer);
    }
}
