import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        long[] roads = new long[N - 1];
        long[] prices = new long[N - 1];
        long answer = 0;

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N - 1; i++) {
            long road = Long.parseLong(st.nextToken());
            roads[i] = road;
        }

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N - 1; i++) {
            long price = Long.parseLong(st.nextToken());
            prices[i] = price;
        }

        for (int i = 0; i < N - 1; i++) {
            long sum = roads[i];
            int j = i + 1;
            for (; j < N - 1; j++) {
                if (prices[i] <= prices[j]) {
                    sum += roads[j];
                } else {
                    break;
                }
            }
            answer += sum * prices[i];
            i = j - 1;
        }
        System.out.println(answer);
    }
}
