public class Programmers_12971 {
    static class Solution {
        public int solution(int sticker[]) {

            int answer = 0;
            int n = sticker.length;
            if (n == 1) {
                return sticker[0];
            }

            int[] dp = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                if(i == 0 || i == 1) {
                    dp[i] = sticker[0];
                    dp[i] = Math.max(dp[i], sticker[i]);
                } else {
                    dp[i] = Math.max(dp[i - 1], dp[i - 2] + sticker[i]);
                    dp[i] = Math.max(dp[i], sticker[i]);
                }

                answer = Math.max(answer, dp[i]);
            }

            dp = new int[n];
            for (int i = 1; i < n; i++) {
                if(i == 1 || i == 2) {
                    dp[i] = sticker[1];
                    dp[i] = Math.max(dp[i], sticker[i]);
                } else {
                    dp[i] = Math.max(dp[i - 1], dp[i - 2] + sticker[i]);
                    dp[i] = Math.max(dp[i], sticker[i]);
                }

                answer = Math.max(answer, dp[i]);
            }
            return answer;
        }
    }
}
