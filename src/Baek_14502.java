import java.util.*;
import java.io.*;

public class Baek_14502 {
    static int N,M;
    static int[][] lab;
    static int answer = 0;
    static int[] move_row = new int[]{-1, 0, 1, 0};
    static int[] move_col = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<M;j++){
                lab[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int depth) {
        if(depth == 3) {
            bfs();
            return;
        }
        for(int i = 0;i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(lab[i][j] == 0) {
                    lab[i][j] = 1;
                    dfs(depth + 1);
                    lab[i][j] = 0;
                }
            }
        }
    }

    static void bfs() {
        int[][] testMap = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0;i < N; i++) {
            for(int j = 0; j < M; j++) {
                testMap[i][j] = lab[i][j];
                if(testMap[i][j] == 2)
                    queue.offer(new int[]{i, j});
            }
        }

        while(!queue.isEmpty()) {
            int[] poll = queue.poll();

            for(int i=0; i<4; i++) {
                int nextRow = poll[0] + move_row[i];
                int nextCol = poll[1] + move_col[i];

                if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
                    if(testMap[nextRow][nextCol] == 0) {
                        testMap[nextRow][nextCol] = 2;
                        queue.offer(new int[]{nextRow, nextCol});
                    }
                }

            }
        }

        int count = 0;

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++){
                if(testMap[i][j] == 0)
                    count++;
            }
        }

        answer = Math.max(count, answer);
    }
}
