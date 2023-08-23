import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class Baek_1969 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];

        Map<Character, Integer>[] arr = new Map[m];

        for (int i = 0; i < m; i++) {
            arr[i] = new HashMap<>();
        }

        for (int i = 0; i < n; i++) {
            String input = bf.readLine();
            for (int j = 0; j < m; j++) {
                arr[j].put(input.charAt(j), arr[j].getOrDefault(input.charAt(j), 0) + 1);
                map[i][j] = input.charAt(j);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            Iterator<Character> keys = arr[i].keySet().iterator();
            Character targetString = 'Z';
            int targetCount = 0;

            while (keys.hasNext()) {
                Character key = keys.next();
                int count = arr[i].get(key);

                if (targetCount < count) {
                    targetString = key;
                    targetCount = count;
                } else if (targetCount == count) {
                    if (targetString > key) {
                        targetString = key;
                    }
                }
            }

            sb.append(targetString);
        }

        String answer = sb.toString();
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != answer.charAt(j)) {
                    count++;
                }
            }
        }

        System.out.println(answer);
        System.out.println(count);
    }
}
