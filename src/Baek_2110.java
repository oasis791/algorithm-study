import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_2110 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int[] home = new int[n];

		for (int i = 0; i < n; i++) {
			home[i] = Integer.parseInt(bf.readLine());
		}

		Arrays.sort(home);

		int lo = 1;
		int hi = home[n - 1] - home[0] + 1;

		while (lo < hi) {
			int mid = (lo + hi) / 2;

			if (check(mid, home, c)) {
				lo = mid + 1;
			} else {
				hi = mid;
			}
		}

		System.out.println(lo - 1);
	}

	static boolean check(int dist, int[] home, int c) {
		int count = 1;
		int last = home[0];

		for (int i = 1; i < home.length; i++) {
			int locate = home[i];

			if (locate - last >= dist) {
				count++;
				last = locate;
			}
		}

		return count >= c;
	}
}
