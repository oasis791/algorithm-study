public class Programmers_Cola {
    static class Solution {
        public static int solution(int a, int b, int n) {
            int count = 0;
            while (true) {
                if(n < a)
                    break;
                count += (n / a) * b;
                n = (n / a) * b + n % a;
            }
            return count;
        }
    }
}
