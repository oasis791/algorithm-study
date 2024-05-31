import java.util.Map;
import java.util.HashMap;

public class Programmers_1835 {
	static class Solution {
		static Map<Character, Integer> map;
		static int answer;
		static String[] sd;
		public static int solution(int n, String[] data) {
			map = new HashMap<>();
			map.put('A', 0);
			map.put('C', 1);
			map.put('F', 2);
			map.put('J', 3);
			map.put('M', 4);
			map.put('N', 5);
			map.put('R', 6);
			map.put('T', 7);

			sd = data;
			answer = 0;

			dfs(new boolean[8], 0, new StringBuilder());

			return answer;
		}

		static void dfs (boolean[] visited, int depth, StringBuilder sb) {
			if(depth == 8) {
				if(check(sb)) answer++;
				return;
			}

			for (int i = 0; i < 8; i++) {
				if(!visited[i]) {
					visited[i] = true;
					sb.append(i);
					dfs (visited, depth + 1, sb);
					sb.deleteCharAt(sb.length() - 1);
					visited[i] = false;
				}
			}
		}

		static boolean check (StringBuilder sb) {

			for (String s : sd) {
				int from = map.get(s.charAt(0));
				int to = map.get(s.charAt(2));
				char op = s.charAt(3);
				int num = s.charAt(4) - '0';

				int fromIndex = 0;
				int toIndex = 0;
				for (int i = 0; i < sb.length(); i++) {
					if(from == sb.charAt(i) - '0') fromIndex = i;
					if(to == sb.charAt(i) - '0') toIndex = i;
				}

				int dist = Math.abs(fromIndex - toIndex) - 1;

				switch(op) {
					case '=':
						if(dist != num) return false;
						break;
					case '>':
						if(dist <= num) return false;
						break;
					case '<':
						if(dist >= num) return false;
						break;
					default:
						break;
				}
			}

			return true;
		}
	}
}
