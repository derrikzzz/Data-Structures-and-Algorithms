import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Codeforces D. OutOfMemoryError
//
// t test cases. Per case: n, m, h; original array a[1..n] (each a_i <= h).
// Each op sets a[b] += c. If a[b] > h after an op the machine crashes and the
// WHOLE array resets to its original values. Output the final array.
//
// Simulation. Two things make it fast:
//   * Only the touched element can newly exceed h (every other element was
//     already <= h and is unchanged since the last reset), so checking arr[b]
//     alone detects a crash.
//   * A crash logically resets ALL n elements, but only the indices touched
//     since the last reset actually differ from original -- revert just those.
//     Each op adds at most one touched index, so total revert work is O(m).
// This keeps each test case O(n + m); a naive whole-array copy per crash would
// be O(n*m) and TLE (test 3 crashes on every operation).
//
// long avoids overflow: arr[b] can momentarily reach h + c (~2e9) before the
// crash check trims it back.
public class Solution {

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(new java.io.BufferedWriter(new java.io.OutputStreamWriter(System.out)));

        int t = in.nextInt();
        while (t-- > 0) {
            solve(in, out);
        }

        out.flush();
    }

    private static void solve(FastReader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        long h = in.nextLong();

        long[] original = new long[n];
        for (int i = 0; i < n; i++) {
            original[i] = in.nextLong();
        }

        // Working copy we mutate; on a crash revert only the touched indices.
        long[] arr = original.clone();
        int[] touched = new int[m]; // indices modified since the last reset
        int touchedCount = 0;

        for (int i = 0; i < m; i++) {
            int b = in.nextInt() - 1; // 1-indexed in input -> 0-indexed
            long c = in.nextLong();

            arr[b] += c;
            touched[touchedCount++] = b;

            if (arr[b] > h) {
                // Crash: revert every index changed since the last reset.
                for (int j = 0; j < touchedCount; j++) {
                    arr[touched[j]] = original[touched[j]];
                }
                touchedCount = 0;
            }
        }

        printArray(arr, out);
    }

    private static void printArray(long[] arr, PrintWriter out) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(' ');
            sb.append(arr[i]);
        }
        out.println(sb);
    }

    // ---- Fast I/O ----
    static class FastReader {
        private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer st;

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}
