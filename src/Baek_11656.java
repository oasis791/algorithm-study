import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Baek_11656 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(bf.readLine());
        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.offer(sb.toString());

        while (sb.length() > 0) {
            sb.deleteCharAt(0);
            pq.offer(sb.toString());
        }

        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }
        sb.deleteCharAt(0);
        System.out.print(sb);
    }
}
