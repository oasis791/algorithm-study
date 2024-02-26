import java.util.*;

public class Programmers_42579 {
    static class Solution {
        public int[] solution(String[] genres, int[] plays) {
            ArrayList<Integer> answer = new ArrayList<>();
            Map<String, Integer> map = new HashMap<>();
            Map<String, Integer> genreIndex = new HashMap<>();
            ArrayList<int[]>[] sub = new ArrayList[genres.length];
            Stack<String> stk = new Stack<>();

            for (int i = 0; i < genres.length; i++) {
                sub[i] = new ArrayList<>();
            }

            int index = 0;
            for (int i = 0; i < genres.length; i++) {
                map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
                if(!genreIndex.containsKey(genres[i])) {
                    genreIndex.put(genres[i], index++);
                }

                int subIndex = genreIndex.get(genres[i]);

                if(sub[subIndex].size() >= 2) {
                    int changeIndex = -1;
                    int min = Integer.MAX_VALUE;
                    int num = Integer.MAX_VALUE;

                    for (int j = 0; j < 2; j++) {
                        int[] temp = sub[subIndex].get(j);
                        if(temp[0] < min) {
                            min = temp[0];
                            num = temp[1];
                            changeIndex = j;
                        } else if (temp[0] == min && temp[1] > num) {
                            num = temp[1];
                            changeIndex = j;
                        }
                    }

                    if((min < plays[i]) || (min == plays[i] && num > i)) {
                        sub[subIndex].get(changeIndex)[0] = plays[i];
                        sub[subIndex].get(changeIndex)[1] = i;
                    } 
                } else {
                    sub[subIndex].add(new int[]{plays[i], i});
                }
            }

            PriorityQueue<String[]> pq = new PriorityQueue<>(new Comparator<>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    return Integer.parseInt(o2[1]) - Integer.parseInt(o1[1]);
                }
            });

            for (String key : map.keySet()) {
                pq.offer(new String[]{key, String.valueOf(map.get(key))});
            }

            while(!pq.isEmpty()) {
                String s = pq.poll()[0];
                int subIndex = genreIndex.get(s);

                Collections.sort(sub[subIndex], new Comparator<>(){
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        if(o1[0] != o2[0]) {
                            return o2[0] - o1[0];
                        } else {
                            return o1[1] - o2[1];
                        }
                    }
                });

                for (int i = 0; i < sub[subIndex].size(); i++) {
                    answer.add(sub[subIndex].get(i)[1]);
                }
            }

            int[] arr = new int[answer.size()];
            for (int i = 0; i < answer.size(); i++) {
                arr[i] = answer.get(i);
            }

            return arr;
        }
    }
}
