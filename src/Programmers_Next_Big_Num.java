public class Programmers_Next_Big_Num {
    public static void main(String[] args) {
        System.out.println(Solution.solution(78));
        System.out.println(Solution.solution(15));
    }

    static class Solution {
        public static int solution(int n) {
            int target = Integer.bitCount(n);
            while (true) {
                n++;
                if(Integer.bitCount(n) == target)
                    return n;
            }
        }
    }
}
