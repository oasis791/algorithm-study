import java.util.HashMap;

public class Programmers_49993 {

    static class Solution {
        public int solution(String skill, String[] skill_trees) {
            int answer = 0;
            HashMap<Character, Integer> map = new HashMap<>();

            int skillIndex = 0;
            for(int i = 0; i < skill.length(); i++) {
                map.put(skill.charAt(i), skillIndex++);
            }

            loop:
            for(int i = 0; i < skill_trees.length; i++) {
                skillIndex = 0;
                for(int j = 0; j < skill_trees[i].length(); j++) {
                    if(skillIndex >= skill.length()) {
                        break;
                    }

                    if(map.containsKey(skill_trees[i].charAt(j))) {
                        if(map.get(skill_trees[i].charAt(j)) <= skillIndex) {
                            skillIndex++;
                        } else {
                            continue loop;
                        }
                    }
                }
                answer++;
            }

            return answer;
        }
    }
}
