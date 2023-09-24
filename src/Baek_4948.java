import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_4948 {
    static final int limit = 123456 * 2 + 1;
    static boolean[] prime = new boolean[123456 * 2 + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        init();

        while (true) {
            int n = Integer.parseInt(bf.readLine());
            if (n == 0) break;

            int count = 0;

            for (int i = n + 1; i <= n * 2; i++) {
                if (!prime[i])
                    count++;
            }
            sb.append(count).append("\n");
        }

        System.out.print(sb);
    }

    static void init() {
        prime[0] = prime[1] = true;

        for (int i = 2; i * i < limit; i++) {
           if(!prime[i]) {
               for (int j = i * i; j < limit; j += i) {
                   prime[j] = true;
               }
           }
        }
    }
}