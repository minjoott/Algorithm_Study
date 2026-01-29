package problems.programmers;

import java.util.HashSet;
import java.util.Set;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/81302
 * [Programmers] 81302. 거리두기 확인하기
 * solved at: 260129
 */

public class 거리두기확인하기 {
    class Solution1 {
        int[] DR = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] DC = {0, 1, 1, 1, 0, -1, -1, -1};

        public int[] solution(String[][] places) {
            int[] answer = new int[5];

            for (int p = 0; p < 5; p++) {  //대기실
                String[] place = places[p];
                Set<String> satisfied = new HashSet<>();  //거리두기 만족한 응시자 집합
                boolean result = true;

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        char ch = place[i].charAt(j);

                        if (ch == 'P') {  //응시자
                            for (int d = 0; d < 8; d += 2) {
                                int r = i + DR[d], c = j + DC[d];
                                if (r < 0 || r >= 5 || c < 0 || c >= 5) continue;

                                if (place[r].charAt(c) == 'P') {  //동서남북으로 붙어 앉은 응시자 존재
                                    if (satisfied.contains(r + "," + c)) continue;

                                    result = false;
                                    break;
                                }
                            }

                            if (!result) break;

                            for (int d = 1; d < 8; d += 2) {
                                int r = i + DR[d], c = j + DC[d];
                                if (r < 0 || r >= 5 || c < 0 || c >= 5) continue;

                                if (place[r].charAt(c) == 'P') {  //파티션 없이 대각선으로 붙어 앉은 응시자 존재
                                    if (satisfied.contains(r + "," + c)) continue;

                                    int[] need1 = {r, j};
                                    int[] need2 = {i, c};
                                    if (place[need1[0]].charAt(need1[1]) != 'X' || place[need2[0]].charAt(need2[1]) != 'X') {
                                        result = false;
                                        break;
                                    }
                                }
                            }

                            if (!result) break;

                            for (int d = 0; d < 8; d += 2) {
                                int r = i + DR[d] * 2, c = j + DC[d] * 2;
                                if (r < 0 || r >= 5 || c < 0 || c >= 5) continue;

                                if (place[r].charAt(c) == 'P') {  //파티션 없이 동서남북으로 2칸 떨어져 앉은 응시자 존재
                                    if (satisfied.contains(r + "," + c)) continue;

                                    int needR = i + DR[d], needC = j + DC[d];
                                    if (place[needR].charAt(needC) != 'X') {
                                        result = false;
                                        break;
                                    }
                                }
                            }

                            if (!result) break;

                            satisfied.add(i + "," + j);  //(i,j) 응시자는 거리두기 만족!
                        }
                        if (!result) break;
                    }
                    if (!result) break;
                }
                answer[p] = (result) ? 1 : 0;
            }
            return answer;
        }
    }
}
