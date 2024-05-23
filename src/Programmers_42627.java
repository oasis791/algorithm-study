import java.util.PriorityQueue;
import java.util.Comparator; 
import java.util.Arrays;

public class Programmers_42627 {
	static class Solution {
		public static int solution(int[][] jobs) {
			PriorityQueue<int[]> rq = new PriorityQueue<>(new Comparator<>() {
					@Override
					public int compare(int[] o1, int[] o2) {
					return o1[1] - o2[1];
					}
					});

			Arrays.sort(jobs, new Comparator<>() {
					@Override
					public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
					}
					});

			int answer = 0;
			int index = 0;
			int curTime = 0;
			while (index < jobs.length || !rq.isEmpty()) {

				while (index < jobs.length && jobs[index][0] <= curTime) {
					rq.offer(jobs[index++]);
				}

				if(!rq.isEmpty()) {
					int[] cur = rq.poll();
					curTime += cur[1];
					answer += curTime - cur[0];
				} else {
					curTime++;
				}
			}

			return answer / jobs.length;
		}
	}
}
