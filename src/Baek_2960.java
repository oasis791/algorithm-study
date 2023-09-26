import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2960 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int answer = 0;

        boolean[] prime = new boolean[n + 1];

        for (int i = 2; i <= n; i++) {
            if(prime[i]) continue;

            for (int j = i; j <= n; j += i) {
                if(!prime[j]) {
                    answer++;
                    if (answer == k) {
                        System.out.println(j);
                        return;
                    }
                    prime[j] = true;
                }
            }
        }
    }
}
