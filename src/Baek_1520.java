import java.io.*;
import java.util.*;

public class Baek_1520 {
    static int[] move_row = new int[]{-1, 0, 1, 0};
    static int[] move_col = new int[]{0, 1, 0, -1};
    static int n, m;
    static int[][] map;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dp = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());

            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int row, int col) {
        if(row == n - 1 && col == m - 1) {
            return 1;
        } 

        if(dp[row][col] != -1) {
            return dp[row][col];
        }

        dp[row][col] = 0;

        for(int i = 0; i < 4; i++) {
            int nextRow = row + move_row[i];
            int nextCol = col + move_col[i];

            if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
                if(map[row][col] > map[nextRow][nextCol]) {
                    dp[row][col] += dfs(nextRow, nextCol);
                }
            }
        }

        return dp[row][col];
    }
}
