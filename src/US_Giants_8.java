import java.util.*;

/**
 *  8 - Search & Recursion.
 */
public class US_Giants_8 {

    class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
    }

    /**
     * 127. Topological Sorting.
     * @param graph
     * @return
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // <node, num of parents>
        Map<DirectedGraphNode, Integer> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbour : node.neighbors) {
                if (map.containsKey(neighbour)) {
                    map.put(neighbour, map.get(neighbour) + 1);
                } else {
                    map.put(neighbour, 1);
                }
            }
        }
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node)) {
                queue.offer(node);
                result.add(node);
            }
        }
        while (!queue.isEmpty()) {
            DirectedGraphNode curNode = queue.poll();
            for (DirectedGraphNode nextNode : curNode.neighbors) {
                map.put(nextNode, map.get(nextNode) - 1);
                if (map.get(nextNode) == 0) {
                    queue.offer(nextNode);
                    result.add(nextNode);
                }
            }
        }
        return result;
    }
}
