import java.util.*;
import java.io.*;

public class Baek_16234 {
    static int n, l, r;
    static int[][] map;
    static ArrayList<int[]> list;
    static boolean[][] visit;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(move());

    }

    static int move() { // 인구이동 발생일 수 구하기
        int result = 0;
        while (true) {
            visit = new boolean[n][n];
            boolean isMove = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visit[i][j]) {
                        int sum = bfs(i, j);// 연합찾기
                        if (list.size() > 1) {
                            changePopulation(sum);
                            isMove = true;
                        }
                    }
                }
            }
            if (!isMove)
                return result;
            result++;
        }

    }

    static void changePopulation(int sum) {
        int avg = sum / list.size();
        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i)[0];
            int y = list.get(i)[1];
            map[x][y] = avg;
        }
    }

    static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        list = new ArrayList<>();

        q.add(new int[]{x, y});
        list.add(new int[]{x, y});
        visit[x][y] = true;
        int sum = map[x][y];

        while (!q.isEmpty()) {
            int[] t = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = t[0] + dx[i];
                int ny = t[1] + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (!visit[nx][ny]) {
                        int tmp = Math.abs(map[t[0]][t[1]] - map[nx][ny]);
                        if (tmp >= l && tmp <= r) {
                            visit[nx][ny] = true;
                            q.add(new int[]{nx, ny});
                            list.add(new int[]{nx, ny});
                            sum += map[nx][ny];
                        }

                    }
                }
            }
        }
        return sum;
    }
}