import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baek_4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        loop:
        while (true) {
            String input = bf.readLine();
            if (input.equals(".")) {
                break;
            }

            Stack<Character> stk = new Stack<>();
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '(' || input.charAt(i) == '[') {
                    stk.push(input.charAt(i));
                } else if (input.charAt(i) == ')' || input.charAt(i) == ']') {
                    if (stk.isEmpty()) {
                        sb.append("no").append("\n");
                        continue loop;
                    }

                    char c = stk.peek();
                    if ((c == '(' && input.charAt(i) == ']') ||
                            (c == '[' && input.charAt(i) == ')')) {
                        sb.append("no").append("\n");
                        continue loop;
                    } else {
                        stk.pop();
                    }
                }
            }

            if (!stk.isEmpty()) {
                sb.append("no").append("\n");
            } else {
                sb.append("yes").append("\n");
            }
        }

        System.out.print(sb);
    }
}
