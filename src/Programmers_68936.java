public class Programmers_68936 {
    static class Solution {
        static int[] answer;
        public int[] solution(int[][] arr) {
            answer = new int[] {0, 0};
            quadTree(arr, new int[]{0, 0}, arr.length);
            return answer;
        }

        static void quadTree(int[][] arr, int[] leftUp, int weight) {
            int num = arr[leftUp[0]][leftUp[1]];

            boolean isSame = true;

loop:
            for (int i = 0; i < weight; i++) {
                for (int j = 0; j < weight; j++) {
                    if(num != arr[leftUp[0] + i][leftUp[1] + j]) {
                        isSame = false;
                        break loop;
                    }
                }
            }

            if(isSame) {
                answer[num]++;
                return;
            }

            quadTree(arr, leftUp, weight / 2);
            quadTree(arr, new int[] {leftUp[0], leftUp[1] + weight / 2}, weight / 2);
            quadTree(arr, new int[] {leftUp[0] + weight / 2, leftUp[1]}, weight / 2);
            quadTree(arr, new int[] {leftUp[0] + weight / 2, leftUp[1] + weight / 2}, weight / 2);
        }
    }
}
