import java.util.ArrayList;
import java.util.Collections;

public class Programmers_150365 {
    static class Solution {
        static int[] move_row = new int[]{1, 0, 0, -1};
        static int[] move_col = new int[]{0, -1, 1, 0};
        static String answer;

        public static String solution(int n, int m, int x, int y, int r, int c, int k) {
            answer = null;
            int dist = distance(x, y, r, c);

            if ((k - dist) % 2 == 1 || k < dist)
                return "impossible";

            dfs(x, y, r, c, k, 0, new StringBuilder(), n, m);

            return answer == null ? "impossible" : answer;
        }

        static int distance(int x, int y, int r, int c) {
            return Math.abs(x - r) + Math.abs(y - c);
        }

        static void dfs(int row, int col, int targetRow, int targetCol, int k, int depth, StringBuilder sb, int n, int m) {
            if (answer != null) {
                return;
            }

            if (depth + distance(row, col, targetRow, targetCol) > k)
                return;

            if (depth == k) {
                if (row == targetRow && col == targetCol) {
                    answer = sb.toString();
                }
                return;
            }
            for (int i = 0; i < 4; i++) {
                if (answer != null)
                    return;
                int nextRow = row + move_row[i];
                int nextCol = col + move_col[i];

                if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= m) {
                    switch (i) {
                        case 0:
                            sb.append("d");
                            break;
                        case 1:
                            sb.append("l");
                            break;
                        case 2:
                            sb.append("r");
                            break;
                        case 3:
                            sb.append("u");
                            break;
                        default:
                            break;
                    }
                    dfs(nextRow, nextCol, targetRow, targetCol, k, depth + 1, sb, n, m);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }
}
