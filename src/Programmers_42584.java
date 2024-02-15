import java.util.Stack;

public class Programmers_42584 {
    static class Solution {
        public int[] solution(int[] prices) {
            Stack<Integer> stk = new Stack<>();
            int[] answer = new int[prices.length];

            for (int i = 0; i < prices.length; i++) {
                while (!stk.isEmpty() && prices[stk.peek()] > prices[i]) {
                    int temp = stk.pop();
                    answer[temp] = i - temp;
                }

                stk.push(i);
            }

            while(!stk.isEmpty()) {
                int temp = stk.pop();
                answer[temp] = prices.length - temp - 1;
            }

            return answer;
        }
    }
}
