package GOnSite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigzagIterator2 {

    public List<List<Integer>> lists;
    public Queue<Node> queue;

    /*
    * @param vecs: a list of 1d vectors
    */public ZigzagIterator2(List<List<Integer>> vecs) {
        lists = vecs;
        queue = new LinkedList<>();

        for (int i = 0; i < lists.size(); i++) {
            List<Integer> list = lists.get(i);
            if (list != null && list.size() != 0) {
                queue.offer(new Node(i, 0));
            }
        }
    }

    /*
     * @return: An integer
     */
    public int next() {
        Node cur = queue.poll();
        int result = lists.get(cur.from).get(cur.index);
        if (cur.index + 1 < lists.get(cur.from).size()) {
            queue.offer(new Node(cur.from, cur.index + 1));
        }
        return result;
    }

    /*
     * @return: True if has next
     */
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    public class Node {
        public int from;
        public int index;
        public Node(int f, int i) {
            from = f;
            index = i;
        }
    }
}

/**
 * Your ZigzagIterator2 object will be instantiated and called as such:
 * ZigzagIterator2 solution = new ZigzagIterator2(vecs);
 * while (solution.hasNext()) result.add(solution.next());
 * Output result
 */