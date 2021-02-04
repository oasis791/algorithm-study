//2293번 동전1
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2293 {
    private static int N;
    private static int K;
    private static int[] coin;
    private static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K + 1];
        coin = new int[N];
        for (int i = 0; i < N; i++)
            coin[i] = Integer.parseInt(bf.readLine());
        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            int index = coin[i];
            for (; index <= K; index++) {
                dp[index] = dp[index] + dp[index - coin[i]];
            }
        }
        System.out.println(dp[K]);
    }
}
