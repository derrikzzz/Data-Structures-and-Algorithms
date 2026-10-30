import java.util.ArrayDeque;
import java.util.Deque;

class MyQueue {
    // ArrayDeque used as stack (peek/pop/push all operate on the head). 
    // Preferred over java.util.Stack, which is sycnrhonised legacy vector

    private final Deque<Integer> in = new ArrayDeque<>();
    private final Deque<Integer> out = new ArrayDeque<>();

    // Enqueue
    public void push(int x) {
        in.push(x);
    }

    // Dequeue
    public int pop() {
        shiftIfNeeded();
        return out.pop();
    }

    // front
    public int peek() {
        shiftIfNeeded();
        return out.peek();

    }

    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }

    private void shiftIfNeeded() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
    }
}
