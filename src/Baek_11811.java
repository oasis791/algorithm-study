import java.util.*;
import java.io.*;

public class Baek_11811 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(bf.readLine());

        for(int i = 0; i < n; i++) {
            int a = 0;
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            for(int j = 0; j < n; j++) {
                int m = Integer.parseInt(st.nextToken());
                a = a|m;
            }
            sb.append(a).append(" ");
        }

        System.out.println(sb);
    }
}
