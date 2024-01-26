import java.io.*;
import java.util.*;

public class Softeer_6289 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] weights = new int[n + 1];
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        
        st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }
        
        int answer = 0;

        loop:
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < graph[i].size(); j++) {
                if(weights[graph[i].get(j)] >= weights[i]) {
                    continue loop;
                }
            }
            answer++;
        }

        System.out.println(answer);
    }
}

