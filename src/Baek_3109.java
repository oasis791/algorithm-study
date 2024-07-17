import java.io.*;
import java.util.*;

public class Baek_3109 {
    static int r, c;
    static boolean[][] map;
    static int[] move_row = new int[]{-1, 0, 1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new boolean[r][c]; // true: 벽, false: 빈공간

        for (int i = 0; i < r; i++) {
            String s = bf.readLine();
            for (int j = 0; j < c; j++) {
                if (s.charAt(j) == 'x')
                    map[i][j] = true;
            }
        }

        for (int i = 0; i < r; i++) {
            if (check(i, 0))
                answer++;
        }
        System.out.println(answer);
    }

    static boolean check(int row, int col) {
        map[row][col] = true;

        if (col == c - 1) {
            return true;
        }

        if (row > 0 && !map[row - 1][col + 1])
            return check(row - 1, col + 1);
        if (!map[row][col + 1])
            return check(row, col + 1);
        if (row + 1 < r && !map[row + 1][col + 1])
            return check(row + 1, col + 1);
        return false;
    }
}
