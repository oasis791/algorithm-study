public class Programmers_12923 {
    static class Solution {
        public static int[] solution(long begin, long end) {
            int[] answer = new int[(int)(end - begin + 1)];

            int index = 0;
            for (long l = begin; l <= end; l++) {
                answer[index++] = findDivisor(l);
            }

            return answer;
        }

        static int findDivisor(long l) {
            if(l == 1) {
                return 0;
            }

            long sqrt = (long)Math.sqrt(l);

            long max = 1;
            for (long i = 2; i <= sqrt; i++) {
                if(l % i == 0) {
                    max = i;
                    if((l / i) <= 10_000_000) {
                        return (int)(l / i);
                    }
                } 
            }

            return max == 1 ? 1 : (int)max;
        }
    }
}
