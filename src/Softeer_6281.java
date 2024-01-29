import java.io.*;
import java.util.*;

public class Softeer_6281 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[n][m]; // true : ice, false: blank
        int[] move_row = new int[]{-1, 0, 1, 0};
        int[] move_col = new int[]{0, 1, 0, -1};
        int iceCount = 0;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m;j++) {
                if(Integer.parseInt(st.nextToken()) == 1) {
                    map[i][j] = true;
                    iceCount++;
                }
            }
        }

        int answer = 0;
        while(iceCount > 0) {
            answer++;

            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[n][m];
            int[][] count = new int[n][m];
            queue.offer(new int[]{0, 0});
            visited[0][0] = true;

            while(!queue.isEmpty()) {
                int[] cur = queue.poll();

                for(int i = 0; i < 4; i++) {
                    int nextRow = cur[0] + move_row[i];
                    int nextCol = cur[1] + move_col[i];
                    if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
                        if(!visited[nextRow][nextCol]) {
                            if(map[nextRow][nextCol]) {
                                count[nextRow][nextCol]++;
                            } else {
                                queue.offer(new int[]{nextRow, nextCol});
                                visited[nextRow][nextCol] = true;
                            }
                        }
                    }
                }
            }

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(count[i][j] >= 2) {
                        map[i][j] = false;
                        iceCount--;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
