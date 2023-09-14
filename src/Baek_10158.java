import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_10158 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(br.readLine());
        int x = 0;
        int y = 0;

        int p_quo = (int) (p + t) / w;
        int p_rem = (p + t) % w;
        if (p_quo % 2 == 0) {
            x = p_rem;
        } else {
            x = w - p_rem;
        }

        int q_quo = (int) (q + t) / h;
        int q_rem = (q + t) % h;
        if (q_quo % 2 == 0) {
            y = q_rem;
        } else {
            y = h - q_rem;
        }
        System.out.println(x + " " + y);
    }
}