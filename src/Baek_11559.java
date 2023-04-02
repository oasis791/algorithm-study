import java.util.*;
import java.io.*;

public class Baek_11559 {
    static Queue<int[]> queue = new LinkedList<>();
    static Queue<int[]> same = new LinkedList<>();
    static String[][] map = new String[12][6];
    static int[] move_row = new int[]{-1, 0, 1, 0};
    static int[] move_col = new int[]{0, 1, 0, -1};
    static int answer = 0;
    static boolean finished;

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 0; i < 12; i++) {
            String[] input = br.readLine().split("");
            map[i] = Arrays.copyOf(input, input.length);
        }

        while(true) {
            boolean[][] visited = new boolean[12][6];
            finished = true;
            answer++;
            // 4개 이상 모이면 점으로 변경
            for(int i = 11; i >= 0; i--) {
                for(int j = 0; j < 6; j++) {
                    if(!visited[i][j] && !map[i][j].equals(".")) {
                        queue.offer(new int[]{i, j});
                        bfs(visited);
                    }
                }
            }

            if (finished) {
                answer--;
                break;
            }

            // 내리기
            for(int j = 0; j < 6; j++) {
                int[] emptyPoint = new int[]{-1, -1};
                for(int i = 11; i >= 0; i--) {
                    if(emptyPoint[0] == -1 && emptyPoint[1] == -1) {
                        if(map[i][j].equals(".")) {
                            emptyPoint[0] = i;
                            emptyPoint[1] = j;
                        }
                    } else if (!map[i][j].equals(".")) {
                        map[emptyPoint[0]][emptyPoint[1]] = map[i][j];
                        map[i][j] = ".";
                        emptyPoint[0] -= 1;
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs(boolean[][] visited) {
        same.clear();
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            same.offer(new int[]{now[0], now[1]});
            visited[now[0]][now[1]] = true;

            for(int i = 0; i < 4; i++) {
                int nextRow = now[0] + move_row[i];
                int nextCol = now[1] + move_col[i];
                if(nextRow >= 0 && nextRow < 12 && nextCol >=0 && nextCol < 6) {
                    if(!visited[nextRow][nextCol] && map[nextRow][nextCol].equals(map[now[0]][now[1]])) {
                        queue.offer(new int[]{nextRow, nextCol});
                    }
                }
            }
        }

        if(same.size() >= 4) {
            finished = false;
            while (!same.isEmpty()) {
                int[] poll = same.poll();
                map[poll[0]][poll[1]] = ".";
            }
        }
    }
}
