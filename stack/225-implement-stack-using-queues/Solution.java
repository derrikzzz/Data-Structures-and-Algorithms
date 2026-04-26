import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    class MyStack {
        //treat q1 as the main "stack" but can swap reference for the pop operation
        private Queue<Integer> q1 = new LinkedList<>();
        private Queue<Integer> q2 = new LinkedList<>();

        public void push(int x) {
            q1.offer(x);
            
        }
        
        public int pop() {
            shiftAllButLast();
            int top = q1.poll();
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;

            return top;
        }
        
        public int top() {
            shiftAllButLast();
            int top = q1.poll();
            q2.offer(top);
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
            return top;
        }
        
        public boolean empty() {
            return q1.isEmpty();
        }

        private void shiftAllButLast() {
            while (q1.size() > 1) {
                q2.offer(q1.poll());
            }
        }
    }

}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
