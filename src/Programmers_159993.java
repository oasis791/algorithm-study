import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class Programmers_159993 {

    static class Solution {
        public int solution(String[] maps) {
            int n = maps.length;
            int m = maps[0].length();
            String[][] map = new String[n][m];

            for(int i = 0; i < n; i++) {
                String[] temp = maps[i].split("");
                map[i] = Arrays.copyOf(temp, m);
            }

            Queue<int[]> queue = new LinkedList<>();
            int[][] dist = new int[n][m];
            boolean[][] visited = new boolean[n][m];
            int[] move_row = new int[]{-1, 0, 1, 0};
            int[] move_col = new int[]{0, 1, 0, -1};
            int[] leverPoint = new int[]{0, 0};
            int[] exitPoint = new int[]{0, 0};

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(map[i][j].equals("S")) {
                        queue.offer(new int[]{i, j});
                        visited[i][j] = true;
                    } else if(map[i][j].equals("L")) {
                        leverPoint[0] = i;
                        leverPoint[1] = j;
                    } else if (map[i][j].equals("E")) {
                        exitPoint[0] = i;
                        exitPoint[1] = j;
                    }
                }
            }

leverLoop:
            while(!queue.isEmpty()) {
                int[] cur = queue.poll();

                for(int i = 0; i < 4; i++) {
                    int nextRow = cur[0] + move_row[i];
                    int nextCol = cur[1] + move_col[i];

                    if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
                        if(!visited[nextRow][nextCol] && !map[nextRow][nextCol].equals("X")) {
                            dist[nextRow][nextCol] = dist[cur[0]][cur[1]] + 1;
                            visited[nextRow][nextCol] = true;
                            queue.offer(new int[]{nextRow, nextCol});

                            if(nextRow == leverPoint[0] && nextCol == leverPoint[1]) {
                                break leverLoop;
                            }
                        }
                    }
                }
            }

            if(!visited[leverPoint[0]][leverPoint[1]]) {
                return -1;
            } 

            queue = new LinkedList<>();
            visited = new boolean[n][m];
            queue.offer(new int[]{leverPoint[0], leverPoint[1]});

            while(!queue.isEmpty()) {
                int[] cur = queue.poll();

                for(int i = 0; i < 4; i++) {
                    int nextRow = cur[0] + move_row[i];
                    int nextCol = cur[1] + move_col[i];

                    if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
                        if(!visited[nextRow][nextCol] && !map[nextRow][nextCol].equals("X")) {
                            dist[nextRow][nextCol] = dist[cur[0]][cur[1]] + 1;
                            visited[nextRow][nextCol] = true;
                            queue.offer(new int[]{nextRow, nextCol});

                            if(nextRow == exitPoint[0] && nextCol == exitPoint[1]) {
                                return dist[nextRow][nextCol];
                            }
                        }
                    }
                }
            }

            return -1;
        }
    }
}
