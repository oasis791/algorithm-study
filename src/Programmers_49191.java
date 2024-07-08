public class Programmers_49191 {
	static class Solution {
		public static int solution(int n, int[][] results) {
			int answer = 0;
			int[][] rank = new int[n + 1][n + 1];

			for(int i = 0; i < results.length; i++){
				int from = results[i][0];
				int to = results[i][1];

				rank[from][to] = 1;
				rank[to][from] = -1;
			}       

			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					for (int k = 1; k <= n; k++) {
						if(rank[j][i] == 1 && rank[i][k] == 1) {
							rank[j][k] = 1;
							rank[k][j] = -1;
						}
					}
				}
			}

			for(int i = 1; i <= n; i++){
				int count = 0;
				for(int j = 1; j <= n; j++){
					if(rank[i][j] != 0){
						count++;
					}
				}

				if(count == n-1){
					answer++;
				}
			}

			return answer;
		}
	}
}
