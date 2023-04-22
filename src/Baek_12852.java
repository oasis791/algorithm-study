import java.io.*;
import java.util.*;

public class Baek_12852 {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(bf.readLine());

        int[] dp = new int[n + 1];
        int[] route = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            route[i] = i - 1;

            if(i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
                dp[i] = dp[i / 2] + 1;
                route[i] = i / 2;
            }

            if(i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
                dp[i] = dp[i / 3] + 1;
                route[i] = i / 3;
            }
        }

        sb.append(dp[n]).append("\n");
        while (n >= 1) {
            sb.append(n + " ");
            n = route[n];
        }

        System.out.println(sb);
    }
}
