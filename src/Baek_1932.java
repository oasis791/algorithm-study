import java.io.*;
import java.util.*;

public class Baek_1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());

		int[][] tri = new int[n][n];
		int[][] dp = new int[n][n];

		for (int i = 0; i < n; i++) {
			Arrays.fill(tri[i], 10001);
		}

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < i + 1; j++) {
				tri[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp[0][0] = tri[0][0];

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i + 1; j++) {
				if(i - 1 >= 0 && j - 1 >= 0) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + tri[i][j]);
				}
				if(tri[i - 1][j] < 10000) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + tri[i][j]);
				}
			}
		}

		int answer = 0;
		for (int i = 0; i < n; i++) {
			answer = Math.max(dp[n - 1][i], answer);
		}

		System.out.println(answer);
    }
}
