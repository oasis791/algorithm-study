public class Programmers_42860 {
	static class Solution {
		public static int solution(String name) {
			int answer = 0;
			int min = Integer.MAX_VALUE;
			int n = name.length();
			for (int i = 0; i < n; i++) {
				char c = name.charAt(i);
				answer += Math.min(c - 'A', 'Z'- c + 1);

				int j = i + 1;
				while (j < n && name.charAt(j) == 'A') j++;
				min = Math.min(min, Math.min(i+i+(n-j), i+(n-j)+(n-j)));
			}

			answer += min;

			return answer;
		}
	}
}
