public class Programmers_84512 {

    static class Solution {
        static char[] vowels = new char[]{'A', 'E', 'I', 'O', 'U'};
        static int num = 0;
        static int answer = 0;

        public static int solution(String word) {
            makeSequence(new StringBuilder(), 0, word);
            return answer;
        }

        static void makeSequence(StringBuilder sb, int limit, String word) {
            if (limit > 5) {
                return;
            }

            if (sb.toString().equals(word) && answer == 0) {
                answer = num;
                return;
            }

            num++;

            for (int i = 0; i < 5; i++) {
                sb.append(vowels[i]);
                makeSequence(sb, limit + 1, word);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
