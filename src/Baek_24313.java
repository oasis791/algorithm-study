import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_24313 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int a1 = Integer.parseInt(st.nextToken());
        int a2 = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(bf.readLine());
        int n = Integer.parseInt(bf.readLine());

        for (int i = n; i <= n + 1000; i++) {
            if(a1 * i + a2 > c * i) {
                System.out.println(0);
                return;
            }
        }

        System.out.println(1);
    }
}
