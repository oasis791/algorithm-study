import java.io.*;
import java.util.*;

class Goorm_11 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if (n % b == 0) {
            System.out.println(n / b);
            return;
        }

        int count = n / b;
        int pain = n % b;
        if (pain % a == 0) {
            System.out.println(count + pain / a);
            return;
        }
        while (count > 0) {
            pain += b;
            count--;

            if (pain % a == 0) {
                count += pain / a;
                break;
            }
        }

        if (count != 0) {
            System.out.println(count);
        } else {
            System.out.println(-1);
        }
    }
}