import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

public class Programmers_154540 {
    class Solution {

        public int[] solution(String[] maps) {
            int n = maps.length;
            int m = maps[0].length();
            int[] move_row = new int[]{-1, 0, 1, 0};
            int[] move_col = new int[]{0, 1, 0, -1};
            int[][] intMap = new int[n][m];
            boolean[][] visited = new boolean[n][m];
            ArrayList<Integer> list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String[] input = maps[i].split("");
                for (int j = 0; j < m; j++) {
                    if(input[j].equals("X")) {
                        intMap[i][j] = 0;
                    } else {
                        intMap[i][j] = Integer.parseInt(input[j]);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(intMap[i][j] > 0 && !visited[i][j]) {
                        Queue<int[]> queue = new LinkedList<>();
                        visited[i][j] = true;
                        queue.offer(new int[]{i, j});
                        int temp = intMap[i][j];

                        while(!queue.isEmpty()) {
                            int[] cur = queue.poll();

                            for (int k = 0; k < 4; k++) {
                                int nextRow = cur[0] + move_row[k];
                                int nextCol = cur[1] + move_col[k];

                                if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
                                    if(intMap[nextRow][nextCol] > 0 && !visited[nextRow][nextCol]) {
                                        visited[nextRow][nextCol] = true;
                                        temp += intMap[nextRow][nextCol];
                                        queue.offer(new int[]{nextRow, nextCol});
                                    }
                                }
                            }
                        }
                        list.add(temp);
                    }
                }
            }

            if(list.size() == 0) {
                return new int[] {-1};
            }

            int[] answer = new int[list.size()];

            for(int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }

            Arrays.sort(answer);

            return answer;
        }
    }
}
