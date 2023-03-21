import java.io.*;
import java.util.StringTokenizer;

public class Baek_14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] duration = new int[N + 1];
        int[] cost = new int[N + 1];
        int[] dp = new int[N + 2];
        int answer = 0;

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            duration[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            if(dp[i-1] > dp[i])
                dp[i] = dp[i - 1];
            answer = Math.max(answer, dp[i]);
            if (i + duration[i] <= N + 1) {
                if (cost[i] + dp[i] > dp[i + duration[i]]) {
                    dp[i + duration[i]] = cost[i] + dp[i];
                }
            }
        }

        bw.write(String.valueOf(Math.max(answer, dp[N + 1])));
        bw.flush();
        bw.close();
        br.close();
    }
}