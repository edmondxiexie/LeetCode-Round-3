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
        Map<Integer, LFUNode> cacheMap;
        Map<Integer, FreqNode> freqMap;
        FreqNode head;
        FreqNode end;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            cacheMap = new HashMap<>();
            freqMap = new HashMap<>();
            head = null;
            end = null;
        }

        public void increaseFreq(LFUNode node) {
            FreqNode freqNode = freqMap.get(node.key);

        }

        public class LFUNode {
            public int key;
            public int value;
            public int freq;
            public LFUNode prev;
            public LFUNode next;
            public LFUNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        public class FreqNode {
            public int freq;
            public FreqNode prev;
            public FreqNode next;
            public LFUNode head;
            public LFUNode end;

            public FreqNode(int freq) {
                this.freq = freq;
            }

            public void remove(LFUNode node) {
                if (node.prev != null) {
                    node.prev.next = node.next;
                } else {
                    head = node.next;
                }
                if (node.next != null) {
                    node.next.prev = node.prev;
                } else {
                    end = node.prev;
                }
            }

            public void addHead(LFUNode node) {
                node.next = head;
                node.prev = null;
                if (head != null) {
                    head.prev = node;
                }
                head = node;
                if (end == null) {
                    end = head;
                }
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
        System.out.println(count0 / 100000.0);
        System.out.println(count5 / 100000.0);
        System.out.println(count7 / 100000.0);
        System.out.println(count9 / 100000.0);
        System.out.println(count0 + count5 + count7 + count9);
    }
}
