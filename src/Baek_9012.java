import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baek_9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        play:
        for (int t = 0; t < T; t++) {
            Stack<String> stack = new Stack<>();
            String[] input = bf.readLine().split("");
            for (int i = 0; i < input.length; i++) {
                if (input[i].equals("(")) {
                    stack.add("(");
                } else {
                    if (stack.isEmpty()) {
                        System.out.println("NO");
                        continue play;
                    }
                    stack.pop();
                }
            }
            if (stack.size() == 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
