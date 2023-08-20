import java.util.*;

public class Programmers_131701 {
    static class Solution {
        public int solution(int[] elements) {
            Set<Integer> set = new HashSet<>();

            int size = 1;
            while (size <= elements.length) {
                for (int i = 0; i < elements.length; i++) {
                    int sum = 0;
                    for (int j = 0; j < size; j++) {
                        int index = (i + j) % elements.length;
                        sum += elements[index];
                    }
                    set.add(sum);
                }
                size++;
            }
            return set.size();
        }
    }
}
