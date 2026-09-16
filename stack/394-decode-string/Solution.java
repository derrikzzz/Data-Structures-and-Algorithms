import java.util.Stack;

public class Solution {
    public String decodeString(String s) {
        Stack<Integer> numberStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();

        int currNumber = 0;
        String currString = "";

        for (char ch : s.toCharArray()) {
            if (ch == '[') {
                numberStack.push(currNumber);
                stringStack.push(currString);
                currNumber = 0;
                currString = "";
            } else if (ch == ']') {
                int num = numberStack.pop();
                String prevString = stringStack.pop();
                currString = prevString + currString.repeat(num);
            } else if (Character.isDigit(ch)) {
                currNumber = currNumber * 10 + (ch - '0');
            } else {
                currString += ch;
            }
        }

        return currString;
    }
}
