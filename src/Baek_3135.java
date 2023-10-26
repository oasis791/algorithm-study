import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_3135 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(bf.readLine());

        int answer = 0;

        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(bf.readLine());

            if(Math.abs(a - b) > Math.abs(b - temp)) {
                a = temp;
                answer = 1;
            }
        }

        System.out.println(answer + Math.abs(b - a));
    }
}
