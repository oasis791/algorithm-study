import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Comparator;
import java.util.Arrays;
import java.util.Collections;

public class Programmers_155651 {
    class Solution {
        public int solution(String[][] book_time) {
            int[][] time = new int[book_time.length][2];
            for(int i = 0; i < book_time.length; i++) {
                StringTokenizer st = new StringTokenizer(book_time[i][0], ":");
                time[i][0] = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
                st = new StringTokenizer(book_time[i][1], ":");
                time[i][1] = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken()) + 10;
            }

            Arrays.sort(time, new Comparator<>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[0] != o2[0]) {
                        return o1[0] - o2[0];
                    } else {
                        return o1[1] - o2[1];
                    }
                }
            });

            ArrayList<Integer> room = new ArrayList<>();

            for(int i = 0; i < time.length; i++) {
                int startTime = time[i][0];
                int endTime = time[i][1];
                boolean isChecked = false;
                Collections.sort(room);

                for(int j = 0; j < room.size(); j++) {
                    if(room.get(j) <= startTime) {
                        room.set(j, endTime);
                        isChecked = true;
                        break;
                    }
                }

                if(!isChecked) {
                    room.add(endTime);
                }
            }

            return room.size();
        }
    }
}
