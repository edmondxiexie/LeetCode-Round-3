import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Edmond on 12/3/16.
 */
public class LRUCache {

    // <key, value>
    Map<Integer, Integer> cacheMap;

    // <key>
    List<Integer> orderList;

    int capacity;

    public LRUCache(int capacity) {
        cacheMap = new HashMap<>();
        orderList = new ArrayList<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cacheMap.containsKey(key)) {
            return -1;
        }
        int value = cacheMap.get(key);
        int index = orderList.indexOf(key);
        orderList.remove(index);
        orderList.add(key);
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
            int evitKey = orderList.get(0);
            orderList.remove(0);
            cacheMap.remove(evitKey);
        }
        cacheMap.put(key, value);
        orderList.add(key);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.set(1, 1);
        cache.set(2, 2);
        System.out.println(cache.get(2));
        cache.set(3, 3);
        System.out.println(cache.get(1));
        cache.set(2, 2);
        cache.set(1, 1);
        System.out.println(cache.get(2));
    }
}
