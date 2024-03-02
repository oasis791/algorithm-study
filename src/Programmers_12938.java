import java.util.Arrays;

public class Programmers_12938 {
    static class Solution {
        public int[] solution(int n, int s) {

            if(s < n) {
                return new int[]{-1};
            }

            int[] answer = new int[n];
            for (int i = 0; i < n; i++) {
                answer[i] = s / n;
            }

            for (int i = 0; i < s % n; i++) {
                answer[i]++;
            }

            Arrays.sort(answer);

            return answer;
        }
    }
}
