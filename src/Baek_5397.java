import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baek_5397 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int t = Integer.parseInt(bf.readLine());

        for (int T = 0; T < t; T++) {
            String input = bf.readLine();
            Stack<Character> left = new Stack<>();
            Stack<Character> right = new Stack<>();

            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);

                switch (c) {
                    case '<':
                        if (!left.isEmpty()) {
                            right.push(left.pop());
                        }
                        break;
                    case '>':
                        if (!right.isEmpty()) {
                            left.push(right.pop());
                        }
                        break;
                    case '-':
                        if (!left.isEmpty()) {
                            left.pop();
                        }
                        break;
                    default:
                        left.push(c);
                }
            }

            while(!left.isEmpty()){
                right.push(left.pop());
            }

            while (!right.isEmpty()) {
                answer.append(right.pop());
            }
            answer.append("\n");
        }
        System.out.print(answer);
    }
}
