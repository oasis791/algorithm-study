import java.util.Arrays;

public class Programmers_12987 {
    static class Solution {
        public int solution(int[] A, int[] B) {
            int n = A.length;
            int answer = 0;

            Arrays.sort(A);
            Arrays.sort(B);

            int j = n - 1;
            for (int i = n - 1; i >= 0; i--) {
                if(A[i] < B[j]) {
                    answer++;
                    j--;
                }
            }

            return answer;
        }
    }
}
