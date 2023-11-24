import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_3055 {
    static int[] move_row = new int[]{-1, 0, 1, 0};
    static int[] move_col = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        char[][] map = new char[r][c];
        Queue<int[]> water = new LinkedList<>();
        Queue<int[]> subPoint = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            String temp = bf.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = temp.charAt(j);
                if (map[i][j] == '*') {
                    water.offer(new int[]{i, j});
                } else if (map[i][j] == 'S') {
                    subPoint.offer(new int[]{i, j, 0});
                }
            }
        }

        boolean[][] gosum = new boolean[r][c];
        int[] peek = subPoint.peek();
        gosum[peek[0]][peek[1]] = true;

        int time = 0;
        while (!subPoint.isEmpty()) {
            time++;
            int size = subPoint.size();
            for (int i = 0; i < size; i++) {
                int[] cur = subPoint.poll();
                if (map[cur[0]][cur[1]] == '*') {
                    continue;
                }
                for (int j = 0; j < 4; j++) {
                    int nextRow = cur[0] + move_row[j];
                    int nextCol = cur[1] + move_col[j];

                    if (nextRow >= 0 && nextRow < r && nextCol >= 0 && nextCol < c) {
                        if (!gosum[nextRow][nextCol] && map[nextRow][nextCol] != '*' && map[nextRow][nextCol] != 'X') {
                            if (map[nextRow][nextCol] == 'D') {
                                System.out.println(time);
                                return;
                            }
                            gosum[nextRow][nextCol] = true;
                            subPoint.offer(new int[]{nextRow, nextCol, cur[2] + 1});
                        }
                    }
                }
            }

            int size2 = water.size();
            for (int i = 0; i < size2; i++) {
                int[] cur = water.poll();
                for (int j = 0; j < 4; j++) {
                    int nextRow = cur[0] + move_row[j];
                    int nextCol = cur[1] + move_col[j];

                    if (nextRow >= 0 && nextRow < r && nextCol >= 0 && nextCol < c) {
                        if (map[nextRow][nextCol] == '.') {
                            map[nextRow][nextCol] = '*';
                            water.offer(new int[]{nextRow, nextCol});
                        }
                    }
                }
            }
        }

        System.out.println("KAKTUS");
    }
}
