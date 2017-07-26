import java.util.*;

/**
 * Created by Edmond on 3/10/17.
 */
public class BuddyAI {
    public static class CheckNode {
        public int val;
        public List<CheckNode> nextList;
        public CheckNode(int val) {
            this.val = val;
            this.nextList = new ArrayList<>();
        }
    }

    public final int SUCCESS = 1;
    public final int FAILURE = 0;

    public boolean checkProgram(CheckNode node) {
        if (node == null) {
            return true;
        }
        Queue<CheckNode> queue = new LinkedList<>();
        Set<CheckNode> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        boolean foundSeccess = false;
        while (!queue.isEmpty()) {
            CheckNode tmp = queue.poll();
            for (CheckNode next : tmp.nextList) {
                if (next.val == FAILURE) {
                    return false;
                }
                if (next.val == SUCCESS) {
                    foundSeccess = true;
                }
                if (!set.contains(next)) {
                    queue.offer(next);
                }

            }
        }
        return foundSeccess;
    }

    public static void main(String[] args) {
        BuddyAI ba = new BuddyAI();
        CheckNode n0 = new CheckNode(0);
        CheckNode n1 = new CheckNode(1);
        CheckNode n2 = new CheckNode(2);
        CheckNode n3 = new CheckNode(3);
        CheckNode n4 = new CheckNode(4);
        CheckNode n5 = new CheckNode(5);
        CheckNode n6 = new CheckNode(5);
        n2.nextList.add(n3);
        n2.nextList.add(n4);
        n3.nextList.add(n5);
        n4.nextList.add(n5);
        n5.nextList.add(n1);
        n5.nextList.add(n2);
        n3.nextList.add(n0);
        n3.nextList.add(n6);
        System.out.print(ba.checkProgram(n2));

    }
}
