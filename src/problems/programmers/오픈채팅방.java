package problems.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42888
 * [Programmers] 42888. 오픈채팅방 (2019 카카오)
 * solved at: 251229
 */

import java.util.*;

public class 오픈채팅방 {
    class Solution1 {
        public String[] solution(String[] record) {
            StringTokenizer st;
            List<String[]> list = new ArrayList<>();
            Map<String, String> map = new HashMap<>();

            for (String r : record) {
                st = new StringTokenizer(r);
                String action = st.nextToken();
                String id = st.nextToken();

                if (action.equals("Enter")) {
                    String nick = st.nextToken();
                    map.put(id, nick);
                    list.add(new String[]{action, id});
                }
                else if (action.equals("Leave")) {
                    list.add(new String[]{action, id});
                }
                else {  //Change
                    String nick = st.nextToken();
                    map.put(id, nick);
                }
            }

            String[] result = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                String action = list.get(i)[0];
                String nick = map.get(list.get(i)[1]);
                String actionByKo = (action.equals("Enter")) ? "님이 들어왔습니다." : "님이 나갔습니다.";
                result[i] = nick + actionByKo;
            }
            return result;
        }
    }
}
