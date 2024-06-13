import java.util.Arrays;

public class Programmers_43238 {
	static class Solution {
		public static long solution(int n, int[] times) {

			Arrays.sort(times);
			long left = 0;
			long right = (long)times[times.length - 1] * n;

			while (left <= right) {
				long mid = (left + right) / 2;

				long count = 0;
				for (int i = 0; i < times.length; i++) {
					count += mid / times[i];
				}

				if(count < n) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}

			return left;
		}
	}
}
