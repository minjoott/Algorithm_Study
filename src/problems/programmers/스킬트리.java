package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/49993
 * [Programmers] 49993. 스킬 트리
 * solved at: 260115
 */

import java.util.HashMap;
import java.util.Map;

public class 스킬트리 {
    class Solution1 {
        public int solution(String skill, String[] skill_trees) {
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < skill.length(); i++)
                map.put(skill.charAt(i), i);

            int answer = 0;
            for (String tree : skill_trees) {
                boolean[] done = new boolean[skill.length()];
                boolean result = true;
                for (char t : tree.toCharArray()) {
                    if (!map.containsKey(t)) continue;

                    if (map.get(t) > 0 && !done[map.get(t) - 1]) {  //선스킬을 배우지 않은 경우
                        result = false;
                        break;
                    }
                    done[map.get(t)] = true;  //선스킬을 모두 배운 경우 => 현스킬 배우기
                }
                if (result) answer++;
            }
            return answer;
        }
    }
}
