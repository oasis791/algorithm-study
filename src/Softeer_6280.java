import java.io.*;
import java.util.*;

public class Softeer_6280 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] round = new int[n + 1];
        round[0] = 4;
        int side = 2;
        for(int i = 1; i <= n; i++) {
            round[i] = round[i - 1] * 4 - (4 * side) + 1;
            side = side * 2 - 1;
        }

        System.out.println(round[n]);
    }
}
