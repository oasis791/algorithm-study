import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Baek_1417 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int dasom = Integer.parseInt(bf.readLine());
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n - 1; i++) {
            pq.offer(Integer.parseInt(bf.readLine()));
        }

        while (!pq.isEmpty() && pq.peek() >= dasom) {
            dasom += 1;
            answer++;
            pq.offer(pq.poll() - 1);
        }

        System.out.println(answer);
    }
}
