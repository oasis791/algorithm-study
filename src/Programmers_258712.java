import java.util.HashMap;
import java.util.StringTokenizer;

public class Programmers_258712 {
    static class Solution {
        static HashMap<String, Integer> indexOfFriends = new HashMap<>();

        public static int solution(String[] friends, String[] gifts) {
            int[] answer = new int[friends.length];
            int[][] info = new int[friends.length][friends.length];
            boolean[][] visited = new boolean[friends.length][friends.length];

            for (int i = 0; i < friends.length; i++) {
                indexOfFriends.put(friends[i], i);
            }

            for (int i = 0; i < gifts.length; i++) {
                StringTokenizer st = new StringTokenizer(gifts[i]);
                int from = indexOfFriends.get(st.nextToken());
                int to = indexOfFriends.get(st.nextToken());

                info[from][to]++;
            }

            for (int i = 0; i < friends.length; i++) {
                for (int j = 0; j < friends.length; j++) {
                    if (i == j)
                        continue;

                    if (visited[i][j])
                        continue;
                    //두 사람이 선물을 주고받은 기록이 있다면, 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받습니다.
                    if ((info[i][j] > 0 || info[j][i] > 0) && info[i][j] != info[j][i]) {

                        if (info[i][j] > info[j][i]) {
                            answer[i]++;
                        } else if (info[i][j] < info[j][i]) {
                            answer[j]++;
                        }
                    } else {
                        //두 사람이 선물을 주고받은 기록이 하나도 없거나 주고받은 수가 같다면, 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받습니다.
                        int pointA = calPoint(i, info);
                        int pointB = calPoint(j, info);

                        if (pointA < pointB) {
                            answer[j]++;
                        } else if (pointA > pointB) {
                            answer[i]++;
                        }
                    }

                    visited[i][j] = true;
                    visited[j][i] = true;
                }
            }

            int max = 0;

            for (int i = 0; i < answer.length; i++) {
                max = Math.max(answer[i], max);
                System.out.print(answer[i] + " ");
            }

            return max;
        }

        static int calPoint(int index, int[][] info) {
            int sentPoint = 0;
            int receivePoint = 0;

            for (int i = 0; i < info.length; i++) {
                sentPoint += info[index][i];
            }

            for (int i = 0; i < info.length; i++) {
                receivePoint += info[i][index];
            }

            return sentPoint - receivePoint;
        }
    }
}
