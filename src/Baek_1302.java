//1302번 베스트셀러
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Baek_1302 {
    private static int N;
    private static String result;
    private static int max = 0;
    private static HashMap<String, Integer> book = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            String input = bf.readLine();
            if (!book.containsKey(input)) {
                book.put(input, 1);
            } else {
                int val = book.get(input);
                book.put(input, val + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : book.entrySet()) {
            String tempKey = entry.getKey();
            Integer tempVal = entry.getValue();
            if (tempVal == max) {
                String[] temp = new String[2];
                temp[0] = tempKey;
                temp[1] = result;
                Arrays.sort(temp);
                result = temp[0];
            } else if (tempVal > max) {
                max = tempVal;
                result = tempKey;
            }
        }
        System.out.println(result);
    }
}
