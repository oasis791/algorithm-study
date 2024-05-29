import java.util.*;

public class Programmers_43164 {
	static class Solution {
		static boolean[] visited;
		static ArrayList<String> routes;
		public static String[] solution(String[][] tickets) {
			String[] answer = {};
			visited = new boolean[tickets.length];
			routes = new ArrayList<>();

			dfs("ICN", "ICN", tickets, 0);

			Collections.sort(routes);
			answer = routes.get(0).split(" ");

			return answer;
		}

		static void dfs (String start, String route, String[][] tickets, int depth) {
			if(depth == tickets.length) {
				routes.add(route);
				return;
			}

			for (int i = 0; i < tickets.length; i++) {
				if(start.equals(tickets[i][0]) && !visited[i]) {
					visited[i] = true;
					dfs(tickets[i][1], route + " " + tickets[i][1], tickets, depth + 1);
					visited[i] = false;
				}
			}
		}
	}
}
