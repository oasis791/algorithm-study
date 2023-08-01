import java.io.*;
import java.util.*;

public class Baek_17609 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());

        for(int t = 0; t < T; t++) {
            String input = bf.readLine();
            int start = 0;
            int end = input.length() - 1;

            if(check(input, start, end, 0)) {
                sb.append(0).append("\n");
                continue;
            } else if(check(input, start, end, 1)) {
                sb.append(1).append("\n");
            } else {
                sb.append(2).append("\n");
            }

        }

        System.out.print(sb);
    }

    static boolean check(String input, int start, int end, int index) {
        if(index == 0) {
            while(start <= end) {
                if(input.charAt(start) != input.charAt(end)) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        } else {
            while(start <= end) {
                if(input.charAt(start) != input.charAt(end)) {
                    boolean a = check(input, start + 1, end, 0);
                    boolean b = check(input, start, end - 1, 0);

                    if(!a && !b) {
                        return false;
                    } else {
                        return true;
                    }
                }

                start++;
                end--;
            }
            return true;
        }
    }
}
