import java.io.*;
import java.util.StringTokenizer;

public class Baek_1629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        bw.write(String.valueOf(pow(A, B, C)));
        bw.flush();
        bw.close();
        br.close();
    }

    static long pow(long num, long n, long c) {
        if (n == 1) {
            return num % c;
        }
        long tmp = pow(num, n / 2, c);
        if (n % 2 == 0) {
            return tmp * tmp % c;
        } else {
            return (tmp * tmp % c) * num % c;
        }
    }
}
