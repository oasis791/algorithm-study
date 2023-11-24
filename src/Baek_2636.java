import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2636 {
    static int[] move_row = new int[]{-1, 0, 1, 0};
    static int[] move_col = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int remainCheese = 0;
        int[][] map = new int[n][m];
        Queue<int[]> deleteCheeseQueue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    remainCheese++;
                }
            }
        }

        int temp = 0;
        int time = 0;

        while (remainCheese > 0) {
            time++;
            temp = remainCheese;

            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[n][m];
            visited[0][0] = true;
            queue.offer(new int[]{0, 0});

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int nextRow = cur[0] + move_row[j];
                    int nextCol = cur[1] + move_col[j];

                    if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
                        if (!visited[nextRow][nextCol]) {
                            if (map[nextRow][nextCol] == 0) {
                                queue.offer(new int[]{nextRow, nextCol});
                            } else {
                                deleteCheeseQueue.offer(new int[]{nextRow, nextCol});
                            }
                            visited[nextRow][nextCol] = true;
                        }
                    }
                }
            }

            remainCheese -= deleteCheeseQueue.size();
            while (!deleteCheeseQueue.isEmpty()) {
                int[] cur = deleteCheeseQueue.poll();
                map[cur[0]][cur[1]] = 0;
            }
        }

        System.out.println(time);
        System.out.println(temp);
    }
}
