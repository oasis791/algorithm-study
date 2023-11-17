public class Programmers_150369 {
    public static void main(String[] args) {
        System.out.println(Solution.solution(4, 5, new int[]{1, 0, 3, 1, 2}, new int[]{0, 3, 0, 4, 0})); // 16
        System.out.println(Solution.solution(2, 7, new int[]{1, 0, 2, 0, 1, 0, 2}, new int[]{0, 2, 0, 1, 0, 2, 0})); // 30
    }

    static class Solution {
        public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
            long answer = 0;

            int deliver = 0;
            int pickUp = 0;

            for (int i = n - 1; i >= 0; i--) {
                deliver -= deliveries[i];
                pickUp -= pickups[i];

                while(deliver < 0 || pickUp < 0) {
                    deliver += cap;
                    pickUp += cap;
                    answer += (i + 1) * 2L;
                }
            }
            return answer;
        }
    }
}
