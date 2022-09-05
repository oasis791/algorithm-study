import java.util.Arrays;

public class Programmers_Phonekemon {
    public static void main(String[] args) {
        System.out.println(Solution.solution(new int[]{3, 1, 2, 3})); //2
        System.out.println(Solution.solution(new int[]{3, 3, 3, 2, 2, 4})); //3
        System.out.println(Solution.solution(new int[]{3, 3, 3, 2, 2, 2})); //2
    }

    static class Solution {
        public static int solution(int[] nums) {
            int amount = nums.length / 2;
            int[] newNum = Arrays.stream(nums).distinct().toArray();
            if (newNum.length >= amount) {
                return amount;
            }
            return newNum.length;
        }
    }
}
