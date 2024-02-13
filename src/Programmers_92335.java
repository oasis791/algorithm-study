public class Programmers_92335 {
    static class Solution {

        public int solution(int n, int k) {
            // 1. n => k 진수
            String base = convertToBase(n, k);

            // 2. 소수 판별
            int answer = 0;
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < base.length(); i++) {
                if(base.charAt(i) == '0') {
                    if(sb.length() > 0 && isPrime(Long.parseLong(sb.toString()))) {
                        answer++;
                    }

                    sb.delete(0, sb.length());
                } else {
                    sb.append(base.charAt(i));
                }
            }

            if(sb.length() > 0) {
                if(isPrime(Long.parseLong(sb.toString())))
                    answer++;
            }

            return answer;
        }

        static String convertToBase (int n, int k) {
            StringBuilder sb = new StringBuilder();

            while(n > 0) {
                sb.insert(0, n % k);
                n /= k;
            }

            return sb.toString();
        }

        static boolean isPrime(long num) {
            if(num == 1)
                return false;
            if(num == 2)
                return true;

            for(long i = 2; i <= Math.sqrt(num); i++) {
                if(num % i == 0) 
                    return false;
            }

            return true;
        }
    }
}
