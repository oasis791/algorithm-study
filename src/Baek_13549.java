import java.util.*;
import java.io.*;

public class Baek_13549 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[400001];
        Arrays.fill(arr, Integer.MAX_VALUE);

        arr[n] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);

        while(!queue.isEmpty()) {
            int now = queue.poll();
            int prev = now - 1;
            int next = now + 1;
            int twice = now * 2;

            if(prev >= 0) {
                if(arr[prev] > arr[now] + 1) {
                    arr[prev] = arr[now] + 1;
                    queue.offer(prev);
                }
            }

            if(next < k * 2) {
                if(arr[next] > arr[now] + 1) {
                    arr[next] = arr[now] + 1;
                    queue.offer(next);
                }
                
            }

            if(twice < k * 2) {
                if(arr[twice] > arr[now]) {
                    arr[twice] = arr[now];
                    queue.offer(twice);
                }
            }
        }

        System.out.println(arr[k]);
    }
}
