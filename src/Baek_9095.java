import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(bf.readLine());
            int[] dp = new int[11];
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;
            for (int i = 4; i <= n; i++) {
                dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
            }
            System.out.println(dp[n]);
        }
    }
}