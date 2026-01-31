package problems.baekjoon;

/**
 * https://www.acmicpc.net/problem/1157
 * [Baekjoon] 1157. 단어 공부
 * solved at: 260131
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 단어공부 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Character, Integer> set = new HashMap<>();
        String str = br.readLine();
        for (char ch : str.toCharArray()) {
            char element = ch;
            if (ch >= 'a') {  //소문자면 대문자로 변환
                int diff = ch - 'a';
                element = (char) ('A' + diff);
            }
            set.put(element, set.getOrDefault(element, 0) + 1);
        }

        List<Entry> list = new ArrayList<>();
        for (char ch : set.keySet()) {
            int cnt = set.get(ch);
            Entry entry = new Entry(ch, cnt);
            list.add(entry);
        }

        Collections.sort(list, (a, b) -> b.cnt - a.cnt);

        int maxCnt = list.get(0).cnt;
        char answer = list.get(0).ch;
        if (list.size() >= 2 && list.get(1).cnt == maxCnt) answer = '?';
        System.out.println(answer);
        return;
    }

}

class Entry {
    char ch;
    int cnt;

    Entry (char ch, int cnt) {
        this.ch = ch;
        this.cnt = cnt;
    }
}
