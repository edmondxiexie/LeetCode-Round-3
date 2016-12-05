import java.util.*;

/**
 * Created by Edmond on 12/3/16.
 */
public class LFUCache {

    // cacheMap records the cache
    // <key, value>
    Map<Integer, Integer> cacheMap;

    // freqMap records the frequency
    // <key, frequency>
    Map<Integer, Integer> freqMap;

    // recentMap records the order of keys with the same frequency
    // <frequency, List<key>>
    Map<Integer, List<Integer>> recentMap;

    int minFreq = 1;

    int capacity;

    public LFUCache(int capacity) {
        cacheMap = new HashMap<>();
        freqMap = new HashMap<>();
        recentMap = new HashMap<>();
        this.capacity = capacity;
        recentMap.put(1, new ArrayList<>());
    }

    public int get(int key) {
        if (!cacheMap.containsKey(key)) {
            return -1;
        }
        int value = cacheMap.get(key);
        int oldFreq = freqMap.get(key);
        int newFreq = oldFreq + 1;

        freqMap.put(key, newFreq);
        int index = recentMap.get(oldFreq).indexOf(key);
        recentMap.get(oldFreq).remove(index);
        if (!recentMap.containsKey(newFreq)) {
            recentMap.put(newFreq, new ArrayList<>());
        }
        recentMap.get(newFreq).add(key);

        while (recentMap.size() > 0 && recentMap.get(minFreq).isEmpty()) {
            minFreq++;
        }

        return value;
    }

    public void set(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (cacheMap.containsKey(key)) {
            get(key);
            cacheMap.put(key, value);
            return;
        }

        if (cacheMap.size() >= capacity) {
            int evitKey = recentMap.get(minFreq).remove(0);
            cacheMap.remove(evitKey);
            freqMap.remove(evitKey);
        }
        minFreq = 1;
        cacheMap.put(key, value);
        freqMap.put(key, minFreq);
        recentMap.get(minFreq).add(key);

    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache( 2 /* capacity */ );
        cache.set(1, 1);
        cache.set(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.set(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3.
        cache.set(4, 4);    // evicts key 1.
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }
}
