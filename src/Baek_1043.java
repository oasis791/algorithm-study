//1043번 거짓말
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_1043 {
    private static int N;
    private static int M;
    private static boolean[] peopleNum;
    private static ArrayList<Integer>[] graph;
    private static int count = 0;

    private static void dfs(ArrayList<Integer> dfsGraph) {
        for (int i = 0; i < dfsGraph.size(); i++) {
            if (!peopleNum[dfsGraph.get(i)]) {
                peopleNum[dfsGraph.get(i)] = true;
                dfs(graph[dfsGraph.get(i)]);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        peopleNum = new boolean[N + 1];
        // 거짓말을 알고 있는 사람 입력
        st = new StringTokenizer(bf.readLine(), " ");
        int people = Integer.parseInt(st.nextToken());
        if (people == 0) {
            System.out.println(M);
            System.exit(0);
        }
        while (st.hasMoreTokens()) {
            peopleNum[Integer.parseInt(st.nextToken())] = true;
        }
        // 다음 입력을 StringBuilder에 저장
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < M; i++) {
            temp.append(bf.readLine());
            temp.append("\n");
        }
        // 사람들의 상관 관계를 graph라는 ArrayList에 저장
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        String input = temp.toString();
        st = new StringTokenizer(input, "\n");
        while (st.hasMoreTokens()) {
            StringTokenizer st2 = new StringTokenizer(st.nextToken(), " ");
            int[] num = new int[Integer.parseInt(st2.nextToken())];
            int index = 0;
            while (st2.hasMoreTokens()) {
                num[index] = Integer.parseInt(st2.nextToken());
                index++;
            }
            for (int i = 0; i < num.length; i++) {
                for (int j = 0; j < num.length; j++) {
                    if (i != j) {
                        graph[num[i]].add(num[j]);
                    }
                }
            }
        }
        for (int i = 1; i < peopleNum.length; i++) {
            if (peopleNum[i]) {
                dfs(graph[i]);
            }
        }

        input = temp.toString();
        st = new StringTokenizer(input, "\n");
        while (st.hasMoreTokens()) {
            boolean check = false;
            StringTokenizer st2 = new StringTokenizer(st.nextToken(), " ");
            people = Integer.parseInt(st2.nextToken());
            for (int i = 0; i < people; i++) {
                if(peopleNum[Integer.parseInt(st2.nextToken())]){
                    check = true;
                    break;
                }
            }
            if(!check){
                count++;
            }
        }

        System.out.println(count);
    }
}
