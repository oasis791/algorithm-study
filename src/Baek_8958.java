import java.io.*;
import java.util.*;

public class Baek_8958 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        for(int t = 0; t < T; t++) {
            String input = bf.readLine();

            int sum = 0;
            char before = 'T';
            int target = 1;
            for(int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if(c == 'O') {
                    if(before == 'O') {
                        sum += ++target;
                    } else {
                        before = 'O';
                        sum += 1;
                        target = 1;
                    }
                } else {
                    before = 'X';
                }
            }
            sb.append(sum).append("\n");
        }

        System.out.print(sb);
    }
}
