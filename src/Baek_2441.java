import java.io.*;

public class Baek_2441 {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(bf.readLine());

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                sb.append(" ");
            }
            for(int j = 0; j < n - i; j++) {
                sb.append("*");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
