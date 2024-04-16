public class Programmers_148652 {
    static class Solution {
        public int solution(int n, long l, long r) {
            int answer = 0;

            for (long i = l - 1; i < r; i++) {
                if(func(i)) answer++;
            }

            return answer;
        }

        static boolean func(long i) {
            if (i % 5 == 2) return false;
            if(i < 5) return true;

            return func(i / 5);
        }
    }
}
