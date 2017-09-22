package Onsite;

import java.util.*;

/**
 * Created by Edmond on 8/10/17.
 */
public class Other {

    /**
     * 146. LRU Cache.
     */
    public class LRUCache {
        private int capacity;
        Map<Integer, LRUNode> cacheMap;
        LRUNode head;
        LRUNode end;


        public LRUCache(int capacity) {
            head = null;
            end = null;
            cacheMap = new HashMap<>();
            this.capacity = capacity;
        }

        public void remove(LRUNode node) {
            if (node.pre != null) {
                node.pre.next = node.next;
            } else {
                head = node.next;
            }
            if (node.next != null) {
                node.next.pre = node.pre;
            } else {
                end = node.pre;
            }
        }

        public void addHead(LRUNode node) {
            node.next = head;
            node.pre = null;
            if (head != null) {
                head.pre = node;
            }
            head = node;
            if (end == null) {
                end = head;
            }
        }

        public int get(int key) {
            if (!cacheMap.containsKey(key)) {
                return -1;
            }
            LRUNode node = cacheMap.get(key);
            int value = node.value;
            remove(node);
            addHead(node);
            return value;
        }

        public void put(int key, int value) {
            if (cacheMap.containsKey(key)) {
                LRUNode node = cacheMap.get(key);
                remove(node);
            } else {
                if (capacity == cacheMap.size()) {
                    cacheMap.remove(end.key);
                    remove(end);
                }
            }
            LRUNode newNode = new LRUNode(key, value);
            addHead(newNode);
            cacheMap.put(key, newNode);
        }

        public class LRUNode {
            public int key;
            public int value;
            public LRUNode pre;
            public LRUNode next;
            public LRUNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    public class LFUCache {
        private int capacity;

        // <key, LFUNode>
        private Map<Integer, Integer> cacheMap;

        // <key, Freq>
        private Map<Integer, Integer> freqMap;

        // <freq, list<key>>
        private Map<Integer, LinkedHashSet<Integer>> freqListMap;

        private int min = -1;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            cacheMap = new HashMap<>();
            freqMap = new HashMap<>();
            freqListMap = new HashMap<>();
            freqListMap.put(1, new LinkedHashSet<>());
        }

        public int get(int key) {
            if (!cacheMap.containsKey(key)) {
                return -1;
            }
            int value = cacheMap.get(key);
            int freq = freqMap.get(key);
            freqMap.put(key, freq + 1);
            freqListMap.get(freq).remove(key);
            if (freq == min && freqListMap.get(freq).isEmpty()) {
                min++;
            }

            if (!freqListMap.containsKey(freq + 1)) {
                freqListMap.put(freq + 1, new LinkedHashSet<>());
            }
            freqListMap.get(freq + 1).add(key);
            return value;
        }

        public void put(int key, int value) {
            if (capacity <= 0) {
                return;
            }
            if (cacheMap.containsKey(key)) {
                cacheMap.put(key, value);
                get(key);
            } else {
                if (cacheMap.size() >= capacity) {
                    int evict = freqListMap.get(min).iterator().next();
                    freqListMap.get(min).remove(evict);
                    cacheMap.remove(evict);
                    freqMap.remove(evict);
                }
                cacheMap.put(key, value);
                freqMap.put(key, 1);
                min = 1;
                freqListMap.get(1).add(key);
            }

        }

    }


    /**
     * random maximum index.
     * @param nums
     * @return
     */
    public int randomMaxIndex(int[] nums) {
        int max = nums[0];
        for (int n : nums) {
            max = Math.max(max, n);
        }
        int count = 0;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == max) {
                count++;
                Random random = new Random();
                if (random.nextInt(count) == count - 1) {
                    index = i;
                }
            }
        }
        return index;
    }

    public void test() {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        lfuCache.get(1);
        lfuCache.put(3, 3);
        System.out.println(lfuCache.get(2));

        System.out.println(lfuCache.get(3));

        lfuCache.put(4, 4);
        System.out.println(lfuCache.get(4));
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(4));


    }

    public static void main(String[] args) {
        int[] nums = {8, 3, 1, 3, 4, 8, 5, 8, 7, 8};
        Other other = new Other();
        int count0 = 0;
        int count5 = 0;
        int count7 = 0;
        int count9 = 0;

        for (int i = 0; i < 100000; i++) {
            int index = other.randomMaxIndex(nums);
            if (index == 0) {
                count0++;
            } else if (index == 5) {
                count5++;
            } else if (index == 7) {
                count7++;
            } else if (index == 9) {
                count9++;
            }
        }
//        System.out.println(count0 / 100000.0);
//        System.out.println(count5 / 100000.0);
//        System.out.println(count7 / 100000.0);
//        System.out.println(count9 / 100000.0);
//        System.out.println(count0 + count5 + count7 + count9);
//        ["LFUCache","put","put","get","put","get","get","put"]
//        [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4]]
        other.test();
//        LinkedHashSet<Integer> list = new LinkedHashSet<>();
//        list.add(1);
//        list.add(2);
//        System.out.println(list);
//        list.add(3);
//        System.out.println(list);
//        System.out.println(list.iterator().next());
//        System.out.println(list.iterator().next());
//        list.remove(2);
//        System.out.println(list);

    }
}
