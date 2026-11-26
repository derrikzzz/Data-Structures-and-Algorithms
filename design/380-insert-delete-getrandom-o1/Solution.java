// LeetCode 380. Insert Delete GetRandom O(1)
//
// RandomizedSet()
//   - insert(val): insert val if not present. Return true if it was inserted, false if already present.
//   - remove(val): remove val if present. Return true if it was removed, false if not present.
//   - getRandom(): return a random element. Each element must have the same probability
//     of being returned. At least one element exists whenever this is called.
//   - Each function must run in O(1) average time.
//
// Constraints: -2^31 <= val <= 2^31 - 1; at most 2 * 10^5 calls to insert, remove and getRandom.
//
// Time: O(1) average per insert / remove / getRandom — HashMap ops are expected O(1);
//       remove swaps the target with the last element so the list only shrinks at the end
// Space: O(n) — map + list each hold every element once
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

class RandomizedSet {
    // Mapping of value -> index in the values list
    private final Map<Integer, Integer> valueToIndex;
    private final List<Integer> values;

    public RandomizedSet() {
        this.valueToIndex = new HashMap<>();
        this.values = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (valueToIndex.containsKey(val)) {
            return false;
        }
        valueToIndex.put(val, values.size());
        values.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!valueToIndex.containsKey(val)) {
            return false;
        }

        int indexToRemove = valueToIndex.get(val);
        int lastValue = values.get(values.size() - 1);

        // Move the last element into the spot of the element to remove
        values.set(indexToRemove, lastValue);
        valueToIndex.put(lastValue, indexToRemove);

        // Remove the duplicate last element and delete val from the map
        values.remove(values.size() - 1);
        valueToIndex.remove(val);

        return true;
    }

    public int getRandom() {
        int randomIndex = ThreadLocalRandom.current().nextInt(values.size());
        return values.get(randomIndex);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
