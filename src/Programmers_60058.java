import java.util.*;

public class Programmers_60058 {
    static class Solution {
        public String solution(String p) {
            return checkParenthesis(p);
        }

        static String checkParenthesis(String p) {
            // 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다. 
            if(p.equals(""))
                return "";

            // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
            String u = "";
            String v = "";
            int leftCount = 0;
            int rightCount = 0;
            for(int i = 0; i < p.length(); i++) {
                if(p.charAt(i) == '(') {
                    leftCount++;
                } else {
                    rightCount++;
                }

                if(leftCount == rightCount) {
                    u = p.substring(0, i + 1);
                    if(i == p.length() - 1) {
                        v = "";
                    } else {
                        v = p.substring(i + 1, p.length());
                    }
                    break;
                }
            }

            if(checkCorrect(u)) {
                // 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다. 
                // 3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다. 
                return u + checkParenthesis(v);
            } else {
                // 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다. 
                StringBuilder sb = new StringBuilder();
                // 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다. 
                // 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다. 
                // 4-3. ')'를 다시 붙입니다. 
                sb.append("(").append(checkParenthesis(v)).append(")");

                // 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. 
                StringBuilder ub = new StringBuilder(u);
                ub.deleteCharAt(0);
                ub.deleteCharAt(ub.length() - 1);
                for(int i = 0; i < ub.length(); i++) {
                    if(ub.charAt(i) == '(') {
                        ub.replace(i, i + 1, ")");
                    } else {
                        ub.replace(i, i + 1, "(");
                    }
                }
                sb.append(ub.toString());

                // 4-5. 생성된 문자열을 반환합니다.
                return sb.toString();
            }
        }

        static boolean checkCorrect(String u) {
            Stack<Character> stk = new Stack<>();

            for(int i = 0; i < u.length(); i++) {
                if (u.charAt(i) == '(') {
                    stk.push('(');
                } else {
                    if(stk.isEmpty()) {
                        return false;
                    } 
                    stk.pop();
                }
            }

            if(stk.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
