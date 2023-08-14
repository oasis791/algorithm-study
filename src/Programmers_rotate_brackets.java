import java.util.*;

public class Programmers_rotate_brackets {
    static class Solution {
        public int solution(String s) {
            int answer = 0;
            if(check(s))
                answer++;
            StringBuilder sb = new StringBuilder();
            sb.append(s);

            for(int i = 1; i < s.length() - 1; i++) {
                char c = sb.charAt(0);
                sb.deleteCharAt(0);
                sb.append(c);
                if(check(sb.toString()))
                    answer++;
            }

            return answer;
        }

        static boolean check(String s) {
            Stack<Character> stk = new Stack<>();

            for(int i = 0; i < s.length(); i++) {
                switch (s.charAt(i)) {
                    case ')':
                        if(stk.size() == 0) {
                            return false;
                        } else if(stk.peek() == '(') {
                            stk.pop();
                        }
                        break;
                    case ']':
                        if(stk.size() == 0) {
                            return false;
                        } else if(stk.peek() == '[') {
                            stk.pop();
                        }
                        break;
                    case '}':
                        if(stk.size() == 0) {
                            return false;
                        } else if(stk.peek() == '{') {
                            stk.pop();
                        }
                        break;
                    default:
                        stk.push(s.charAt(i));
                        break;
                }
            }

            if(stk.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
