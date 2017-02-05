import java.util.*;

/**
 * Created by Edmond on 11/30/16.
 */
public class Question_120_140 {

    /**
     * 124. Binary Tree Maximum Path Sum.
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        HelperMaxPathSum(root);
        return maxPathSum;
    }

    private int HelperMaxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(HelperMaxPathSum(root.left), 0);
        int right = Math.max(HelperMaxPathSum(root.right), 0);
        maxPathSum = Math.max(maxPathSum, left + right + root.val);
        return root.val + Math.max(left, right);
    }

    /**
     * 126. Word Ladder II.
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>();
        for (String word : wordList) {
            wordSet.add(word);
        }
        List<List<String>> result = new ArrayList<>();
        if (!wordSet.contains(endWord)) {
            return result;
        }
        wordSet.add(beginWord);
        Map<String, Integer> distance = new HashMap<>();
        BFS(endWord, beginWord, wordSet, distance);
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        DFS(beginWord, endWord, wordSet, distance, path, result);
        return result;
    }

    private void BFS(String beginWord, String endWord, Set<String> wordSet, Map<String, Integer> distance) {
        Queue<String> queue = new LinkedList<>();
        int level = 1;
        queue.offer(beginWord);
        distance.put(beginWord, level);
        boolean found = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                String top = queue.poll();
                for (String next : getNextStr(top, wordSet)) {
                    if (distance.containsKey(next)) {
                        continue;
                    }
                    if (next.equals(endWord)) {
                        found = true;
                    }
                    queue.offer(next);
                    distance.put(next, level);
                }
            }
            if (found) {
                break;
            }
        }
    }

    private void DFS(String beginWord, String endWord, Set<String> wordSet, Map<String, Integer> distance, List<String> path, List<List<String>> result) {
        if (!distance.containsKey(beginWord)) {
            return;
        }
        if (beginWord.equals(endWord)) {
            result.add(new ArrayList(path));
        }
        for (String next : getNextStr(beginWord, wordSet)) {
            if (distance.containsKey(next) && distance.get(next) == distance.get(beginWord) - 1) {
                path.add(next);
                DFS(next, endWord, wordSet, distance, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    private List<String> getNextStr(String top, Set<String> wordSet) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < top.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch == top.charAt(i)) {
                    continue;
                }
                String next = replace(top, i, ch);
                if (wordSet.contains(next)) {
                    result.add(next);
                }
            }
        }
        return result;
    }

    private String replace(String top, int i, char ch) {
        char[] charArray = top.toCharArray();
        charArray[i] = ch;
        return new String(charArray);
    }

    private int maxPathSum = Integer.MIN_VALUE;

    /**
     * 128. Longest Consecutive Sequence.
     * @param nums
     * @return
     */
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
     * 133. Clone Graph.
     * @param node
     * @return
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }
        if (visited.containsKey(node.label)) {
            return visited.get(node.label);
        }

        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        visited.put(clone.label, clone);

        for (UndirectedGraphNode neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }

        return clone;
    }

    private HashMap<Integer, UndirectedGraphNode> visited = new HashMap<>();

    /**
     * 135. Candy.
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int[] res = new int[ratings.length];
        Arrays.fill(res, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                res[i] = res[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                res[i] = Math.max(res[i], res[i + 1] + 1);
            }
        }
        int sum = 0;
        for (int n : res) {
            sum += n;
        }
        return sum;
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
        String beginWord = "hit";
        String endWord = "cog";
        String[] strs = {"hot","dot","dog","lot","log","cog"};
        List<String> wordList = new ArrayList<>();
        for (String s : strs) {
            wordList.add(s);
        }
        Question_120_140 q = new Question_120_140();
        q.findLadders(beginWord, endWord, wordList);

    }

}
