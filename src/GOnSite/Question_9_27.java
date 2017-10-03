package GOnSite;

import java.util.*;

/**
 * Created by Edmond on 9/27/17.
 */
public class Question_9_27 {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];
            if (!map.containsKey(from)) {
                map.put(from, new PriorityQueue<>());
            }
            map.get(from).offer(to);
        }
        List<String> result = new LinkedList<>();
        DFS(map, result, "JFK");
        return result;
    }

    public void DFS(Map<String, PriorityQueue<String>> map, List<String> result, String cur) {
        PriorityQueue pq = map.get(cur);
        while (pq != null && !pq.isEmpty()) {
            String next = (String) pq.poll();
            DFS(map, result, next);
        }
        result.add(0, cur);
    }

    public static void main(String[] args) {
        Question_9_27 q = new Question_9_27();
        String[][] tickets = {{"MUC","LHR"},{"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}};
        System.out.println(q.findItinerary(tickets).toString());
    }
}
