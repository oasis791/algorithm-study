import java.util.Stack;

public class Programmers_154539 {

    static class Solution {
        public int[] solution(int[] numbers) {
            int n = numbers.length;
            int[] answer = new int[n];
            Stack<Integer> stk = new Stack<>();
            answer[n - 1] = -1;
            stk.push(numbers[n - 1]);

            for (int i = n - 2; i >= 0; i--) {
                int num = -1;

                while (!stk.isEmpty()) {
                    if (stk.peek() > numbers[i]) {
                        num = stk.peek();
                        break;
                    } else {
                        stk.pop();
                    }
                }

                answer[i] = num;
                stk.push(numbers[i]);
            }

            return answer;
        }
    }
}
