import java.util.*;
public class Programmers_Triangle_Snail {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution.solution(3)));
    }
    static class Solution {
        public static int[] solution(int n) {
            int[][] matrix = new int[n][n];
            int num = 1;
            int[] move_row = new int[]{1, 0, -1};
            int[] move_col = new int[]{0, 1, -1};
            int dir = 0; // 0: 하단, 1: 오른쪽, 2:왼쪽 대각선 위
            matrix[0][0] = num++;
            int[] curPoint = new int[]{0, 0};

            loop:
            while(true) {
                // 1. 방향을 설정
                for(int i = 0; i < 2; i++) {
                    int nowDir = dir + i;
                    if(nowDir >= 3)
                        nowDir -= 3;
                    if(curPoint[0] + move_row[nowDir] >= 0 && curPoint[0] + move_row[nowDir] < n
                            && curPoint[1] + move_col[nowDir] >= 0 && curPoint[1] + move_col[nowDir] < n
                            && matrix[curPoint[0] + move_row[nowDir]][curPoint[1] + move_col[nowDir]] == 0) {
                        curPoint[0] += move_row[nowDir];
                        curPoint[1] += move_col[nowDir];
                        matrix[curPoint[0]][curPoint[1]] = num++;
                        dir = nowDir;
                        continue loop;
                    }
                }
                break;
            }

            ArrayList<Integer> list = new ArrayList<>();
            for(int i = 0; i < n ; i++){
                for(int j = 0; j < n; j++){
                    if(matrix[i][j] == 0)
                        break;
                    list.add(matrix[i][j]);
                }
            }

            int[] answer = new int[list.size()];
            for(int i=0;i<list.size();i++)
                answer[i] = list.get(i);
            return answer;
        }
    }
}
