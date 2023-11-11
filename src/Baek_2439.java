import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2439 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        int blank = n - 1;
        int star = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < blank; j++) {
                sb.append(" ");
            }
            blank--;

            for (int j = 0; j < star; j++) {
                sb.append("*");
            }
            star++;
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
