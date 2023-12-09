import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2083 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String name = st.nextToken();
            if (name.equals("#")) {
                System.out.print(sb);
                return;
            }
            sb.append(name).append(" ");
            int age = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (age > 17 || weight >= 80) {
                sb.append("Senior").append("\n");
            } else {
                sb.append("Junior").append("\n");
            }
        }
    }
}