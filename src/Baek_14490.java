//14490번 백대열
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_14490 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int A = 0;
        int B = 0;
        String input = bf.readLine();
        StringTokenizer st = new StringTokenizer(input, ":");
        for (int i = 0; i < 2; i++) {
            switch (i) {
                case 0:
                    A = Integer.parseInt(st.nextToken());
                    break;
                case 1:
                    B = Integer.parseInt(st.nextToken());
                    break;
                default:
                    break;
            }
        }
        int max = 0;
        int min = Math.min(A, B);
        for (int i = 1; i <= min; i++) {
            if (A % i == 0 && B % i == 0) {
                max = i;
            }
        }
        System.out.println(A/max+":"+B/max);
    }
}
