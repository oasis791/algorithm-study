import java.util.Arrays;

public class Programmers_152996 {
	static class Solution {
		public static long solution(int[] weights) {

			long[] visit = new long[4001];
			long[] same = new long[1001];

			Arrays.fill(visit, -1);
			Arrays.fill(same, -1);

			long answer = 0;

			for (int i = 0; i < weights.length; i++) {
				same[weights[i]] += 1;
				answer += same[weights[i]];
				long sameCount = same[weights[i]];

				for (int j = 2; j <= 4; j++) {
					visit[weights[i] * j] += 1;
					answer += (visit[weights[i] * j] - sameCount);
				}
			}

			return answer;
		}
	}
}
