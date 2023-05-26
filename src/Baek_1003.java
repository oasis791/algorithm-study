import java.util.*;
import java.io.*;

public class Baek_1003 {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(bf.readLine());
        for(int test = 0; test < t; test++) {
            int n = Integer.parseInt(bf.readLine());
            int[][] dp = new int[n + 1][2];
            dp[0][0] = 1;
            dp[0][1] = 0;
            if(n >= 1) {
                dp[1][0] = 0;
                dp[1][1] = 1;
            }

            for(int i = 2; i <= n; i++) {
                dp[i][0] = dp[i - 2][0] + dp[i - 1][0];
                dp[i][1] = dp[i - 2][1] + dp[i - 1][1];
            }

            sb.append(dp[n][0]).append(" ").append(dp[n][1]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
