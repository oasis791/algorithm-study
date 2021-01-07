//18352번 특정 거리의 도시 찾기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_18352 {
    private static int N;
    private static int M;
    private static int K;
    private static int X;
    private static int[] dist;
    private static ArrayList<Integer>[] graph;
    private static Queue<Integer> Q = new LinkedList<>();
    private static void dijkstra(){
        while(!Q.isEmpty()){
            int temp = Q.poll();
            for(Integer i : graph[temp]) {
                if(dist[i] == -1) {
                    dist[i] = dist[temp] + 1;
                    Q.offer(i);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());
        dist = new int[N+1];
        Arrays.fill(dist,-1);
        graph = new ArrayList[N+1];
        for(int i=0;i<=N;i++)
            graph[i] = new ArrayList<>();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(bf.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int arrive = Integer.parseInt(st.nextToken());
            graph[start].add(arrive);
        }
        dist[X]=0;
        Q.offer(X);
        dijkstra();
        boolean check = false;
        for(int i=1;i<=N;i++){
            if(dist[i]==K) {
                System.out.println(i);
                check = true;
            }
        }
        if(!check)
            System.out.println(-1);
    }
}
