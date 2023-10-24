import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_10709 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[][] map = new int[h + 1][w + 1];
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= h; i++) {
            Arrays.fill(map[i], -1);
            String s = bf.readLine();
            for (int j = 1; j <= w; j++) {
                if(s.charAt(j - 1) == 'c') {
                    map[i][j] = 0;
                } else if (map[i][j - 1] != -1) {
                    map[i][j] = map[i][j - 1] + 1;
                }
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
