import java.io.*;
import java.util.*;

public class Baek_7579 {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int answer = Integer.MAX_VALUE;
        int sum = 0;

        int[] mem = new int[n];
        int[] fee = new int[n];

        st = new StringTokenizer(bf.readLine(), " ");
        StringTokenizer st2 = new StringTokenizer(bf.readLine(), " ");
        for(int i = 0; i < n; i++) {
            mem[i] = Integer.parseInt(st.nextToken());
            fee[i] = Integer.parseInt(st2.nextToken());
        }


        int[][] dp = new int[n][100001];

        for(int i = 0; i < n; i++) {
            int price = fee[i];
            int memory = mem[i];

            for (int j = 0; j < 100001; j++) {
                if (i == 0) {
                    if(j >= price)
                        dp[i][j] = memory;
                } else {
                    if(j >= price) {
                        dp[i][j] = Math.max(dp[i - 1][j - price] + memory, dp[i - 1][j]);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
                if(dp[i][j] >= m) {
                    answer = Math.min(answer, j);
                }
            }
        }

        System.out.println(answer);
    }
}
