import java.util.*;
import java.io.*;

public class Baek_1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] num = new int[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        dp[0] = num[0];
        int answer = dp[0];

        for(int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + num[i], num[i]);
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }
}
