import java.util.Arrays;

public class Programmers_12913 {
    static class Solution {
        int solution(int[][] land) {
            int[][] dp = new int[land.length][4];
            dp[0] = Arrays.copyOf(land[0], 4);

            for(int i = 1; i < land.length; i++) {
                for(int j = 0; j < 4; j++) {
                    int max = 0;

                    for(int k = 0; k < 4; k++) {
                        if(k != j) {
                            max = Math.max(dp[i - 1][k], max);
                        }
                    }

                    dp[i][j] = land[i][j] + max;
                }
            }

            int answer = 0;

            for(int i = 0; i < 4; i++) {
                answer = Math.max(dp[land.length - 1][i], answer);
            }

            return answer;
        }
    }
}
