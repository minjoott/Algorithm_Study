package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParentheses {
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 != 0) return false;

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {  // 여는 괄호면
                stack.push(c);
            }
            else {  // 닫는 괄호면
                if (stack.isEmpty()) return false;

                char pop = stack.pop();
                if ((pop == '(' && c != ')') || (pop == '[' && c != ']') || (pop == '{' && c != '}')) return false;
            }
        }

        if (stack.isEmpty())
            return true;
        else
            return false;
    }
}
