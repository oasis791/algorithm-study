import java.util.Queue;
import java.util.LinkedList;

public class Programmers_87694 {
    static class Solution {
        static boolean[][][] map = new boolean[101][101][2];
        static int[][] path = new int[101][101];
        static boolean[][] visited = new boolean[101][101];

        public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
            int[] start = new int[]{characterY * 2, characterX * 2};
            int[] end = new int[]{itemY * 2, itemX * 2};

            for (int i = 0; i < rectangle.length; i++) {
                int[] upperLeft = new int[]{rectangle[i][1] * 2, rectangle[i][0] * 2};
                int[] lowerRight = new int[]{rectangle[i][3] * 2, rectangle[i][2] * 2};

                for (int j = upperLeft[0]; j <= lowerRight[0]; j++) {
                    for (int k = upperLeft[1]; k <= lowerRight[1]; k++) {
                        if(!map[j][k][1] && (j == upperLeft[0] || j == lowerRight[0] || 
                                    k == upperLeft[1] || k == lowerRight[1])) {
                            map[j][k][0] = true;
                            map[j][k][1] = false;
                        } else {
                            map[j][k][0] = false;
                            map[j][k][1] = true;
                        }
                    }
                }
            }

            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{start[0], start[1]});
            visited[start[0]][start[1]] = true;
            int[] move_row = new int[] {-1, 0, 1, 0};
            int[] move_col = new int[] {0, 1, 0, -1};

            while(!queue.isEmpty()) {
                int[] cur = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nextRow = cur[0] + move_row[i];
                    int nextCol = cur[1] + move_col[i];

                    if(nextRow >= 1 && nextRow <= 100 && nextCol >= 1 && nextCol <= 100) {
                        if(!visited[nextRow][nextCol] && map[nextRow][nextCol][0]) {
                            path[nextRow][nextCol] = path[cur[0]][cur[1]] + 1;
                            visited[nextRow][nextCol] = true;
                            queue.offer(new int[] {nextRow, nextCol});
                        }
                    }
                }
            }

            return path[end[0]][end[1]] / 2;
        }
    }
}
