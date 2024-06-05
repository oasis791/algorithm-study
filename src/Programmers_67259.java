import java.util.Queue;
import java.util.LinkedList;

public class Programmers_67259 {
    static class Solution {
        static int[] move_row = new int[]{-1, 0, 1, 0};
        static int[] move_col = new int[]{0, 1, 0, -1};

        public static int solution(int[][] board) {
            int n = board.length;
            int[][][] dp = new int[n][n][4]; // 0: 크기, 1: 방향
            int answer = Integer.MAX_VALUE;

            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0, 0, 0, -1}); // -1은 슈퍼

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int cost = cur[2];
                int dir = cur[3];

                if (cur[0] == n - 1 && cur[1] == n - 1) {
                    answer = Math.min(answer, cost);
                }

                for (int i = 0; i < 4; i++) {
                    int nextRow = cur[0] + move_row[i];
                    int nextCol = cur[1] + move_col[i];

                    if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
                        if (board[nextRow][nextCol] == 1) continue;
                        int tempCost = cost;

                        if (dir == -1 || dir == i) {
                            tempCost += 100;
                        } else {
                            tempCost += 600;
                        }

                        if (dp[nextRow][nextCol][i] == 0 || board[nextRow][nextCol] >= tempCost) {
                            dp[nextRow][nextCol][i] = 1;
                            board[nextRow][nextCol] = tempCost;
                            queue.offer(new int[]{nextRow, nextCol, tempCost, i});
                        }
                    }
                }
            }

            return answer;
        }
    }
}
