import java.io.*;
import java.util.*;

public class Goorm_7 {
    static int[] move_row = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] move_col = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
    static int answer = 0;
    static int n, k;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int count = 0;
                if(map[i][j] == 1)
                    continue;

                for(int k = 0; k < 8; k++) {
                    int nextRow = move_row[k] + i;
                    int nextCol = move_col[k] + j;

                    if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
                        if(map[nextRow][nextCol] == 1)
                            count++;
                    }
                }

                if(count == k)
                    answer++;
            }
        }

        System.out.println(answer);
    }
}