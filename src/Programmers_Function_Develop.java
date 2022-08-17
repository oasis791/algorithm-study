import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

public class Programmers_Function_Develop {
    static class Solution {
        public int[] solution(int[] progresses, int[] speeds) {
            Queue<Integer> queue = new LinkedList<>();
            ArrayList<Integer> list = new ArrayList<>();

            for (int progress : progresses)
                queue.offer(progress);

            int count = 0;

            while (!queue.isEmpty()) {
                if (queue.peek() >= 100) {
                    count++;
                    queue.poll();
                    int[] finalSpeeds = speeds;
                    speeds = IntStream.range(1, speeds.length).map(i -> finalSpeeds[i]).toArray();
                    continue;
                }

                if (count != 0) {
                    list.add(count);
                    count = 0;
                }

                for (int i = 0; i < queue.size(); i++) {
                    Integer element = queue.poll();
                    queue.offer(element + speeds[i]);
                }

            }
            if(count!=0)
                list.add(count);

            int[] answer = list.stream().mapToInt(Integer::intValue).toArray();
            return answer;
        }
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] output1 = solution.solution(new int[]{93, 30, 55}, new int[]{1, 30, 5});
        System.out.println(output1);

        int[] output2 = solution.solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1});
        System.out.println(output2);
    }
}

