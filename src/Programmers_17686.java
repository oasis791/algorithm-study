import java.util.Arrays;
import java.util.Comparator;

public class Programmers_17686 {
    static class Solution {
        public String[] solution(String[] files) {
            // 1. 파일명 HEAD, NUMBER, TAIL로 분리
            String[][] newFiles = new String[files.length][4];
            for (int i = 0; i < files.length; i++) {
                int index = 0;
                String file = files[i];
                StringBuilder sb = new StringBuilder();

                // HEAD 부분 분리
                while (!Character.isDigit(file.charAt(index))) {
                    sb.append(file.charAt(index++));
                }
                newFiles[i][0] = sb.toString();
                sb.delete(0, sb.length());

                // NUMBER 부분 분리
                while (index < file.length()) {
                    if (sb.length() >= 5) {
                        break;
                    }
                    if (Character.isDigit(file.charAt(index))) {
                        sb.append(file.charAt(index++));
                    } else {
                        break;
                    }
                }

                newFiles[i][1] = sb.toString();
                newFiles[i][2] = String.valueOf(i);
                newFiles[i][3] = file;
            }

            Arrays.sort(newFiles, new Comparator<>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    // 2. HEAD부분 기준으로 사전 순 정렬 (대소문자 구분 X)
                    if (o1[0].toUpperCase().equals(o2[0].toUpperCase())) {
                        // 3. HEAD부분이 대소문자 차이외에는 같을 경우, NUMBER의 숫자순으로 정렬
                        // (9 < 10 < 0011 < 012 < 13 < 014)
                        // 012 == 12
                        if (Integer.parseInt(o1[1]) == Integer.parseInt(o2[1])) {
                            // 4. HEAD 부분과 NUMBER의 숫자도 같을 경우, 원래 입력에 주어진 순서를 유지
                            return Integer.parseInt(o1[2]) - Integer.parseInt(o2[2]);
                        } else {
                            return Integer.parseInt(o1[1]) - Integer.parseInt(o2[1]);
                        }
                    } else {
                        return o1[0].toUpperCase().compareTo(o2[0].toUpperCase());
                    }
                }
            });

            String[] answer = new String[newFiles.length];

            for (int i = 0; i < answer.length; i++) {
                answer[i] = newFiles[i][3];
            }

            return answer;
        }
    }
}
