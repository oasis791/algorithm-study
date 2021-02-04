import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_1697 {
    private static int N;
    private static int K;
    private static int[] arr;
    private static int[] move = {1, -1, 2};
    private static Queue<Integer> Q = new LinkedList<>();
    private static boolean[] visited;
    private static boolean check;
    private static void bfs() {
        while (!Q.isEmpty()) {
            int temp = Q.poll();
            if (temp == K) {
                check = true;
                break;
            }
            if (!check) {
                for (int i = 0; i < 3; i++) {
                    int next;
                    if (i == 2) {
                        next = temp * move[2];
                    } else {
                        next = temp + move[i];
                    }
                    if (next < 0 || next > 100000)
                        continue;
                    if (!visited[next]) {
                        arr[next] = arr[temp] + 1;
                        visited[next] = true;
                        Q.offer(next);
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[100001];
        visited = new boolean[100001];
        arr[N] = 0;
        Q.offer(N);
        visited[N] = true;
        bfs();

        System.out.println(arr[K]);
    }
}
