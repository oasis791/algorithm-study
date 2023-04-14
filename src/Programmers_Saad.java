import java.util.*;
import java.io.*;

public class Programmers_Saad {
    static class Solution {
        public int solution(int[][] targets) {
            int answer = 0;
            Arrays.sort(targets, new Comparator<>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            });

            int minEnd = targets[0][1];
            for(int i = 1; i < targets.length; i++) {
                if(targets[i][0] >= minEnd) {
                    answer++;
                    minEnd = targets[i][1];
                }
            }

            return answer + 1;
        }
    }
}