import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_4179 {
    static int[] move_row = new int[]{-1, 0, 1, 0};
    static int[] move_col = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        char[][] map = new char[r][c];
        Queue<int[]> jihoon = new LinkedList<>();
        Queue<int[]> fire = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            String input = bf.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'J') {
                    jihoon.offer(new int[]{i, j, 0});
                } else if (map[i][j] == 'F') {
                    fire.offer(new int[]{i, j});
                }
            }
        }

        boolean[][] visited = new boolean[r][c];
        int[] init = jihoon.peek();
        visited[init[0]][init[1]] = true;

        while (!jihoon.isEmpty()) {
            int size = jihoon.size();
            for (int i = 0; i < size; i++) {
                int[] cur = jihoon.poll();
                if (map[cur[0]][cur[1]] == 'F')
                    continue;

                if (cur[0] == 0 || cur[1] == 0 || cur[0] == r - 1 || cur[1] == c - 1) {
                    System.out.println(cur[2] + 1);
                    return;
                }

                for (int j = 0; j < 4; j++) {
                    int nextRow = cur[0] + move_row[j];
                    int nextCol = cur[1] + move_col[j];

                    if (nextRow >= 0 && nextRow < r && nextCol >= 0 && nextCol < c) {
                        if (!visited[nextRow][nextCol] && map[nextRow][nextCol] == '.') {
                            visited[nextRow][nextCol] = true;
                            jihoon.offer(new int[]{nextRow, nextCol, cur[2] + 1});
                        }
                    }
                }
            }

            size = fire.size();
            for (int i = 0; i < size; i++) {
                int[] cur = fire.poll();

                for (int j = 0; j < 4; j++) {
                    int nextRow = cur[0] + move_row[j];
                    int nextCol = cur[1] + move_col[j];

                    if (nextRow >= 0 && nextRow < r && nextCol >= 0 && nextCol < c) {
                        if (map[nextRow][nextCol] == '.' || map[nextRow][nextCol] == 'J') {
                            map[nextRow][nextCol] = 'F';
                            fire.offer(new int[]{nextRow, nextCol});
                        }
                    }
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}
