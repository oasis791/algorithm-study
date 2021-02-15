//11057번 오르막 수
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_11057 {
    private static int N;
    private static int result = 0;
    private static int[] dp = new int[10];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < 10; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 9; j++) {
                int sum = 0;
                for (int k = j; k < 10; k++) {
                    sum += dp[k] % 10007;
                }
                dp[j] = sum % 10007;
            }
        }

        for (int i = 0; i < 10; i++) {
            result += dp[i] % 10007;
        }
        System.out.println(result % 10007);
    }
}
