import java.io.*;
import java.util.*;

public class Baek_17129 {
    static int[] move_row = new int[]{-1, 0, 1, 0};
    static int[] move_col = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Queue<int[]> queue = new LinkedList<>();

        int[][] map = new int[n][m];
        int[][] dist = new int[n][m];
        int answer = Integer.MAX_VALUE;
        int answerMenu = 0;

        for(int i = 0; i < n; i++) {
            String[] input = bf.readLine().split("");
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(input[j]);

                if (map[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            for(int i = 0; i < 4; i++) {
                int nextRow = now[0] + move_row[i];
                int nextCol = now[1] + move_col[i];

                if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
                    if(map[nextRow][nextCol] != 1 && map[nextRow][nextCol] != 2) {
                        if((dist[nextRow][nextCol] == 0) || (dist[nextRow][nextCol] > dist[now[0]][now[1]] + 1)) {
                            dist[nextRow][nextCol] = dist[now[0]][now[1]] + 1;

                            if(map[nextRow][nextCol] >= 3) {
                                if(answer > dist[nextRow][nextCol]) {
                                    answer = dist[nextRow][nextCol];
                                    answerMenu = map[nextRow][nextCol];
                                }
                            }
                            queue.offer(new int[]{nextRow, nextCol});
                        }                     
                    }
                }
            }
        }

        if(answerMenu == 0) {
            System.out.println("NIE");
        } else {
            System.out.println("TAK");
            System.out.println(answer);
        }
    }
}

