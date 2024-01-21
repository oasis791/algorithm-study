public class Programmers_42883 {
    static class Solution {
        public String solution(String number, int k) {
            StringBuilder sb = new StringBuilder(number);
            int count = 0;
            int size = number.length();

            loop:
            for (int i = 0; i < size; i++) {
                if (count >= k) {
                    break;
                }

                if (i + k - count >= size)
                    break loop;

                for (int j = 1; j <= k - count; j++) {
                    if (sb.charAt(i) < sb.charAt(i + j)) {
                        sb.deleteCharAt(i);
                        size--;
                        i--;
                        count++;
                        break;
                    }
                }
            }

            if (count < k) {
                sb.deleteCharAt(sb.length() - 1);
            }

            return sb.toString();
        }
    }
}