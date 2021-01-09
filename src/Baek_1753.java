//1753번 최단경로

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_1753 {
    private static int V;
    private static int E;
    private static int K;
    private static int[] dist;
    private static ArrayList<Property>[] graph;
    private static PriorityQueue<Property> pq = new PriorityQueue<>(new Comparator<Property>() {
        @Override
        public int compare(Property o1, Property o2) {
            if(o1.weight < o2.weight)
                return -1;
            else if(o1.weight > o2.weight)
                return 1;
            else
                return 0;
        }
    });
    static class Property{
        public int arrive;
        public int weight;
        public Property(int v,int w){
            arrive = v;
            weight = w;
        }
    }
    private static void dijkstra() {
        while (!pq.isEmpty()) {
            Property temp = pq.poll();
            for (Property i : graph[temp.arrive]) {
                if (dist[i.arrive] > dist[temp.arrive] + i.weight) {
                    dist[i.arrive] = dist[temp.arrive] + i.weight;
                    pq.offer(new Property(i.arrive, dist[i.arrive]));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(bf.readLine());
        dist = new int[V+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        graph = new ArrayList[V+1];
        for(int i=0;i<=V;i++)
            graph[i] = new ArrayList<>();

        for(int i=0;i<E;i++){
            st = new StringTokenizer(bf.readLine()," ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Property(v,w));
        }
        pq.offer(new Property(K,0));
        dist[K] = 0;
        dijkstra();
        for(int i=1;i<=V;i++){
            if(dist[i]==Integer.MAX_VALUE)
                System.out.println("INF");
            else
                System.out.println(dist[i]);
        }
    }
}