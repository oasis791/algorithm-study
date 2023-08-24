import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1049 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int answer = Integer.MAX_VALUE;


        int[] unit = new int[m];
        int[] pack = new int[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            pack[i] = Integer.parseInt(st.nextToken());
            unit[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(unit);
        Arrays.sort(pack);

        answer = Math.min(((n / 6) + 1) * pack[0], n * unit[0]);
        answer = Math.min(answer, ((n / 6)) * pack[0] + (n % 6) * unit[0]);

        System.out.println(answer);
    }
}
