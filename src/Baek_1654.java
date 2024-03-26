import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1654 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		int[] lan = new int[k];
		long max = 0;

		for (int i = 0; i < k; i++) {
			lan[i] = Integer.parseInt(bf.readLine());
			if (max < lan[i]) {
				max = lan[i];
			}
		}

		long start = 0;
		long end = max + 1;

		while (start < end) {
			long mid = (start + end) / 2;

			long count = 0;

			for (int i = 0; i < lan.length; i++) {
				count += (lan[i] / mid);
			}

			if (count < n) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}

		System.out.println(start - 1);
	}
}