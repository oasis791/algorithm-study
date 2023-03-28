import java.util.*;

public class Programmers_Park_Walking {
    class Solution {
        public int[] solution(String[] park, String[] routes) {
            int w = park[0].length();
            int h = park.length;
            int[] point = new int[]{0, 0};
            String[][] map = new String[h][w];
            int[] move_row = new int[]{-1, 0, 1, 0};
            int[] move_col = new int[]{0, 1, 0, -1};

            for (int i = 0; i < h; i++) {
                String[] parkRow = park[i].split("");
                for (int j = 0; j < w; j++) {
                    map[i][j] = parkRow[j];
                    if (map[i][j].equals("S")) {
                        point[0] = i;
                        point[1] = j;
                    }
                }
            }

            loop:
            for (int i = 0; i < routes.length; i++) {
                StringTokenizer st = new StringTokenizer(routes[i], " ");
                String op = st.nextToken();
                int n = Integer.parseInt(st.nextToken());
                int dir = 0; // 0: N, 1: E, 2: S, 3: W

                switch (op) {
                    case "N":
                        dir = 0;
                        break;
                    case "E":
                        dir = 1;
                        break;
                    case "S":
                        dir = 2;
                        break;
                    case "W":
                        dir = 3;
                        break;
                    default:
                        break;
                }

                int nextRow = point[0];
                int nextCol = point[1];
                for (int j = n; j > 0; j--) {
                    nextRow += move_row[dir];
                    nextCol += move_col[dir];
                    if (nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w)
                        continue loop;
                    if (map[nextRow][nextCol].equals("X"))
                        continue loop;
                }
                point[0] = nextRow;
                point[1] = nextCol;
            }
            return point;
        }
    }
}
