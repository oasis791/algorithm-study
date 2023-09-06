import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Baek_2776 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        for (int T = 0; T < t; T++) {
            int n = Integer.parseInt(bf.readLine());
            Set<Integer> set = new HashSet<>();
            StringTokenizer st = new StringTokenizer(bf.readLine());

            for (int i = 0; i < n; i++) {
                set.add(Integer.parseInt(st.nextToken()));
            }

            int m = Integer.parseInt(bf.readLine());
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < m; i++) {
                if (set.contains(Integer.parseInt(st.nextToken()))) {
                    sb.append(1);
                } else {
                    sb.append(0);
                }
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }
}
