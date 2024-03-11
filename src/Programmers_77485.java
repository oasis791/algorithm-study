public class Programmers_77485 {
    static class Solution {
        public int[] solution(int rows, int columns, int[][] queries) {
            int[][] map = new int[rows + 1][columns + 1];
            int[] answer = new int[queries.length];

            int index = 1;
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= columns; j++) {
                    map[i][j] = index++;
                }
            }

            for (int i = 0; i < queries.length; i++) {
                int y1 = queries[i][0];
                int x1 = queries[i][1];
                int y2 = queries[i][2];
                int x2 = queries[i][3];

                int min = Integer.MAX_VALUE;

                // ->
                int prev = map[y1][x1];
                for (int j = x1 + 1; j <= x2; j++) {
                    int temp = map[y1][j];
                    map[y1][j] = prev;
                    prev = temp;
                    min = Math.min(min, map[y1][j]);
                }

                // |
                for (int j = y1 + 1; j <= y2; j++) {
                    int temp = map[j][x2];
                    map[j][x2] = prev;
                    prev = temp;
                    min = Math.min(min, map[j][x2]);
                }

                // <-
                for (int j = x2 - 1; j >= x1; j--) {
                    int temp = map[y2][j];
                    map[y2][j] = prev;
                    prev = temp;
                    min = Math.min(min, map[y2][j]);
                }

                // ^
                for (int j = y2 - 1; j >= y1; j--) {
                    int temp = map[j][x1];
                    map[j][x1] = prev;
                    prev = temp;
                    min = Math.min(min, map[j][x1]);
                }

                answer[i] = min;
            }

            return answer;
        }
    }
}
