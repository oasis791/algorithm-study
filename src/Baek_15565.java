import java.io.*;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Baek_15565 {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int answer = Integer.MAX_VALUE;

        Queue<Integer> queue = new LinkedList<>();
        st = new StringTokenizer(bf.readLine());
        int ryanCount = 0;
        int start = 0;
        boolean check = false;

        for (int i = 0; i < n; i++) {
            int now = Integer.parseInt(st.nextToken());

            if(now == 1) {
                ryanCount++;
                if(!check) {
                    start = i;
                    check = true;
                } else {
                    queue.offer(i);
                }

                if(ryanCount == k) {
                    int nextRyan = queue.poll();
                    answer = Math.min(answer, i - start + 1);
                    start = nextRyan;
                    ryanCount--;
                }
            }
        }

        if(answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
