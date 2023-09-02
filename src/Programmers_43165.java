public class Programmers_43165 {
    public static void main(String[] args) {
        System.out.println(Solution.solution(new int[]{1, 1, 1, 1, 1}, 3));
    }

    static class Solution {
        static int answer = 0;

        public static int solution(int[] numbers, int target) {
            dfs(numbers, target, 0, 0, 0);
            return answer;
        }

        static void dfs(int[] numbers, int target, int index, int sum, int depth) {
            if (depth == numbers.length) {
                if (sum == target) {
                    answer++;
                }

                return;
            }

            dfs(numbers, target, index + 1, sum + numbers[index], depth + 1);
            dfs(numbers, target, index + 1, sum - numbers[index], depth + 1);
        }
    }
}
