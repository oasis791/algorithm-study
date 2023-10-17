import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Baek_2161 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(bf.readLine());
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            dq.offerFirst(i);
        }

        while (dq.size() > 1) {
            sb.append(dq.pollLast()).append(" ");
            dq.offerFirst(dq.pollLast());
        }
        sb.append(dq.poll());
        System.out.println(sb);
    }
}
