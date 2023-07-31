import java.io.*;
import java.util.*;

public class Baek_1120 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        String a = st.nextToken();
        String b = st.nextToken();

        int answer = Integer.MAX_VALUE;

        for(int i = 0; i <= b.length() - a.length(); i++) {
            int count = 0;
            for(int j = 0; j < a.length(); j++) {
                if(a.charAt(j) != b.charAt(i + j)) {
                    count++;
                }
            }

            answer = Math.min(count, answer);
        }

        System.out.println(answer);
    }
}
