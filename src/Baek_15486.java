import java.io.*;
import java.util.*;

public class Baek_15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] cost = new int[n];
        int[] sche = new int[n];
        int[] dp = new int[n + 1];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            sche[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;

        for(int i = 0; i < n; i++) {
            if(dp[i] < answer) {
                dp[i] = answer;
            }

            if(i + sche[i] <= n) {
                dp[i + sche[i]] = Math.max(dp[i] + cost[i], dp[i + sche[i]]);
            }
            answer = Math.max(dp[i], answer);
        }

        System.out.println(Math.max(answer, dp[n]));
    }
}
