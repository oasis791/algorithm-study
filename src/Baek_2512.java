import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2512 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		int[] budget = new int[n];

		int max = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < budget.length; i++) {
			budget[i] = Integer.parseInt(st.nextToken());
			if (max < budget[i]) {
				max = budget[i];
			}
		}

		int total = Integer.parseInt(bf.readLine());
		int lo = 0;
		int hi = max + 1;

		while (lo < hi) {
			int mid = (lo + hi) / 2;

			if (check(mid, budget, total)) {
				lo = mid + 1;
			} else {
				hi = mid;
			}
		}

		System.out.println(lo - 1);
	}

	static boolean check(int give, int[] budget, int total) {
		int count = 0;

		for (int i = 0; i < budget.length; i++) {
			if (budget[i] < give) {
				count += budget[i];
			} else {
				count += give;
			}
		}

		return count <= total;
	}
}
