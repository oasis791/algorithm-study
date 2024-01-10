import java.util.Arrays;
import java.util.Comparator;

public class Programmers_147354 {
    static class Solution {
        public int solution(int[][] data, int col, int row_begin, int row_end) {
            Arrays.sort(data, new Comparator<>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[col - 1] == o2[col - 1]) {
                        // 2.1. 동일하면, 기본키인 첫번째 컬럼 값으로 내림차순정렬
                        return -1 * (o1[0] - o2[0]);
                    } else {
                        // 2. col번째 컬럼의 값을 기준으로 오름차순 정렬
                        return o1[col - 1] - o2[col - 1];
                    }
                }
            });

            // 3. 정렬된 데이터에서 S_i를 i번째 행의 튜플에 대해 각 
            int answer = 0;
            for(int i = row_begin - 1; i < row_end; i++) {
                int temp = 0;
                for(int j = 0; j < data[i].length; j++) {
                    temp += data[i][j] % (i + 1);
                }
                answer ^= temp;
            }

            return answer;
        }
    }
}
