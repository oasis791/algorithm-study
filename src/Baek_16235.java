//16235번 나무 재테크
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_16235 {
    private static int N; // 땅크기
    private static int M; // 처음 산 나무의 개수
    private static int K; // 몇년 후
    private static int year = 0;
    private static int[] move_x = {-1,-1,-1,0,0,1,1,1};
    private static int[] move_y = {-1,0,1,-1,1,-1,0,1};
    private static int[][] food;
    private static ArrayList<Integer>[][] tree;
    private static int[][] A;
    private static void springAndSummer(){
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(tree[i][j].size()!=0){
                    int dead = 0;
                    Collections.sort(tree[i][j]);
                    for(int k=0;k<tree[i][j].size();k++){
                        int temp = tree[i][j].get(k);
                        if(food[i][j]>=temp){
                            food[i][j] -= temp;
                            tree[i][j].set(k,temp+1);
                        }
                        else {
                            dead += temp / 2;
                            tree[i][j].remove(k);
                            k--;
                        }
                    }
                    food[i][j] += dead;
                }
            }
        }
    }
    private static void autumn(){
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(tree[i][j].size()!=0){
                    for(int k=0;k<tree[i][j].size();k++){
                        if(tree[i][j].get(k) % 5 == 0){
                            for(int l=0;l<8;l++){
                                int nextX = i + move_x[l];
                                int nextY = j + move_y[l];
                                if(nextX<1||nextX>N||nextY<1||nextY>N)
                                    continue;
                                else{
                                    tree[nextX][nextY].add(1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private static void winter(){
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++)
                food[i][j] = food[i][j] + A[i][j];
        }
    }
    private static int count(){
        int count = 0;
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(tree[i][j].size()!=0) {
                    for (int k = 0; k < tree[i][j].size(); k++) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N+1][N+1];
        for(int i=0;i<=N;i++){
            for(int j=0;j<=N;j++)
                tree[i][j] = new ArrayList<>();
        }
        food = new int[N+1][N+1];
        for(int i=0;i<=N;i++) {
            Arrays.fill(food[i], 5);
        }
        A = new int[N+1][N+1];
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for(int j=1;j<=N;j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(bf.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            tree[x][y].add(z);
        }
        while(year<K){
            springAndSummer();
            autumn();
            winter();
            year++;
        }
        System.out.println(count());
    }
}
