import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_10773 {
    private static int N;
    private static ArrayList<Integer> num = new ArrayList<>();
    private static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(bf.readLine());
            if (input != 0) {
                num.add(input);
            } else {
                if (num.size() != 0) {
                    num.remove(num.size() - 1);
                }
            }
        }
        for (Integer integer : num) {
            result += integer;
        }
        System.out.println(result);
    }
}
