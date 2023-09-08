import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_10157 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(bf.readLine());

        int[][] map = new int[r + 1][c + 1];

        int[] move_row = new int[]{1, 0, -1, 0}; // 하, 우, 상, 좌
        int[] move_col = new int[]{0, 1, 0, -1};
        int dir = 0;

        if (k > c * r) {
            System.out.println(0);
            return;
        }

        int col = 1;
        int row = 0;

        for (int i = 1; i <= c * r; i++) {
            int nextRow = row + move_row[dir];
            int nextCol = col + move_col[dir];

            if (nextRow >= 1 && nextRow <= r && nextCol >= 1 && nextCol <= c) {
                if (map[nextRow][nextCol] == 0) {
                    map[nextRow][nextCol] = i;
                    row = nextRow;
                    col = nextCol;

                    if (i == k) {
                        System.out.println(col + " " + row);
                        return;
                    }
                } else {
                    dir = (dir + 1) % 4;
                    i--;
                }
            } else {
                dir = (dir + 1) % 4;
                i--;
            }
        }
    }
}