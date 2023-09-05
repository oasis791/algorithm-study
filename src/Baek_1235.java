import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Baek_1235 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        String[] students = new String[n];

        for (int i = 0; i < n; i++) {
            students[i] = bf.readLine();
        }

        int answer = 0;

        loop:
        for (int i = 1; i <= students[0].length(); i++) {
            Set<String> set = new HashSet<>();
            for (String s : students) {
                String temp = s.substring(s.length() - i);
                if (set.contains(temp)) {
                    continue loop;
                }
                set.add(temp);
            }
            answer = i;
            break;
        }

        System.out.println(answer);
    }
}
