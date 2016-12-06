import com.sun.tools.javac.util.ListBuffer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 281. Zigzag Iterator.
 */
public class ZigzagIterator {
    private Queue<List> queue;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        queue = new LinkedList<>();
        if (!v1.isEmpty()) {
            queue.offer(v1);
        }
        if (!v2.isEmpty()) {
            queue.offer(v2);
        }
    }

    public int next() {
        if (!hasNext()) {
            return -1;
        }
        List<Integer> curList = queue.poll();
        int curNum = curList.get(0);
        curList.remove(0);
        if (!curList.isEmpty()) {
            queue.offer(curList);
        }
        return curNum;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
