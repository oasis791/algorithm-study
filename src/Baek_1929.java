import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1929 {
    static boolean[] prime = new boolean[1000000];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        prime[0] = true;
        prime[1] = true;
        makePrime();

        StringBuilder sb = new StringBuilder();
        for(; m <= n; m++) {
            if(!prime[m])
                sb.append(m).append("\n");
        }

        System.out.print(sb);
    }

    static void makePrime() {
        for(int i = 2; i < Math.sqrt(1000000); i++) {
            if(!prime[i]) {
                for(int j = i * i; j < 1000000; j = j + i) {
                    prime[j] = true;
                }
            }
        }
    }
}
