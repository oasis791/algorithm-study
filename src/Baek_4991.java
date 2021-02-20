//4991번 로봇 청소기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_4991 {
    private static int h;
    private static int w;
    private static String[][] room;
    private static int[] move_h = {-1, 0, 1, 0};
    private static int[] move_w = {0, 1, 0, -1};
    private static Queue<int[]> q;
    private static ArrayList<int[]> dirty;
    private static ArrayList<int[]>[] graph;
    private static boolean[][] visited;
    private static boolean[] visitedDfs;
    private static int[][] path;
    private static int index = 0;
    private static int min;
    private static void initPath() {
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextH = temp[0] + move_h[i];
                int nextW = temp[1] + move_w[i];
                if ((nextH >= 0 && nextH < h && nextW >= 0 && nextW < w) && !visited[nextH][nextW]
                        && !room[nextH][nextW].equals("x")) {
                    path[nextH][nextW] = path[temp[0]][temp[1]] + 1;
                    visited[nextH][nextW] = true;
                    q.offer(new int[]{nextH, nextW});
                }
            }
        }
        for (int i = 0; i < dirty.size(); i++) {
            if(path[dirty.get(i)[0]][dirty.get(i)[1]]!=0) {
                graph[index].add(new int[]{i + 1, path[dirty.get(i)[0]][dirty.get(i)[1]]});
            }
        }
    }

    private static void dfs(ArrayList<int[]> node, int count, int depth) {
        boolean[] temp = Arrays.copyOf(visitedDfs, visitedDfs.length);
        if (depth == dirty.size() + 1) {
            min = Math.min(min, count);
        } else {
            for (int i = 0; i < node.size(); i++) {
                visitedDfs = Arrays.copyOf(temp, temp.length);
                if(!visitedDfs[node.get(i)[0]]){
                    visitedDfs[node.get(i)[0]] = true;
                    dfs(graph[node.get(i)[0]], count + node.get(i)[1], depth + 1);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (h == 0 && w == 0) {
                System.exit(0);
            } else {
                min = 10000;
                index = 0;
                q = new LinkedList<>();
                room = new String[h][w];
                visited = new boolean[h][w];
                path = new int[h][w];
                dirty = new ArrayList<>();
                for (int i = 0; i < h; i++) {
                    String input = bf.readLine();
                    String[] temp = input.split("");
                    for (int j = 0; j < w; j++) {
                        room[i][j] = temp[j];
                        if (room[i][j].equals("o")) {
                            q.offer(new int[]{i, j});
                            visited[i][j] = true;
                        }
                        if (room[i][j].equals("*")) {
                            dirty.add(new int[]{i, j});
                        }
                    }
                }
                graph = new ArrayList[dirty.size() + 1];
                for (int i = 0; i <= dirty.size(); i++) {
                    graph[i] = new ArrayList<>();
                }

                initPath();
                for (int i = 0; i < dirty.size(); i++) {
                    index++;
                    visited = new boolean[h][w];
                    path = new int[h][w];
                    int[] temp = dirty.get(i);
                    visited[temp[0]][temp[1]] = true;
                    q.offer(new int[]{temp[0], temp[1]});
                    initPath();
                }
                visitedDfs = new boolean[dirty.size() + 1];
                visitedDfs[0] = true;
                dfs(graph[0], 0, 1);
                if (min == 10000) {
                    System.out.println(-1);
                } else {
                    System.out.println(min);
                }
            }
        }
    }
}

