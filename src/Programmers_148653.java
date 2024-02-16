public class Programmers_148653 {
    static class Solution {
        public int solution(int storey) {
            int answer = 0;
            String[] s = String.valueOf(storey).split("");

            for (int i = s.length - 1; i >= 0; i--) {
                int temp = Integer.parseInt(s[i]);

                if(temp >= 0 && temp <= 4) {
                    answer += temp;
                } else if (temp == 5) {
                    if(i == 0 || Integer.parseInt(s[i - 1]) < 5) {
                        answer += 5;
                    } else {
                        answer += 5;
                        if(i > 0) {
                            s[i - 1] = String.valueOf(Integer.parseInt(s[i - 1]) + 1);
                        } else {
                            answer += 1;
                        }
                    }
                } else {
                    answer += 10 - temp;

                    if(i > 0) {
                        s[i - 1] = String.valueOf(Integer.parseInt(s[i - 1]) + 1);
                    } else {
                        answer += 1;
                    }
                }

            }

            return answer;
        }
    }
    class Solution {
        public int solution(int storey) {
            int answer = 0;
            String[] s = String.valueOf(storey).split("");

            for (int i = s.length - 1; i >= 0; i--) {
                int temp = Integer.parseInt(s[i]);

                if(temp >= 0 && temp <= 4) {
                    answer += temp;
                } else if (temp == 5) {
                    if(i == 0 || Integer.parseInt(s[i - 1]) < 5) {
                        answer += 5;
                    } else {
                        answer += 5;
                        if(i > 0) {
                            s[i - 1] = String.valueOf(Integer.parseInt(s[i - 1]) + 1);
                        } else {
                            answer += 1;
                        }
                    }
                } else {
                    answer += 10 - temp;

                    if(i > 0) {
                        s[i - 1] = String.valueOf(Integer.parseInt(s[i - 1]) + 1);
                    } else {
                        answer += 1;
                    }
                }

            }

            return answer;
        }
    }
}
