public class Programmers_124_Country {
    public static void main(String[] args) {
        System.out.println(Solution.solution(1));
        System.out.println(Solution.solution(2));
        System.out.println(Solution.solution(3));
        System.out.println(Solution.solution(4));
        System.out.println(Solution.solution(5));
        System.out.println(Solution.solution(6));
        System.out.println(Solution.solution(7));
        System.out.println(Solution.solution(8));
        System.out.println(Solution.solution(9));
        System.out.println(Solution.solution(10));
        System.out.println(Solution.solution(50_000_000));
        System.out.println(1 / 3);

    }

    static class Solution {
        public static String solution(int n) {
            StringBuilder sb = new StringBuilder();

            while (n > 0) {
                func(n % 3, sb);
                if (n % 3 == 0) {
                    n = n / 3 - 1;
                } else {
                    n /= 3;
                }
            }
            return sb.toString();
        }

        static StringBuilder func(int n, StringBuilder sb) {
            switch (n) {
                case 1 -> sb.insert(0, 1);
                case 2 -> sb.insert(0, 2);
                case 0 -> sb.insert(0, 4);
            }
            return sb;
        }
    }
}
