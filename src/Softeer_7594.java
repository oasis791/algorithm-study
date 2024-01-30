import java.io.*;
import java.util.*;

public class Softeer_7594 {
    static int answer = 0;
    static int[] move_row = new int[]{-1, 0, 1, 0};
    static int[] move_col = new int[]{0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] map = new int[n][n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0, n, new boolean[n][n], map);

        System.out.println(answer);
    }

    static void dfs(int depth, int count, int sum, int n, boolean[][] visited, int[][] map) {
        if(depth == 4 || count >= (n * n)) {
            answer = Math.max(answer, sum);
            return;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j]) {
                    for(int k = 0; k < 4; k++) {
                        int nextRow = i + move_row[k];
                        int nextCol = j + move_col[k];

                        if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
                            if(!visited[nextRow][nextCol]) {
                                visited[i][j] = true;
                                visited[nextRow][nextCol] = true;
                                dfs(depth + 1, count + 2,sum + map[i][j] + map[nextRow][nextCol], n, visited, map);
                                visited[nextRow][nextCol] = false;
                                visited[i][j] = false;
                            }
                        }
                    }
                }
            }
        }
    }
}

