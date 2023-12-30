public class Programmers_12928 {
    static class Solution {
        public int solution(int n) {
            if (n == 0) return 0;

            int answer = 0;

            for (int i = 1; i <= n; i++) {
                if (n % i == 0) {
                    answer += i;
                }
            }

            return answer;
        }
    }
}
