import java.io.*;
import java.util.*;

public class Baek_13413 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());

        for(int t = 0; t < T; t++) {
            int n = Integer.parseInt(bf.readLine());
            int answer = 0;
            String input = bf.readLine();
            String target = bf.readLine();

            int black = 0;
            int white = 0;

            for(int i = 0; i < n; i++) {
                if(input.charAt(i) != target.charAt(i)) {
                    if(input.charAt(i) == 'B') {
                        black++;
                    } else {
                        white++;
                    }
                }
            }
            sb.append(Math.max(black, white)).append("\n");
        }
        System.out.print(sb);
    }
}
