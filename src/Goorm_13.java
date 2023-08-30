import java.io.*;
import java.util.*;

public class Goorm_13 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int max = 0;
        int answer = 0;
        int[][] map = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        for(int i = 0; i < n ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        int[] complex = new int[max + 1];
        Queue<int[]> queue = new LinkedList<>();
        int[] move_row = new int[]{-1, 0, 1, 0};
        int[] move_col = new int[]{0, 1, 0, -1};

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j]) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                    int target = map[i][j];
                    int count = 0;
                    while(!queue.isEmpty()) {
                        count++;
                        int[] cur = queue.poll();

                        for(int l = 0; l < 4; l++) {
                            int nextRow = cur[0] + move_row[l];
                            int nextCol = cur[1] + move_col[l];

                            if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
                                if(map[nextRow][nextCol] == target && !visited[nextRow][nextCol]) {
                                    visited[nextRow][nextCol] = true;
                                    queue.offer(new int[]{nextRow, nextCol});
                                }
                            }
                        }
                    }

                    if(count >= k) {
                        complex[target]++;
                    }
                }
            }
        }

        int temp = 0;
        for (int i = 1; i < complex.length; i++) {
            if(temp <= complex[i]) {
                answer = i;
                temp = complex[i];
            }
        }

        System.out.println(answer);
    }
}