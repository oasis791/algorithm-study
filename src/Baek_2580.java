import java.io.*;
import java.util.*;

public class Baek_2580 {
    static int[][] map = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku(0, 0);
    }

    static void sudoku(int row, int col) {
        if (col == 9) {
            sudoku(row + 1, 0);
            return;
        }

        if (row == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }

        if(map[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (possible(row, col, i)) {
                    map[row][col] = i;
                    sudoku(row, col + 1);
                }
            }
            map[row][col] = 0;
            return;
        }

        sudoku(row, col + 1);
    }

    static boolean possible(int row, int col, int num) {

        for(int i = 0; i < 9; i++) {
            if(map[row][i] == num) {
                return false;
            }
        }

        for(int i = 0; i < 9; i++) {
            if(map[i][col] == num) {
                return false;
            }
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(map[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
