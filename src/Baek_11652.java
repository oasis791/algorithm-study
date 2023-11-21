import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_11652 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        Map<Long, Integer> map = new HashMap<>();
        long answer = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            long temp = Long.parseLong(bf.readLine());
            map.put(temp, map.getOrDefault(temp, 0) + 1);

            if (map.get(temp) > count) {
                answer = temp;
                count = map.get(temp);
            } else if (map.get(temp) == count) {
                answer = Math.min(answer, temp);
            }
        }

        System.out.println(answer);
    }
}
