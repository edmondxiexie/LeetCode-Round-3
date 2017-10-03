package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Edmond on 9/19/17.
 */
public class Question {
    // 第一题给一个字符串，返回最长的完全按照字典序顺序排列的前缀，比如“abcdeab"，按照字典序排列的前缀就是"abcde”，
    // 因为到“abcdea“最后一个和e就违反字典序了。
    public String dictPrefix(String word) {
        if (word == null || word.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char target = word.charAt(0);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != target) {
                break;
            }
            sb.append(target);
            target++;

        }
        return sb.toString();
    }

    public int weightedRandomPick(int[] nums) {
        int sum = 0;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (Math.random() * sum < nums[i]) {
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        Question q = new Question();
        String word = "";
//        System.out.println(q.dictPrefix(word));
        int[] nums = {0, 0, 0, 0};
        int[] record = new int[4];
        for (int i = 0; i < 10000; i++) {
            int index = q.weightedRandomPick(nums);
            if (index >= 0) {
                record[index]++;
            }
        }
        System.out.println(Arrays.toString(record));
    }

    public static void output(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        for (TreeNode node : root.next) {
            output(node);
        }
    }

    public int index = 0;

    public TreeNode buildTree(String str) {
        String[] strs = str.split(",");
        TreeNode dummy = new TreeNode(0);
        dummy.next = buildTree(strs);
        return dummy.next.get(0);
    }

    public List<TreeNode> buildTree(String[] strs) {
        // recursion
        List<TreeNode> list = new ArrayList<>();
        String first = strs[index].substring(1);
        list.add(new TreeNode(Integer.parseInt(first)));
        index++;
        boolean isEnd = false;
        while (index < strs.length) {
            if (strs[index].startsWith("(")) {
                list.get(list.size() - 1).next = buildTree(strs);
            } else {
                String tmp = strs[index];
                if (tmp.endsWith(")")) {
                    while (tmp.endsWith(")")) {
                        tmp = tmp.substring(0, tmp.length() - 1);
                    }
                    isEnd = true;
                }
                list.add(new TreeNode(Integer.parseInt(tmp)));
            }
            if (isEnd) {
                break;
            }
            index++;
        }
        return list;
    }

    public class TreeNode {
        public int val;
        public List<TreeNode> next;
        public TreeNode(int val) {
            this.val = val;
            next = new ArrayList<>();
        }
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append(val);
            if (next != null) {
                result.append('[');
                for (TreeNode child : next) {
                    result.append(child.toString());
                    result.append(',');
                }
                //result.deleteCharAt(result.length() - 1);
                result.append(']');
            }
            return result.toString();
        }
    }
}
