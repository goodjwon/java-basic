package com.example.ch4.def.stc;

import java.util.Stack;

public class BracketValidation {
    public static void main(String[] args) {
        String expression = "{[()]}";
        System.out.println("유효한 괄호인가요? " + isValid(expression));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.empty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.empty();
    }
}
