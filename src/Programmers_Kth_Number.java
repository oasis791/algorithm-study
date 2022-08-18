import java.util.ArrayList;
import java.util.Arrays;

public class Programmers_Kth_Number {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[]{1, 5, 2, 6, 3, 7, 4}, new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}});
    }

    static class Solution {
        public int[] solution(int[] array, int[][] commands) {
            ArrayList<Integer> list = new ArrayList<>();

            for (int i = 0; i < commands.length; i++) {
                int[] newArray = Arrays.copyOf(array, array.length);
                int start = commands[i][0];
                int end = commands[i][1];
                int target = commands[i][2];

                int[] ints = Arrays.stream(newArray).limit(end).skip(start - 1).sorted().toArray();
                list.add(ints[target - 1]);
            }
            int[] answer = list.stream().mapToInt(Integer::intValue).toArray();
            return answer;
        }
    }

}
