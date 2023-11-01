import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_15702 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] scores = new int[n];
        int bestScore = 0;
        int bestPerson = 100001;

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int perNum = Integer.parseInt(st.nextToken());
            int score = 0;
            for (int j = 0; j < n; j++) {
                if(st.nextToken().equals("O")) {
                    score += scores[j];
                }
            }

            if (score > bestScore || (score == bestScore && bestPerson > perNum)) {
                bestScore = score;
                bestPerson = perNum;
            }
        }

        System.out.println(bestPerson + " " + bestScore);
    }
}
