import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_5525 {
    private static int N, M;
    private static String[] S;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
        S = new String[M];
        S = bf.readLine().split("");

        int countI = 0;
        int countO = 0;
        int result = 0;
        boolean start = false;
        boolean before = false; // false가 0, true가 I

        for (int i = 0; i < M; i++) {
            if (!start) {
                if (S[i].equals("I")) {
                    start = true;
                    before = true;
                    countI++;
                }
            } else {
                if (before) {
                    if (S[i].equals("O")) {
                        countO++;
                        before = false;
                    } else {
                        countI = 0;
                        countO = 0;
                        start = false;
                    }
                } else {
                    if (S[i].equals("I")) {
                        countI++;
                        before = true;
                    } else {
                        countI = 0;
                        countO = 0;
                        start = false;
                    }
                }
                if (countI == N + 1 && countO == N) {
                    result++;
                    countO = 0;
                    countI = 0;
                    start = false;
                    before = false;
                }
            }
        }

        System.out.println(result);

    }
}
