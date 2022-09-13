import java.util.Arrays;
import java.util.stream.Collectors;

public class Programmers_Weird_Letter {
    static class Solution {
        public static String solution(String s) {
            String[] array = s.split("");
            int index = 0;
            for (int i = 0; i < array.length; i++) {
                if(array[i].equals(" ")){
                    index = 0;
                    continue;
                }
                if (index++ % 2 == 0) {
                    array[i] = array[i].toUpperCase();
                } else {
                    array[i] = array[i].toLowerCase();
                }
            }

            return Arrays.stream(array).collect(Collectors.joining());
        }
    }
}
