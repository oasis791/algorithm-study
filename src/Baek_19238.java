//19238번 스타트 택시
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_19238 {
    private static int N;
    private static int M;
    private static int fuel;
    private static int[] taxi;
    private static int[][] dist;
    private static int[][] passenger;
    private static int[][] matrix;
    private static boolean[][] visited;
    private static int result = 0;
    private static Queue<int[]> q = new LinkedList<>();
    private static void distInit() {

    }
    private static void bfs() {

    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];
        dist = new int[N][M];
        passenger = new int[M][4];
        taxi = new int[2];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(bf.readLine(), " ");
        taxi[0] = Integer.parseInt(st.nextToken());
        taxi[1] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            passenger[i][0] = Integer.parseInt(st.nextToken()); //0,1 승객 출발점
            passenger[i][1] = Integer.parseInt(st.nextToken());
            passenger[i][2] = Integer.parseInt(st.nextToken()); //2,3 승객 도착점
            passenger[i][3] = Integer.parseInt(st.nextToken());
        }
        distInit();
        bfs();
        System.out.println(result);
    }
}