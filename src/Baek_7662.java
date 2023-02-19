import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        for (int t = 0; t < T; t++) {
            TreeMap<Integer, Integer> map = new TreeMap<>();
            int k = Integer.parseInt(bf.readLine());
            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                String op = st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                if (op.equals("I")) {
                    if (map.containsKey(num)) {
                        map.put(num, map.get(num) + 1);
                    } else {
                        map.put(num, 1);
                    }
                } else {
                    if (map.size() > 0) {
                        int key;
                        if (num == -1) {
                            key = map.firstKey();
                        } else {
                            key = map.lastKey();
                        }
                        map.put(key, map.get(key) - 1);
                        if (map.get(key) == 0)
                            map.remove(key);
                    }
                }
            }

            if (map.size() == 0) {
                System.out.println("EMPTY");
            } else {
                System.out.println(map.lastKey() + " " + map.firstKey());
            }
        }
    }
}
