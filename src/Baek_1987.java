import java.util.*;
import java.io.*;

public class Baek_1987 {
    static int r, c;
    static int[] move_row = new int[]{-1, 0, 1, 0};
    static int[] move_col = new int[]{0, 1, 0, -1};
    static char[][] map;
    static int answer = 0;
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for(int i = 0; i < r; i++) {
            map[i] = Arrays.copyOf(bf.readLine().toCharArray(), c);
        }

        boolean[] alphabets = new boolean[26];
        alphabets[map[0][0] - 65] = true;
        dfs(0, 0, 1, alphabets);
        System.out.println(answer);
    }

    static void dfs(int row, int col, int depth, boolean[] alphabets) {
        answer = Math.max(answer, depth);

        for(int i = 0 ; i < 4; i++) {
            int nextRow = row + move_row[i];
            int nextCol = col + move_col[i];

            if(nextRow >=0 && nextRow < r && nextCol >= 0 && nextCol < c) {
                if(!alphabets[map[nextRow][nextCol] - 65]) {
                    alphabets[map[nextRow][nextCol] - 65] = true;
                    dfs(nextRow, nextCol, depth + 1, alphabets);
                    alphabets[map[nextRow][nextCol] - 65] = false;
                }
            }
        }
    }
}
