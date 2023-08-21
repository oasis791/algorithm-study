public class Programmers_87390 {

    static class Solution {
        public int[] solution(int n, long left, long right) {
            int size = (int)(right - left) + 1;
            int[] answer = new int[size];

            long target = left;
            for(int i = 0; i < answer.length; i++) {
                int[] point = calculateIndex(target++, n);
                answer[i] = calculateValue(point[0], point[1]);
            }

            return answer;
        }

        static int[] calculateIndex(long target, int n) {
            int row = (int) (target / (long)n);
            int col = (int) (target % (long)n);

            return new int[]{row, col};
        }

        static int calculateValue(int row, int col) {
            return Math.max(row + 1, col + 1);
        }
    }
}
