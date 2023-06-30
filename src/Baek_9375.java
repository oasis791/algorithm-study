import java.io.*;
import java.util.*;

public class Baek_9375 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());

        for (int t = 0; t < T; t++) {
            Map<String, Integer> map = new HashMap<>();
            int n = Integer.parseInt(bf.readLine());
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
                String name = st.nextToken();
                String cate = st.nextToken();

                if (!map.containsKey(cate)) {
                    map.put(cate, 1);
                } else {
                    map.put(cate, map.get(cate) + 1);
                }
            }

            int answer = 1;
            int count = 0;
            if (map.size() == 1) {
                for (String s : map.keySet()) {
                    answer = map.get(s);
                }
            } else {
                for (String s : map.keySet()) {
                    Integer num = map.get(s);
                    answer *= (num + 1);
                }
                answer -= 1;
            }

            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
