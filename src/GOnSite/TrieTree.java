package GOnSite;

import java.util.*;

/**
 * Created by Edmond on 9/10/17.
 */
public class TrieTree {
    private TrieNode root;

    public TrieTree() {
        root = new TrieNode();
    }

    public class TrieNode {
        public boolean isWord;
        public String word;
        public Map<Character, TrieNode> children;
        public TrieNode() {
            isWord = false;
            children = new HashMap<>();
            word = "";
        }
    }

    public void add(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode());
            }
            cur = cur.children.get(c);
            if (i == word.length() - 1) {
                cur.isWord = true;
                cur.word = word;
            }
        }
    }

    public boolean search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                return false;
            }
            cur = cur.children.get(c);
        }
        return cur.isWord;
    }

    public List<String> getWords(String prefix) {
        Queue<TrieNode> queue = new LinkedList<>();
        TrieNode cur = root;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!cur.children.containsKey(c)) {
                return result;
            }
            cur = cur.children.get(c);
        }
        queue.offer(cur);
        while (!queue.isEmpty()) {
            TrieNode node = queue.poll();
            if (node.isWord) {
                result.add(node.word);
            }
            for (TrieNode next : node.children.values()) {
                queue.offer(next);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TrieTree tree = new TrieTree();
        String[] words = {"area","lead","wall","lady","ball"};
        for (String word : words) {
            tree.add(word);
        }
        System.out.println(tree.search("lead"));
        System.out.println(tree.search("lady"));
        System.out.println(tree.search("lsf"));
        System.out.println(tree.getWords("a").toString());

    }
}
