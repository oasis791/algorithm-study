import java.util.Arrays;
import java.util.PriorityQueue;

public class Programmers_Not_Completion {
    public static void main(String[] args) {

        System.out.println(solution(new String[]{"leo", "kiki", "eden"}, new String[]{"eden", "kiki"}));
        System.out.println(solution(new String[]{"marina", "josipa", "nikola", "vinko", "filipa"}, new String[]{"josipa", "filipa", "marina", "nikola"}));
        System.out.println(solution(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"}));

    }
    public static String solution(String[] participant, String[] completion) {
        PriorityQueue<String> pq = new PriorityQueue<>();
        PriorityQueue<String> cq = new PriorityQueue<>();
        pq.addAll(Arrays.asList(participant));
        cq.addAll(Arrays.asList(completion));

        while (!cq.isEmpty()) {
            String s1 = pq.poll();
            String s2 = cq.poll();
            if (!s1.equals(s2)) {
                return s1;
            }
        }

        return pq.poll();
    }

}
