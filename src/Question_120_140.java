import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edmond on 11/30/16.
 */
public class Question_120_140 {
    public static int longestConsecutive(int[] nums) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                int left = 0;
                int right = 0;
                if (map.containsKey(num - 1)) {
                    left = map.get(num - 1);
                }
                if (map.containsKey(num + 1)) {
                    right = map.get(num + 1);
                }
                int total = left + right + 1;
                max = Math.max(max, total);
                map.put(num, total);
                map.put(num - left, total);
                map.put(num + right, total);
            }
        }
        return max;
    }

    /**
     * 138. Copy List with Random Pointer.
     * @param head
     * @return
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode newNode = dummy;
        RandomListNode cur = head;
        while (cur != null) {
            newNode.next = new RandomListNode(cur.label);
            newNode = newNode.next;
            map.put(cur, newNode);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                map.get(cur).random = map.get(cur.random);
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {

    }

}
