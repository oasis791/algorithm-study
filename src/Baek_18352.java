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
    private static ArrayList<Integer> graph[];
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();
    private static void dijkstra(){
        for(Integer i : graph[X]){
            dist[i] = 1;
            pq.offer(i);
        }
        while(!pq.isEmpty()){
            int temp = pq.poll();
            for(Integer i : graph[temp]){
                dist[i] = Math.min(dist[i],dist[temp]+1);
                pq.offer(i);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        N=Integer.parseInt(st.nextToken()); // 도시 수
        M=Integer.parseInt(st.nextToken()); // 도시 사이 간선
        K=Integer.parseInt(st.nextToken()); // 최단 거리가 K인 도시를 뽑아야됨
        X=Integer.parseInt(st.nextToken()); // 도시 X에서 시작함
        dist = new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
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
        dijkstra();
        boolean check = false;
        for(int i=1;i<=N;i++){
            if(dist[i]==K) {
                System.out.println(i);
                check = true;
            }
        }
        if(check==false)
            System.out.println(-1);
    }
}
