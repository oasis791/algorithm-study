import java.io.*;
import java.util.*;

public class Goorm_10 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        String[][] map = new String[n][n];
        boolean[][] gVisited = new boolean[n][n];
        boolean[][] pVisited = new boolean[n][n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int[] goorm = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
        st = new StringTokenizer(bf.readLine());
        int[] player = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken();
            }
        }

        int gCount = 1;
        int pCount = 1;

        gVisited[goorm[0]][goorm[1]] = true;
        pVisited[player[0]][player[1]] = true;

        loop:
        while (true) {
            String cur = map[goorm[0]][goorm[1]];
            int move = Integer.parseInt(cur.substring(0, cur.length() - 1));
            char op = cur.charAt(cur.length() - 1);

            int[] dir = converter(op);
            int count = 0;
            int nextRow = goorm[0];
            int nextCol = goorm[1];

            while (count < move) {
                nextRow = indexConverter(nextRow + dir[0], n);
                nextCol = indexConverter(nextCol + dir[1], n);


                if (gVisited[nextRow % n][nextCol % n]) {
                    break loop;
                } else {
                    gVisited[nextRow % n][nextCol % n] = true;
                    gCount++;
                    count++;
                }
            }

            goorm[0] = nextRow;
            goorm[1] = nextCol;
        }

        loop:
        while (true) {
            String cur = map[player[0]][player[1]];
            int move = Integer.parseInt(cur.substring(0, cur.length() - 1));
            char op = cur.charAt(cur.length() - 1);

            int[] dir = converter(op);
            int count = 0;
            int nextRow = player[0];
            int nextCol = player[1];

            while (count < move) {
                nextRow = indexConverter(nextRow + dir[0], n);
                nextCol = indexConverter(nextCol + dir[1], n);


                if (pVisited[nextRow][nextCol]) {
                    break loop;
                } else {
                    pVisited[nextRow][nextCol] = true;
                    pCount++;
                    count++;
                }
            }

            player[0] = nextRow;
            player[1] = nextCol;
        }

        if (gCount > pCount) {
            System.out.print("goorm ");
            System.out.println(gCount);
        } else {
            System.out.print("player ");
            System.out.println(pCount);
        }
    }

    static int[] converter(char op) {
        int[] move_row = new int[]{-1, 0, 1, 0};
        int[] move_col = new int[]{0, 1, 0, -1};

        if (op == 'L') {
            return new int[]{move_row[3], move_col[3]};
        } else if (op == 'R') {
            return new int[]{move_row[1], move_col[1]};
        } else if (op == 'U') {
            return new int[]{move_row[0], move_col[0]};
        } else {
            return new int[]{move_row[2], move_col[2]};
        }
    }

    static int indexConverter(int index, int n) {
        if(index < 0) {
            return index + n;
        } else if (index >= n) {
            return index - n;
        } else {
            return index;
        }
    }
}