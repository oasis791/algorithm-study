import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_17478 {
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        answer.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
        func(0, n, new StringBuilder());
        System.out.print(answer);
    }

    static void func(int depth, int n, StringBuilder prefix) {
        if (depth >= 1) {
            for (int i = 0; i < 4; i++) {
                prefix.append("_");
            }
        }

        answer.append(prefix).append("\"재귀함수가 뭔가요?\"\n");
        if (depth == n) {
            answer.append(prefix).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
        } else {
            answer.append(prefix).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n")
                    .append(prefix).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n")
                    .append(prefix).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
            func(depth + 1, n, prefix);
        }
        answer.append(prefix).append("라고 답변하였지.\n");
        if (depth >= 1) {
            for (int i = 0; i < 4; i++) {
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
    }
}
