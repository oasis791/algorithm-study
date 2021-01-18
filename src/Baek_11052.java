//11052번 카드 구매하기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_11052 {
    private static int N;
    private static int[] cost;
    private static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        cost = new int[N];
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < N; i++)
            cost[i] = Integer.parseInt(st.nextToken());
        dp[0] = cost[0];
        dp[1] = Math.max(cost[1], dp[0] * 2);
        for (int i = 2; i < N; i++) {
            int max = cost[i];
            for (int j = 0; j < i - 1; j++) {
                if (j == i - 1 - j)
                    max = Math.max(max, dp[j] * 2);
                else {
                    max = Math.max(max, dp[j] + dp[i - 1 - j]);
                }
            }
            dp[i] = max;
        }
        System.out.println(dp[N - 1]);
    }
}
