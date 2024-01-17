import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

public class Programmers_250136 {
    static class Solution {
        static Queue<int[]> queue = new LinkedList<>();
        static int[] move_row = new int[]{-1, 0, 1, 0};
        static int[] move_col = new int[]{0, 1, 0, -1};
        static int n, m;
        static boolean[][] visited;
        public int solution(int[][] land) {
            n = land.length;
            m = land[0].length;
            HashMap<Integer, Integer> map = new HashMap<>();
            visited = new boolean[n][m];
            int[][] oilIndexMap = new int[n][m];
            int oilIndex = 1;

            // 1. 석유에 먼저 번호와 크기를 매겨놓자.
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(land[i][j] == 1 && !visited[i][j]) {
                        queue.offer(new int[]{i, j});
                        visited[i][j] = true;
                        oilIndexMap[i][j] = oilIndex;
                        map.put(oilIndex, bfs(land, oilIndex, oilIndexMap));
                        oilIndex++;
                    }
                }
            }

            // 2. 단순히 열을 하나씩 브루트포스해서, 만나는 석유 번호의 크기를 합해서 리턴하면 답 나올듯
            int answer = 0;
            for(int j = 0; j < m; j++) {
                boolean[] oilVisited = new boolean[map.size() + 1];
                int temp = 0;
                for(int i = 0; i < n; i++) {
                    if(land[i][j] == 1 && !oilVisited[oilIndexMap[i][j]]) {
                        oilVisited[oilIndexMap[i][j]] = true;
                        temp += map.get(oilIndexMap[i][j]);
                    }
                }

                answer = Math.max(answer, temp);
            }
            return answer;
        }

        static int bfs(int[][] land, int oilIndex, int[][] oilIndexMap) {
            int size = 1;

            while(!queue.isEmpty()) {
                int[] cur =  queue.poll();

                for(int i = 0; i < 4; i++) {
                    int nextRow = cur[0] + move_row[i];
                    int nextCol = cur[1] + move_col[i];

                    if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
                        if(land[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
                            size++;
                            visited[nextRow][nextCol] = true;
                            oilIndexMap[nextRow][nextCol] = oilIndex;
                            queue.offer(new int[]{nextRow, nextCol});
                        }
                    }
                }
            }

            return size;
        }
    }
}
