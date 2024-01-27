import java.io.*;
import java.util.*;

public class Softeer_6282 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int[][] map = new int[n][n];
        for(int i = 0; i < n; i++) {
            String[] input = bf.readLine().split("");
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        boolean[][] visited = new boolean[n][n];
        int[] move_row = new int[]{-1, 0, 1, 0};
        int[] move_col = new int[]{0, 1, 0, -1};
        ArrayList<Integer> answer = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                    int count = 1;
                    while(!queue.isEmpty()) {
                        int[] cur = queue.poll();

                        for(int k = 0; k < 4; k++) {
                            int nextRow = cur[0] + move_row[k];
                            int nextCol = cur[1] + move_col[k];

                            if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
                                if(!visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
                                    visited[nextRow][nextCol] = true;
                                    count++;
                                    queue.offer(new int[]{nextRow, nextCol});
                                }
                            }
                        }
                    }
                    answer.add(count);
                }
            }
        }

        Collections.sort(answer);

        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append("\n");

        for(int i = 0; i < answer.size(); i++) {
            sb.append(answer.get(i)).append("\n");
        }

        System.out.print(sb);
    }
}
