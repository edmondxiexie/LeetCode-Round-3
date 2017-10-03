package GOnSite;

import java.util.*;

/**
 * Created by Edmond on 10/1/17.
 */
public class Question_10_1 {
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        int n = org.length;
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        // build graph
        for (int[] seq : seqs) {
            if (seq.length == 1) {
                if (!graph.containsKey(seq[0])) {
                    graph.put(seq[0], new HashSet<>());
                    inDegree.put(seq[0], 0);
                }
            } else {
                for (int i = 0; i < seq.length - 1; i++) {
                    int from = seq[i];
                    int to = seq[i + 1];
                    if (from > n || to > n) {
                        return false;
                    }
                    if (!graph.containsKey(from)) {
                        graph.put(from, new HashSet<>());
                        inDegree.put(from, 0);
                    }
                    if (!graph.containsKey(to)) {
                        graph.put(to, new HashSet<>());
                        inDegree.put(to, 0);
                    }
                    if (!graph.get(from).contains(to)) {
                        graph.get(from).add(to);
                        inDegree.put(to, inDegree.get(to) + 1);
                    }
                }
            }
        }

        // queue
        int index = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                queue.offer(key);
            }
        }
        while (!queue.isEmpty()) {
            if (queue.size() > 1) {
                return false;
            }
            int cur = queue.poll();
            if (org[index] != cur) {
                return false;
            }
            index++;
            for (int next : graph.get(cur)) {
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }

        return index == n;
    }


}
