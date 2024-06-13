import java.io.*;
import java.util.*;

public class Baek_2343 {
	static int n, m;
	static int[] lessons;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		lessons = new int[n];

		int left = 0;
		int right = 0;
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < n; i++) {
			lessons[i] = Integer.parseInt(st.nextToken());
			left = Math.max(lessons[i], left);
			right += lessons[i];
		}

		while(left <= right) {
			int mid = (left + right) / 2;

			int count = getCount(mid);

			if(count > m) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(left);
	}

	static int getCount(int mid) {
		int sum = 0;
		int count = 0;

		for (int i = 0; i < n; i++) {
			if(sum + lessons[i] > mid) {
				sum = 0;
				count++;
			}
			sum += lessons[i];
		}

		if(sum != 0) count++;
		return count;
	}
}
