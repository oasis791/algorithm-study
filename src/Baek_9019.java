import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_9019 {
    private static int T;
    private static int A;
    private static int B;
    private static String[] dslr = {"D", "S", "L", "R"};
    private static boolean[] visited = new boolean[10000];
    private static String[] result = new String[10000];
    private static Queue<Integer> q;
    private static boolean check;
    private static void bfs(){
        while (!q.isEmpty()) {
            int temp = q.poll();
            if (temp == B) {
                check = true;
                break;
            }
            if (!check) {
                int next;
                for (int i = 0; i < 4; i++) {
                    switch (dslr[i]) {
                        case "D":
                            next = temp * 2;
                            if (next > 9999) {
                                next %= 10000;
                            }
                            if(!visited[next]) {
                                visited[next] = true;
                                result[next] = result[temp] + "D";
                                q.offer(next);
                            }
                            break;
                        case "S":
                            next = temp - 1;
                            if (next == -1) {
                                next = 9999;
                            }
                            if(!visited[next]) {
                                visited[next] = true;
                                result[next] = result[temp] + "S";
                                q.offer(next);
                            }
                            break;
                        case "L":
                            next = temp % 1000 * 10 + temp / 1000;
                            if(!visited[next]) {
                                visited[next] = true;
                                result[next] = result[temp] + "L";
                                q.offer(next);
                            }
                            break;
                        case "R":
                            next = temp % 10 * 1000 + temp / 10;
                            if(!visited[next]) {
                                visited[next] = true;
                                result[next] = result[temp] + "R";
                                q.offer(next);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            q = new LinkedList<>();
            Arrays.fill(visited, false);
            Arrays.fill(result,"");
            check = false;
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            visited[A] = true;
            q.offer(A);
            bfs();
            System.out.println(result[B]);
        }
    }
}