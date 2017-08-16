package Onsite;

import java.util.*;

/**
 * Created by Edmond on 8/10/17.
 */
public class HeapStackQueue {


    /**
     * 612. K Closest Points.
     */
//    public Point globalOrigin;

    public Point kClosest2(Point[] points, Point origin, int k) {
        PointInfo[] pointInfos = new PointInfo[points.length];
        for (int i = 0; i < pointInfos.length; i++) {
            pointInfos[i] = new PointInfo(points[i], getDistanceTwoPoints(points[i], origin));
        }
        return kClosest2Helper(pointInfos, k, 0, pointInfos.length - 1);
    }

    private Point kClosest2Helper(PointInfo[] pointInfos, int k, int start, int end) {
        if (start == end) {
            return pointInfos[start].point;
        }
        int position = partition(pointInfos, start, end);
        if (position + 1 == k) {
            return pointInfos[position].point;
        } else if (position + 1 < k) {
            return kClosest2Helper(pointInfos, k, position + 1, end);
        } else {
            return kClosest2Helper(pointInfos, k, start, position - 1);
        }

    }

    private int partition(PointInfo[] pointInfos, int start, int end) {
        int left = start;
        int right = end;
        PointInfo pivot = pointInfos[left];
        while (left < right) {
            while (left < right && pointInfos[right].compareTo(pivot) >= 0) {
                right--;
            }
            pointInfos[left] = pointInfos[right];
            while (left < right && pointInfos[left].compareTo(pivot) < 0) {
                left++;
            }
            pointInfos[right] = pointInfos[left];
        }
        pointInfos[left] = pivot;
        return left;
    }

    public static class Point {
        public int x;
        public int y;
        public Point() {
            x = 0;
            y = 0;
        }
        public Point(int a, int b) {
            x = a;
            y = b;
        }
        @Override
        public String toString() {
            return this.x + " " + this.y;
        }
    }

    private int getDistanceTwoPoints(Point point, Point origin) {
        int result = 0;
        result += (point.x - origin.x) * (point.x - origin.x);
        result += (point.y - origin.y) * (point.y - origin.y);
        return result;
    }

    public class PointInfo implements Comparable<PointInfo> {
        public Point point;
        public int distance;
        public PointInfo(Point point, int distance) {
            this.point = point;
            this.distance = distance;
        }

        @Override
        public int compareTo(PointInfo other) {
            if (this.distance != other.distance) {
                return this.distance - other.distance;
            } else if (this.point.x != other.point.x) {
                return this.point.x - other.point.x;
            } else {
                return this.point.y - other.point.y;
            }
        }
    }

    /**
     * 636. Exclusive Time of Functions
     * @param n
     * @param logs
     * @return
     */
    public int[] exclusiveTime(int n, List<String> logs) {

        int[] res = new int[n];
        Stack<Integer> stack = new Stack();
        int pre = 0;
        for (String log : logs) {
            String[] arr = log.split(":");
            int id = Integer.parseInt(arr[0]);
            String type = arr[1];
            int time = Integer.parseInt(arr[2]);
            if (type.equals("start")) {
                if (!stack.isEmpty()) {
                    res[stack.peek()] += time - pre;
                }
                stack.push(id);
                pre = time;
            } else {
                res[stack.pop()] += time - pre + 1;
                pre = time + 1;
            }
        }
        return res;
    }

    /**
     * 133. Clone Graph.
     * @param node
     * @return
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

/*

follow up1: 如果不用hashmap 该怎么做？
follow up2: 如果图很大该怎么做？

1. 2d array很明显可以在多个machine上并行计算，hashMap要keep一个hashmap比较不适合多个machine和并行计算,  当然hashMap也可以distributed, 这样问题会increase the scope of the problem, 你需要请教一下面试官能不能用这个方法来做. 一般来说我不喜欢这个解法，因为这样introduce more problems into our current one, right ? 工作中就会有越来越多的第三方的service, 最后能可能导致product 不能按时deliver.

2. 如果图有明显的聚集特点，比如，你的linked connection,很可能是学校的联系人一坨，工作过的一个公司一坨，那copy图的时候可以每一坨copy一次, 当然这都是预先知道图的特性，在工作时，问清是不是有这些特点也是对于解决问题有很好的帮助，其实无论是不是稀松或者indense， 面试官可以想知道的是你有没有这些可以根据某些特征来找到更好解决问题的办法，至于是不是稀松或者有特征，需要和面试官交流来得到一个比较好的解法。或者说有些既不是学校的也不是工作的，那你就可以不copy这些node, 问一问面试官能不能允许一定的错误率,这会让面试官觉得你很有做一个industry工程师的思维。

3. 图的COPY，一般来说用BFS, 所以如果从DFS来讲，会造成一些明显的比如进入black whole这些问题，所以只是机械地讲从BFS就用DFS，显得你在面试时有点饥不择食，在工作中就是会瞎干，让senior engineer觉得你这人干活不靠谱，DFS的话公司的机器搞坏不说，问题估计一辈子都解决不了。
In summary, 我这些是提供我的一些思路，但具体问题，你还是需要多和面试官察言观色，看他/她到底是想把你导向哪个方向再具体展开~ Good Luck~

*/
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        if (node == null) {
            return node;
        }
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        UndirectedGraphNode head = new UndirectedGraphNode(node.label);
        map.put(node, head);
        while (!queue.isEmpty()) {
            UndirectedGraphNode cur = queue.poll();
            UndirectedGraphNode clone = map.get(cur);
            for (UndirectedGraphNode neighbor : cur.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    queue.offer(neighbor);
                }
                clone.neighbors.add(map.get(neighbor));
            }
        }
        return head;
    }

    class UndirectedGraphNode {
        public int label;
        public List<UndirectedGraphNode> neighbors;UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }

    };
}
