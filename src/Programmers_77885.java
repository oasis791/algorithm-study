public class Programmers_77885 {
    static class Solution {
        public long[] solution(long[] numbers) {

            long[] answer = numbers.clone();

            for (int i = 0; i < numbers.length; i++) {
                answer[i]++;
                answer[i] += (answer[i] ^ numbers[i]) >> 2;
            }

            return answer;
        }
    }
}
