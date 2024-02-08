import java.util.Stack;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Comparator;

public class Programmers_176962 {
    static class Solution {
        public String[] solution(String[][] plans) {
            PriorityQueue<String[]> pq = new PriorityQueue<>(new Comparator<>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    return Integer.parseInt(o1[1]) - Integer.parseInt(o2[1]);
                }
            });
            Stack<String[]> stack = new Stack<>();

            for (int i = 0; i < plans.length; i++) {
                String[] input = new String[3];
                input[0] = plans[i][0];
                StringTokenizer st = new StringTokenizer(plans[i][1], ":");
                input[1] = String.valueOf(Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken()));
                input[2] = plans[i][2];
                pq.offer(input);
            }

            String[] answer = new String[plans.length];
            int index = 0;

            while (!pq.isEmpty()) {
                String[] cur = pq.poll();
                int startTime = Integer.parseInt(cur[1]);
                int runningTime = Integer.parseInt(cur[2]);
                int nowTime = startTime;

                if (!pq.isEmpty()) {
                    String[] next = pq.peek();
                    if (startTime + runningTime > Integer.parseInt(next[1])) {
                        runningTime -= Integer.parseInt(next[1]) - startTime;
                        stack.push(new String[] {cur[0], String.valueOf(runningTime)});
                        continue;
                    }
                }

                answer[index++] = cur[0];
                nowTime += runningTime;

                if (!stack.isEmpty()) {

                    if (!pq.isEmpty()) {
                        while(!stack.isEmpty()) {
                            String[] remain = stack.pop();
                            int remainTime = Integer.parseInt(remain[1]);
                            String[] next = pq.peek();
                            if (nowTime + remainTime > Integer.parseInt(next[1])) {
                                remainTime = (startTime + runningTime + remainTime) - Integer.parseInt(next[1]);
                                stack.push(new String[] {remain[0], String.valueOf(remainTime)});
                                break;
                            } else {
                                nowTime += remainTime;
                                answer[index++] = remain[0];
                            }
                        }
                    } else {
                        String[] remain = stack.pop();
                        answer[index++] = remain[0];
                    }
                }
            }

            while (!stack.isEmpty()) {
                answer[index++] = stack.pop()[0];
            }

            return answer;
        }
    }
}
