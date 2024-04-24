import java.util.*;

public class Programmers_42884 {
    static class Solution {
        public static int solution(int[][] routes) {
            Arrays.sort(routes, new Comparator<>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[1] == o2[1]) {
                        return o1[0] - o2[0];
                    }

                    return o1[1] - o2[1];
                }
            });

            int answer = 0;
            int endPoint = -30001;

            for (int i = 0; i < routes.length; i++) {
                int start = routes[i][0];
                int end = routes[i][1];

                if(endPoint >= start) {
                    continue;
                } else {
                    endPoint = end;
                    answer++;
                }
            }

            return answer;
        }
    }
}
