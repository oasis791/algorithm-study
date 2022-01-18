import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Programmers_2021_kakao {
    public static void main(String[] args) throws IOException {
        {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String new_id = bf.readLine();
            StringBuilder sb;
            //1단계
            String firstStep = new_id.toLowerCase();
            System.out.println(firstStep);
            //2단계
            String match = "[^a-z0-9[-][_][.]]";
            String secondStep = firstStep.replaceAll(match, "");
            System.out.println(secondStep);
            //3단계
//            String thirdStep = secondStep;
//            String[] chars = thirdStep.split("");
//            int count = 0;
//            for (int i = 0; i < chars.length; i++) {
//                if (chars[i].equals(".")) {
//                    count++;
//                } else {
//                    if (count >= 2) {
//                        sb = new StringBuilder();
//                        for (int j = 0; j < count; j++) {
//                            sb.append(".");
//                        }
//                        match = sb.toString();
//                        thirdStep = thirdStep.replace(match, ".");
//                    }
//                    count = 0;
//                }
//            }
//            if (count >= 2) {
//                sb = new StringBuilder();
//                for (int j = 0; j < count; j++) {
//                    sb.append(".");
//                }
//                match = sb.toString();
//                thirdStep = thirdStep.replace(match, ".");
//            }
            String thirdStep = secondStep.replaceAll("[.]{2,}", ".");
            System.out.println(thirdStep);
            //4단계
            sb = new StringBuilder(thirdStep);
            String fourthStep = thirdStep;
            if (fourthStep.startsWith("."))
                fourthStep = sb.deleteCharAt(0).toString();
            if (fourthStep.endsWith("."))
                fourthStep = sb.deleteCharAt(sb.length() - 1).toString();
            System.out.println(fourthStep);
            //5단계
            String fifthStep = fourthStep;
            if (fourthStep.equals(""))
                fifthStep = "a";
            System.out.println(fifthStep);
            //6단계
            String sixthStep = fifthStep;
            if (sixthStep.length() >= 16) {
                sb = new StringBuilder(sixthStep);
                sixthStep = sb.delete(15, sb.length()).toString();
            }
            sb = new StringBuilder(sixthStep);
            if (sixthStep.endsWith("."))
                sixthStep = sb.deleteCharAt(sb.length() - 1).toString();
            System.out.println(sixthStep);
            //7단계
            String seventhStep = sixthStep;
            if (seventhStep.length() <= 2) {
                char target;
                if (seventhStep.length() == 1) {
                    target = seventhStep.charAt(0);
                    seventhStep += target;
                    seventhStep += target;
                }
                if (seventhStep.length() == 2) {
                    target = seventhStep.charAt(1);
                    seventhStep += target;
                }
            }
            String answer = seventhStep;
            System.out.println(answer);

        }
    }
}