import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1380 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int scenario = 0;
        while (true) {
            int n = Integer.parseInt(bf.readLine());

            if(n == 0)
                break;

            scenario++;
            String[] names = new String[n + 1];
            boolean[] confiscated = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                names[i] = bf.readLine();
            }

            for (int i = 0; i < 2 * n - 1; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                int num = Integer.parseInt(st.nextToken());
                String status = st.nextToken();

                confiscated[num] = !confiscated[num];
            }

            for (int i = 1; i <= n; i++) {
                if (confiscated[i]) {
                    sb.append(scenario).append(" ").append(names[i]).append("\n");
                }
            }
        }

        System.out.print(sb);
    }
}
