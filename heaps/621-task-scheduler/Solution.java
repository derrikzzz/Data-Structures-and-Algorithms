import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
// LeetCode 621. Task Scheduler
//
// Tasks are letters A-Z. Each CPU interval runs one task or idles, and two runs
// of the same letter must be at least n intervals apart. Return the minimum
// number of intervals to finish all tasks.
//
// Constraints: 1 <= tasks.length <= 10^4; 0 <= n <= 100.
//
// Approach 1: Greedy counting formula (frame built from the most frequent task)
// Time: O(N) — one counting pass over tasks
// Space: O(1) — freq[26]
//
// Approach 2: Max-heap + cooldown queue (simulation)
// Time: O(answer) — one loop iteration per CPU interval; heap ops are O(log 26) = O(1)
// Space: O(1) — heap and queue hold at most 26 entries
public class Solution {
    public int leastIntervalFormula(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char character : tasks) {
            freq[character - 'A']++;
        }

        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }

        int countMax = 0;
        for (int f : freq) if (f == maxFreq) countMax++;  

        return Math.max(tasks.length, (maxFreq - 1) * (n + 1) + countMax);
    }

    public int leastIntervalHeap(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char character : tasks) {
            freq[character - 'A']++;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int f : freq) {
            if (f > 0) {
                heap.offer(f);
            }
        }

        // earliestRunTime = first interval this task is allowed to run again.
        record Task(int remainingCount, int earliestRunTime) {}

        // Cooldown queue: FIFO works because every task waits the same n, so
        // tasks come off cooldown in the order they went on.
        Queue<Task> cooldown = new ArrayDeque<>();
        int time = 0;

        while (!heap.isEmpty() || !cooldown.isEmpty()) {
            time++; // current interval

            // 1. Release: a task whose earliestRunTime is now can run this interval.
            if (!cooldown.isEmpty() && cooldown.peek().earliestRunTime() == time) {
                heap.offer(cooldown.poll().remainingCount());
            }

            // 2. Run the available task with the most copies left (or idle if heap is empty).
            if (!heap.isEmpty()) {
                int remainingCount = heap.poll() - 1; // one copy just ran
                if (remainingCount > 0) {
                    // Ran at `time`, must skip the next n intervals → next run at time + n + 1.
                    // e.g. n = 2: run at 1, skip 2 and 3, run again at 4.
                    int earliestRunTime = time + n + 1;
                    cooldown.offer(new Task(remainingCount, earliestRunTime));
                }
            }
        }

        return time;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] inputs = {"AAABBB", "ACABDB", "AAABBB", "AAAAAABCDEFG", "ABCDE"};
        int[] ns = {2, 1, 3, 2, 1};
        int[] expected = {8, 6, 10, 16, 5};

        for (int k = 0; k < inputs.length; k++) {
            char[] tasks = inputs[k].toCharArray();
            System.out.printf("%s n=%d -> formula=%d heap=%d (expected %d)%n",
                    inputs[k], ns[k], s.leastIntervalFormula(tasks, ns[k]),
                    s.leastIntervalHeap(tasks, ns[k]), expected[k]);
        }
    }
}
