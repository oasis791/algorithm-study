import java.io.*;
import java.util.*;

public class Softeer_6284 {
    static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        long k = Long.parseLong(st.nextToken());
        long p = Long.parseLong(st.nextToken());
        long n = Long.parseLong(st.nextToken());

        long result = recursion(p, n);

        System.out.println(k * result % MOD);
    }

    static long recursion (long p, long n) {
        if (n == 1) {
            return p;
        }

        long cur = recursion(p, n / 2);

        if(n % 2 == 1) {
            return (cur * cur % MOD) * p % MOD;
        } else {
            return (cur * cur % MOD);
        }
    }
}
