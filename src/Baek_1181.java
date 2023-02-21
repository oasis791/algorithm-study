import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Baek_1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        Set<String>[] dic = new HashSet[51];
        for (int i = 1; i < 51; i++) {
            dic[i] = new HashSet<>();
        }
        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            dic[s.length()].add(s);
        }

        for (int i = 1; i <= 50; i++) {
            dic[i].stream().sorted().forEach(System.out::println);
        }
    }
}
