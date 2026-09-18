import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeMap {

    record Entry(int timestamp, String value) {}

    private final Map<String, List<Entry>> store = new HashMap<>();

    public TimeMap() {}

    public void set(String key, String value, int timestamp) {
        store.computeIfAbsent(key, k -> new ArrayList<>()).add(new Entry(timestamp, value));
    }

    public String get(String key, int timestamp) {
        if (!store.containsKey(key)) return "";

        List<Entry> entries = store.get(key);
        int lo = 0, hi = entries.size() - 1, idx = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (entries.get(mid).timestamp() <= timestamp) {
                idx = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return idx == -1 ? "" : entries.get(idx).value();
    }
}
