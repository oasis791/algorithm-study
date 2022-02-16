import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Programmer_2020_kakao_intern {
    static class Solution {
        public String solution(int[] numbers, String hand) {
            StringBuilder sb = new StringBuilder();
            int currentLeftThumb = -1;
            int currentRightThumb = 10;

            int index = 1;
            int[][] keyPad = new int[4][3];
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 3; c++) {
                    if (index == 10) {
                        keyPad[r][c] = -1;
                        index++;
                    } else if (index == 11) {
                        keyPad[r][c] = 0;
                        index++;
                    } else if (index == 12) {
                        keyPad[r][c] = 10;
                        index++;
                    } else {
                        keyPad[r][c] = index++;
                    }
                }
            }

            for (int number : numbers) {
                if (number == 1 || number == 4 || number == 7) {
                    sb.append("L");
                    currentLeftThumb = number;
                } else if (number == 3 || number == 6 || number == 9) {
                    sb.append("R");
                    currentRightThumb = number;
                } else {
                    int distanceFromTheLeft = 0;
                    int distanceFromTheRight = 0;
                    int leftRow=0, leftCol=0, rightRow=0, rightCol=0, targetRow=0, targetCol=0;

                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (keyPad[i][j] == currentLeftThumb) {
                                leftRow = i;
                                leftCol = j;
                            }
                            if (keyPad[i][j] == currentRightThumb) {
                                rightRow = i;
                                rightCol = j;
                            }
                            if (keyPad[i][j] == number) {
                                targetRow = i;
                                targetCol = j;
                            }
                        }
                    }

                    distanceFromTheLeft = Math.abs(targetRow - leftRow) + Math.abs(targetCol - leftCol);
                    distanceFromTheRight = Math.abs(targetRow - rightRow) + Math.abs(targetCol - rightCol);

                    if (distanceFromTheLeft < distanceFromTheRight) {
                        sb.append("L");
                        currentLeftThumb = number;
                    } else if (distanceFromTheRight < distanceFromTheLeft) {
                        sb.append("R");
                        currentRightThumb = number;
                    } else if (distanceFromTheLeft == distanceFromTheRight) {
                        if (hand.equals("left")) {
                            sb.append("L");
                            currentLeftThumb = number;
                        } else {
                            sb.append("R");
                            currentRightThumb = number;
                        }
                    }
                }
            }
            String answer = sb.toString();
            return answer;
        }
    }
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String inputNumbers = bf.readLine();
        StringBuilder sb = new StringBuilder(inputNumbers);
        sb.delete(0, 1);
        sb.delete(sb.length() - 1, sb.length());
        inputNumbers = sb.toString();
        StringTokenizer st = new StringTokenizer(inputNumbers, ", ");
        sb = new StringBuilder();
        while (st.hasMoreTokens())
            sb.append(st.nextToken());

        inputNumbers = sb.toString();
        int[] numbers = new int[inputNumbers.length()];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(inputNumbers.split("")[i]);
        }

        String handType = bf.readLine();

        System.out.println(solution.solution(numbers, handType));
    }
}
