import java.util.Stack;

public class Programmers_Pair_Delete {
    static class Solution {
        public static int solution(String s) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char cur = s.charAt(i);
                if(!stack.isEmpty()){
                    if(stack.peek() == cur) {
                        stack.pop();
                        continue;
                    }
                }
                stack.push(cur);
            }

            return stack.isEmpty() ? 1 : 0;
        }
    }
}
