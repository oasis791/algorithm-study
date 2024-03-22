import java.util.Arrays;

public class Programmers_12952 {
    static class Solution {
        static int answer = 0;
        public int solution(int n) {
            dfs(0, n, 0, new boolean[n][n]);
            return answer;
        }

        static void dfs (int depth, int n, int row, boolean[][] visited) {
            if(depth == n) {
                answer++;
                return;
            }

            for (int j = 0; j < n; j++) {
                if(!visited[row][j]) {
                    boolean[][] newVis = new boolean[n][n];
                    for (int k = 0; k < n; k++) {
                        newVis[k] = Arrays.copyOf(visited[k], n);
                    }

                    moveQueen(newVis, new int[]{row, j}, n);
                    dfs(depth + 1, n, row + 1, newVis);
                }
            }
        }

        static void moveQueen(boolean[][] visited, int[] point, int n) {

            // ->
            int i = 0;
            while(point[1] + i < n) {
                visited[point[0]][point[1] + i++] = true;
            }

            // <-
            i = 0;
            while(point[1] - i >= 0) {
                visited[point[0]][point[1] - i++] = true;
            }

            // 아래
            i = 0;
            while(point[0] + i < n) {
                visited[point[0] + i++][point[1]] = true;
            }
            // 위
            i = 0;
            while(point[0] - i >= 0) {
                visited[point[0] - i++][point[1]] = true;
            }

            // 왼쪽 위
            i = 0;
            while(point[0] - i >= 0 && point[1] - i >= 0) {
                visited[point[0] - i][point[1] - i++] = true;
            }

            // 오른쪽 아래
            i = 0;
            while(point[0] + i < n && point[1] + i < n) {
                visited[point[0] + i][point[1] + i++] = true;
            }

            // 오른쪽 위
            i = 0;
            while(point[0] - i >= 0 && point[1] + i < n) {
                visited[point[0] - i][point[1] + i++] = true;
            }

            // 왼쪽 아래
            i = 0;
            while(point[0] + i < n && point[1] - i >= 0) {
                visited[point[0] + i][point[1] - i++] = true;
            }
        }
    }
}
