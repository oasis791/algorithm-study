import java.util.Arrays;

public class Programmers_12905 {
	static class Solution {
		static int[] move_row = new int[]{-1, -1, 0};
		static int[] move_col = new int[]{-1, 0, -1};
		public static int solution(int[][] board) {
			int answer = 0;
			int n = board.length;
			int m = board[0].length;
			int[][] dp = new int[n][m];

			for (int i = 0; i < n; i++) {
				dp[i] = Arrays.copyOf(board[i], m);
			}

			for (int i = 0; i < n; i++) {
				loop:
				for (int j = 0; j < m; j++) {
					if (board[i][j] == 1) {
						int min = Integer.MAX_VALUE;
						for (int k = 0; k < 3; k++) {
							int nextRow = i + move_row[k];
							int nextCol = j + move_col[k];

							if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) {
								continue loop;
							} else {
								if(board[nextRow][nextCol] == 0) continue loop;
							}

							min = Math.min(dp[nextRow][nextCol], min);
						}

						// 제일 작은거 + 1
						dp[i][j] = min + 1;
					}
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					answer = Math.max(answer, dp[i][j]);
				}
			}

			return answer * answer;
		}
	}
}
