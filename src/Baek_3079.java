import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_3079 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] time = new int[n];
		int max = 0;
		for (int i = 0; i < n; i++) {
			time[i] = Integer.parseInt(bf.readLine());

			if (max < time[i]) {
				max = time[i];
			}
		}

		long lo = 1;
		long hi = (long)max * m;

		while (lo < hi) {
			long mid = (lo + hi) / 2;

			long count = 0;
			for (int i = 0; i < time.length; i++) {
				count += mid / time[i];
				if(count >= m) break;
			}

			if (count >= m) {
				hi = mid;
			} else {
				lo = mid + 1;
			}
		}

		System.out.println(lo);
	}
}