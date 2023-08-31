public class Programmers_43162 {
    public static void main(String[] args) {

    }
    static int answer = 0;
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                answer++;
                dfs(i, visited, computers);
            }
        }
        return answer;
    }

    // 1 1 0
    // 1 1 0
    // 0 0 1

    static void dfs(int node, boolean[] visited, int[][] graph) {
        for(int i = 0; i < graph[node].length; i++) {
            if(i != node && !visited[i] && graph[node][i] == 1) {
                visited[i] = true;
                dfs(i, visited, graph);
            }
        }
    }
}