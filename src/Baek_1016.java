import java.util.*;
import java.io.*;

public class Baek_1016 {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());
        
        long[] mu = new long[1000001];
        makeMuFunc(mu);
        System.out.println(mertensFunc(max, mu) - mertensFunc(min - 1, mu));
    }
    static void makeMuFunc(long[] mu) {
        mu[1] = 1;
        for(int i = 1; i < 1000001; i++) {
            for(int j = 2 * i; j < 1000001; j += i) {
                mu[j] -= mu[i];
            }
        }
    }

    static long mertensFunc(long num, long[] mu) {
        long count = 0;
        for(long i = 1; i * i <= num; i++) {
            count += mu[(int)i] * (num / (i * i));
        }

        return count;
    }
}
