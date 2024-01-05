public class Programmers_17687 {
    static class Solution {
        public String solution(int n, int t, int m, int p) {
            // n: 진법, t: 미리 구할 숫자 개수
            // m: 참가인원, p: 튜브의 순서
            StringBuilder sb = new StringBuilder();
            int cur = 1;
            int i = 0;

            while (sb.length() < t) {
                // 2. 각 숫자를 n진법 화 시킨다.
                String temp = Integer.toString(i, n);
                // 3. 각 문자열을 cur 부터 m까지 (p와 같으면 답에 추가)
                for (int j = 0; j < temp.length(); j++) {
                    if (sb.length() < t) {
                        char c = temp.charAt(j);
                        if (cur > m)
                            cur = 1;
                        if (cur == p) {
                            sb.append(Character.toUpperCase(c));
                        }
                        cur++;
                    }
                }
                i++;
            }

            return sb.toString();
        }
    }
}
