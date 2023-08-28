import java.io.*;
import java.util.*;

public class Baek_2167 {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] prefix = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= m; j++) {
                if(j == 1) {
                    prefix[i][j] = prefix[i - 1][m] + Integer.parseInt(st.nextToken());
                } else {
                    prefix[i][j] = prefix[i][j - 1] + Integer.parseInt(st.nextToken());
                }
            }
        }

        int k = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(bf.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int sum = 0;
            for (int j = row; j <= x; j++) {
                int tempRow = j;
                int tempCol = col;
                if(tempCol - 1 == 0) {
                    tempRow -= 1;
                    tempCol = m;
                } else {
                    tempCol -= 1;
                }

                sum += prefix[j][y] - prefix[tempRow][tempCol];
            }
            sb.append(sum).append("\n");
        }

        System.out.print(sb);
    }
}
