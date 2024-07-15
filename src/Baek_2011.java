import java.io.*;
import java.util.*;

public class Baek_2011 {
	static final int MOD = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String n = bf.readLine();

		if(n.equals("") || n.charAt(0) == '0') {
			System.out.println(0);
			return;
		}

		StringBuilder sb = new StringBuilder();
		long[] dp = new long[n.length() + 1];

		dp[0] = 1;
		dp[1] = 1;
		sb.append(n.charAt(0));
		int zeroCount = 0;

		for (int i = 1; i < n.length(); i++) {
			char c = n.charAt(i);

			if(c == '0') {
				if(n.charAt(i - 1) - '0' >= 3) {
					dp[i + 1] = 0;
				} else {
					dp[i + 1] = dp[i - 1];
				}
				sb = new StringBuilder();
				zeroCount++;

				if(zeroCount == 2) {
					System.out.println(0);
					return;
				}
				continue;
			}

			zeroCount = 0;
			sb.append(c);

			if(sb.length() == 2 && Integer.parseInt(sb.toString()) <= 26) {
				dp[i + 1] = (dp[i] + dp[i - 1]) % MOD;
			} else {
				dp[i + 1] = dp[i];
			}

			if(sb.length() == 2) {
				sb.deleteCharAt(0);
			}
		}

		System.out.println(dp[n.length()] % MOD);
    }
}
