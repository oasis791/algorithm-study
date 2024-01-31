import java.io.*;
import java.util.*;

public class Softeer_6279 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int answer = 0;

        char[] lines = new char[n];
        String input = bf.readLine();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            lines[i] = input.charAt(i);
        }

        for (int i = 0; i < n; i++) {
            if(lines[i] == 'P') {
                for (int j = i - k; j <= i + k; j++) {
                    if (j < 0 || j >= n)
                        continue;

                    if(lines[j] == 'H' && !visited[j]) {
                        visited[j] = true;
                        answer++;
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
