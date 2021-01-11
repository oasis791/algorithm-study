//1916번 최소비용 구하기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_1916 {
    private static int N;
    private static int M;
    private static int start;
    private static int end;
    private static int[] dist;
    private static ArrayList<Property_1916>[] graph;
    private static PriorityQueue<Property_1916> pq = new PriorityQueue<>(new Comparator<Property_1916>() {
        @Override
        public int compare(Property_1916 o1, Property_1916 o2) {
            if(o1.weight < o2.weight)
                return -1;
            else if(o1.weight > o2.weight)
                return 1;
            else
                return 0;
        }
    });
    static class Property_1916{
        public int arrive;
        public int weight;
        public Property_1916(int v,int w){
            this.arrive = v;
            this.weight = w;
        }
    }
    private static void dijkstra() {
        while (!pq.isEmpty()) {
            Property_1916 temp = pq.poll();
            for (Property_1916 i : graph[temp.arrive]) {
                if (dist[i.arrive] > dist[temp.arrive] + i.weight) {
                    dist[i.arrive] = dist[temp.arrive] + i.weight;
                    pq.offer(new Property_1916(i.arrive, dist[i.arrive]));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
        dist = new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        graph = new ArrayList[N+1];
        for(int i=0;i<=N;i++)
            graph[i] = new ArrayList<>();
        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(bf.readLine()," ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Property_1916(v,w));
        }
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        dist[start] = 0;
        pq.offer(new Property_1916(start,0));
        dijkstra();
        System.out.println(dist[end]);
    }
}
