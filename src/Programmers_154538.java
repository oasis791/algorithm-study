public class Programmers_154538 {
    static class Solution {
        public int solution(int x, int y, int n) {
            int[] dp = new int[y + 1];
            dp[x] = 1;
            for(int i = x + 1; i <= y; i++) {
                if(i % 3 == 0 && i / 3 >= x && dp[i / 3] > 0) {
                    if(dp[i] == 0) {
                        dp[i] = dp[i / 3] + 1;
                    } else {
                        dp[i] = Math.min(dp[i], dp[i / 3] + 1);
                    }
                }

                if(i % 2 == 0 && i / 2 >= x && dp[i / 2] > 0) {
                    if(dp[i] == 0) {
                        dp[i] = dp[i / 2] + 1;
                    } else {
                        dp[i] = Math.min(dp[i], dp[i / 2] + 1);
                    }
                }

                if(i - n >= x && dp[i - n] > 0) {
                    if(dp[i] == 0) {
                        dp[i] = dp[i - n] + 1;
                    } else {
                        dp[i] = Math.min(dp[i], dp[i - n] + 1);
                    }
                }
            }

            return dp[y] == 0 ? -1 : dp[y] - 1;
        }
    }
}
