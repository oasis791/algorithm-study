import java.io.*;
import java.util.*;

public class Goorm_9 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int answer = 0;

        int[][][] map = new int[n + 1][n + 1][2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());

            for (int j = 1; j <= n; j++) {
                String temp = st.nextToken();
                if (temp.equals("@")) {
                    map[i][j][0] = 0;
                    map[i][j][1] = 1;
                } else if (temp.equals("#")) {
                    map[i][j][0] = -1;
                }
            }
        }

        int[] move_row = new int[]{-1, 0, 1, 0};
        int[] move_col = new int[]{0, 1, 0, -1};

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(bf.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            if (map[row][col][0] != -1) {
                if (map[row][col][1] == 1) {
                    map[row][col][0] += 2;
                } else {
                    map[row][col][0]++;
                }
            }

            answer = Math.max(answer, map[row][col][0]);

            for (int j = 0; j < 4; j++) {
                int nextRow = row + move_row[j];
                int nextCol = col + move_col[j];

                if (nextRow > 0 && nextRow <= n && nextCol > 0 && nextCol <= n) {
                    if (map[nextRow][nextCol][0] >= 0) {
                        if (map[nextRow][nextCol][1] == 1) {
                            map[nextRow][nextCol][0] += 2;
                        } else {
                            map[nextRow][nextCol][0]++;
                        }

                        answer = Math.max(answer, map[nextRow][nextCol][0]);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}