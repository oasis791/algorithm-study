import java.util.*;
import java.io.*;

public class Baek_5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());

        loop:
        for(int t = 0; t < T; t++) {
            String p = bf.readLine();
            int n = Integer.parseInt(bf.readLine());
            int[] arr = new int[n];

            int start = 0;
            int end = n - 1;
            boolean isReversed = false;

            String input = bf.readLine();
            input = input.substring(1, input.length() - 1);
            StringTokenizer st = new StringTokenizer(input, ",");

            int index = 0;
            while(st.hasMoreTokens()) {
                arr[index++] = Integer.parseInt(st.nextToken());
            }

            for(int i = 0; i < p.length(); i++) {
                char op = p.charAt(i);

                if(op == 'R') {
                    int temp = start;
                    start = end;
                    end = temp;
                    isReversed = !isReversed;
                } else {
                    if(isReversed) {
                        if(start < end) {
                            sb.append("error").append("\n");
                            continue loop;
                        } else {
                            start--;
                        }
                    } else {
                        if(start > end) {
                            sb.append("error").append("\n");
                            continue loop;
                        } else {
                            start++;
                        }
                    }
                }
            }

            sb.append("[");
            int size = 0;
            if(isReversed) {
                for(int i = start; i >= end; i--) {
                    size++;
                    sb.append(arr[i]).append(",");
                }
            } else {
                for(int i = start; i <= end; i++) {
                    size++;
                    sb.append(arr[i]).append(",");
                }
            }
            if(size != 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]").append("\n");
        }

        System.out.println(sb);
    }
}
