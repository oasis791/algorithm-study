import java.io.*;
import java.util.*;

public class Baek_5014 {
    static int f, s, g, u, d;
    static int answer = -1;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        // total floors
        f = Integer.parseInt(st.nextToken());
        // current floor
        s = Integer.parseInt(st.nextToken());
        // start link floor
        g = Integer.parseInt(st.nextToken());
        // up
        u = Integer.parseInt(st.nextToken());
        // down
        d = Integer.parseInt(st.nextToken());

        visited = new boolean[f + 1];

        answer = bfs();
        if (answer == -1) {
            System.out.println("use the stairs");
        } else {
            System.out.println(answer);
        }
    }

    static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        visited[s] = true;

        int size = 0;
        int sum = -1;
        while (!queue.isEmpty()) {
            size = queue.size();
            sum++;
            for (int i = 0; i < size; i++) {
                int curStair = queue.poll();

                if (curStair == g) {
                    return sum;
                }

                if (curStair + u <= f && !visited[curStair + u]) {
                    queue.offer(curStair + u);
                    visited[curStair + u] = true;
                }

                if (curStair - d > 0 && !visited[curStair - d]) {
                    queue.offer(curStair - d);
                    visited[curStair - d] = true;
                }
            }
        }

        return -1;
    }
}
