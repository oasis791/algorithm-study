//2164번 카드2
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek_2164 {
    private static int N;
    private static Deque<Integer> dq = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        if(N==1)
            System.out.println("1");
        else {
            int temp = 0;
            for (int i = 1; i <= N; i++) {
                dq.offer(i);
            }
            int count = 1;
            while (dq.size() != 1) {
                if (count % 2 != 0) {
                    dq.pollFirst();
                } else {
                    temp = dq.pollFirst();
                    dq.offerLast(temp);
                }
                count++;
            }
            System.out.println(dq.poll());
        }
    }
}
