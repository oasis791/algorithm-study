import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class Programmers_131704 {
    static class Solution {
        public int solution(int[] order) {
            Stack<Integer> stk = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            int answer = 0;

            for (int i = 1; i <= order.length; i++) {
                queue.offer(i);
            }

            loop:
            for (int i = 0; i < order.length; i++) {
                int orderBox = order[i];

                if(!queue.isEmpty() && queue.peek() == orderBox) {
                    queue.poll();
                    answer++;
                    continue; 
                }

                if (!stk.isEmpty() && stk.peek() == orderBox) {
                    stk.pop();
                    answer++;
                    continue; 
                }


                while(!queue.isEmpty()) {
                    if(queue.peek() == orderBox) {
                        queue.poll();
                        answer++;
                        continue loop;
                    } else {
                        stk.push(queue.poll());
                    }
                }

                if(!stk.isEmpty()) {
                    int temp = stk.pop();
                    if(temp == orderBox) {
                        answer++;
                        continue loop;
                    }
                }
                break;
            }
            return answer;
        }
    }
}
