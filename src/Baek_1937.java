import java.io.*;
import java.util.*;

public class Baek_1937 {
	static int[] move_row = new int[]{-1, 0, 1, 0};
	static int[] move_col = new int[]{0, 1, 0, -1};
	static int[][] map, dp;
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		int answer = 0;

		map = new int[n][n];
		dp = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				answer = Math.max(answer, dfs(i, j));
			}
		}

		System.out.println(answer);
	}

	static int dfs(int row, int col) {
		if(dp[row][col] != 0) {
			return dp[row][col];
		}

		dp[row][col] = 1;

		for (int i = 0; i < 4; i++) {
			int nextRow = row + move_row[i];
			int nextCol = col + move_col[i];

			if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
				if(map[nextRow][nextCol] > map[row][col]) {
					dp[row][col] = Math.max(dp[row][col], dfs(nextRow, nextCol) + 1);
				}
			}
		}

		return dp[row][col];
	}
}
