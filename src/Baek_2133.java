import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2133 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        if (n % 2 == 1) {
            System.out.println(0);
            return;
        }

        if (n == 2) {
            System.out.println(3);
            return;
        }
        int[] dp = new int[n + 1];
        dp[2] = 3;
        dp[4] = 11;

        for (int i = 6; i <= n; i += 2) {
            dp[i] = dp[i - 2] * 3 + 2;
            for (int j = 1; j <= (i - 4) / 2; j++) {
                dp[i] += dp[2 * j] * 2;
            }
        }

        System.out.println(dp[n]);
    }
}
