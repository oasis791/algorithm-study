//20500 Ezreal 여눈부터 가네 ㅈㅈ
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_20500 {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        int[] dp = new int[N + 1];
        if (N == 1) {
            System.out.println(0);
            System.exit(0);
        }
        if (N == 2) {
            System.out.println(1);
            System.exit(0);
        }
        if (N == 3) {
            System.out.println(1);
            System.exit(0);
        }
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
        for (int i = 4; i < N + 1; i++) {
            if (i % 2 == 0) {
                dp[i] = ((dp[i - 1] % 1000000007) * 2 + 1) % 1000000007;
            } else {
                dp[i] = ((dp[i - 1] % 1000000007) * 2 - 1) % 1000000007;
            }
        }
        System.out.println(dp[N]);
    }
}
