import java.io.*;
import java.util.*;

public class Baek_16120 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = bf.readLine();
        String target = "PPAP";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            stack.push(c);
            if ((c == target.charAt(target.length() - 1)) && stack.size() >= target.length()) {
                boolean toDelete = true;
                int index = stack.size() - 1;

                for (int j = target.length() - 1; j >= 0; j--) {
                    Character c1 = stack.get(index--);
                    Character c2 = target.charAt(j);

                    if (c1 != c2) {
                        toDelete = false;
                        break;
                    }
                }

                if (toDelete) {
                    for (int j = 0; j < target.length(); j++) {
                        stack.pop();
                    }
                    stack.push('P');
                }
            }
        }

        if (stack.size() == 1) {
            if (stack.pop() == 'P') {
                System.out.println("PPAP");
            } else {
                System.out.println("NP");
            }
        } else {
            System.out.println("NP");
        }
    }
}
