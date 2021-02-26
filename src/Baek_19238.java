//19238번 스타트 택시
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_19238 {
    private static int N;
    private static int M;
    private static int fuel;
    private static int[] taxi;
    private static int[][] dist;
    private static ArrayList<int[]> passenger = new ArrayList<>();
    private static int[][] matrix;
    private static boolean[][] visited;
    private static int[] move_x = {-1, 0, 1, 0};
    private static int[] move_y = {0, 1, 0, -1};
    private static int index;
    private static Queue<int[]> q = new LinkedList<>();
    private static void bfs() {
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = temp[0] + move_x[i];
                int nextY = temp[1] + move_y[i];
                if ((nextX > 0 && nextX <= N && nextY > 0 && nextY <= N) && !visited[nextX][nextY] && matrix[nextX][nextY] != 1) {
                    dist[nextX][nextY] = dist[temp[0]][temp[1]] + 1;
                    visited[nextX][nextY] = true;
                    q.offer(new int[]{nextX, nextY});
                }
            }
        }
    }

    private static void shortestPath() {
        for (int i = 1; i < passenger.size(); i++) {
            // passenger의 값이 서로 같을 경우
            if (dist[passenger.get(index)[0]][passenger.get(index)[1]] == dist[passenger.get(i)[0]][passenger.get(i)[1]]) {
                if (passenger.get(index)[0] != passenger.get(i)[0]) {
                    //행이 다르면 행 비교
                    if (passenger.get(index)[0] > passenger.get(i)[0]) {
                        index = i;
                    }
                } else if (passenger.get(index)[1] != passenger.get(i)[1]) {
                    // 열이 다르면 열 비교
                    if (passenger.get(index)[1] > passenger.get(i)[1]) {
                        index = i;
                    }
                }
            } else if (dist[passenger.get(index)[0]][passenger.get(index)[1]] > dist[passenger.get(i)[0]][passenger.get(i)[1]]) {
                // passenger의 값이 서로 다르면 작은 값으로 이동
                index = i;
            }
        }

        // 벽때문에 승객까지 갈 수 없는 경우를 check하기 위함
        if (visited[passenger.get(index)[0]][passenger.get(index)[1]]) {
            visited = new boolean[N + 1][N + 1];
            q.offer(new int[]{passenger.get(index)[0], passenger.get(index)[1]});
            visited[passenger.get(index)[0]][passenger.get(index)[1]] = true;
            fuel -= dist[passenger.get(index)[0]][passenger.get(index)[1]];
        } else {
            System.out.println(-1);
            System.exit(0);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        matrix = new int[N + 1][N + 1];
        taxi = new int[2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(bf.readLine(), " ");
        taxi[0] = Integer.parseInt(st.nextToken());
        taxi[1] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            passenger.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        while (passenger.size() > 0) {
            visited = new boolean[N + 1][N + 1];
            dist = new int[N + 1][N + 1];
            q.offer(new int[]{taxi[0], taxi[1]});
            visited[taxi[0]][taxi[1]] = true;
            bfs();
            //passenger를 get할 때 선택한 index를 저장하기 위한 변수 index
            index = 0;
            shortestPath();
            dist = new int[N + 1][N + 1];
            bfs();
            // 벽 때문에 도착점에 갈 수 없는 경우를 check하기 위함
            if(visited[passenger.get(index)[2]][passenger.get(index)[3]]) {
                fuel -= dist[passenger.get(index)[2]][passenger.get(index)[3]];
            } else {
                System.out.println(-1);
                System.exit(0);
            }
            if (fuel < 0) {
                System.out.println(-1);
                System.exit(0);
            } else {
                taxi[0] = passenger.get(index)[2];
                taxi[1] = passenger.get(index)[3];
                fuel += 2 * dist[passenger.get(index)[2]][passenger.get(index)[3]];
                passenger.remove(index);
            }
        }
        System.out.println(fuel);
    }
}