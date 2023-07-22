import java.io.*;
import java.util.*;

public class Baek_4195 {
    static int[] parents;
    static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(bf.readLine());

        for(int T = 0; T < t; T++) {
            int f = Integer.parseInt(bf.readLine());
            Map<String, Integer> map = new HashMap<>();
            parents = new int[f * 2];
            answer = new int[f * 2];
            int index = 0;

            for(int i = 0; i < f * 2; i++) {
                parents[i] = i;
                answer[i] = 1;
            }

            for(int i = 0; i < f; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                if(!map.containsKey(a)) {
                    map.put(a, index++);
                }

                if(!map.containsKey(b)) {
                    map.put(b, index++);
                }

                int x = map.get(a);
                int y = map.get(b);

                sb.append(union(x, y)).append("\n");
            }
        }
        System.out.print(sb);
    }

    static int find(int x) {
        if(parents[x] == x) {
            return x;
        } else {
            return parents[x] = find(parents[x]);
        }
    }

    static int union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y) {
            parents[y] = x;
            answer[x] += answer[y];

            answer[y] = 1;
        }

        return answer[x];
    }
}
