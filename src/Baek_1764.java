import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            set.add(bf.readLine());
        }

        int count = 0;
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String temp = bf.readLine();
            if (set.contains(temp)) {
                count++;
                answer.add(temp);
            }
        }

        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();
        sb.append(count).append("\n");
        for (String s : answer) {
            sb.append(s).append("\n");
        }

        System.out.print(sb);
    }
}
