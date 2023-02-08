import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Baek_1715 {
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int answer = 0;
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(bf.readLine()));
        }

        while (pq.size() > 1) {
            Integer now = pq.poll();
            Integer next = pq.poll();
            answer += now + next;
            pq.add(now + next);
        }

        System.out.println(answer);
    }
}
