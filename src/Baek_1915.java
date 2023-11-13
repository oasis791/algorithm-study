import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1915 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        if (n == 1 && m == 1) {
            System.out.println(1);
            return;
        }
        int answer = 0;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String[] input = bf.readLine().split("");
            for (int j = 1; j <= m; j++) {
                int temp = Integer.parseInt(input[j - 1]);

                if (i == 1 && j == 1) {
                    dp[i][j] = temp;
                } else {
                    if (temp == 1) {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                        answer = Math.max(answer, dp[i][j]);
                    }
                }
            }
        }
        System.out.println(answer * answer);
    }
}