import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> p = new Stack<>();
        for(Character c : s.toCharArray()) {
            switch(c) {
                case '}':
                    if(p.isEmpty() || p.pop() != '{')
                        return false;
                    break;
                case ']':
                    if(p.isEmpty() || p.pop() != '[')
                        return false;
                    break;
                case ')':
                    if(p.isEmpty() || p.pop() != '(')
                        return false;
                    break;
                default:
                    p.push(c);
                    break;
            }
        }
        return p.isEmpty();
    }
}
