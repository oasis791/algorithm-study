public class Programmers_42897 {
    static class Solution {
        public int solution(int[] money) {
            int answer = 0;
            int n = money.length;

            if (n == 1) {
                return money[0];
            }

            int[] dp = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                if(i == 0 || i == 1) {
                    dp[i] = money[0];
                    dp[i] = Math.max(dp[i], money[i]);
                } else {
                    dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
                    dp[i] = Math.max(dp[i], money[i]);
                }

                answer = Math.max(answer, dp[i]);
            }

            dp = new int[n];
            for (int i = 1; i < n; i++) {
                if(i == 1 || i == 2) {
                    dp[i] = money[1];
                    dp[i] = Math.max(dp[i], money[i]);
                } else {
                    dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
                    dp[i] = Math.max(dp[i], money[i]);
                }

                answer = Math.max(answer, dp[i]);
            }

            return answer;
        }
    }
}
