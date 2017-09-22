import java.util.Arrays;

/**
 * Created by Edmond on 8/22/17.
 */
public class Question_521_540 {
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int firstLeft = s.indexOf('(');
        if (firstLeft < 0) {
            int val = Integer.parseInt(s);
            return new TreeNode(val);
        }
        int val = Integer.parseInt(s.substring(0, firstLeft));
        TreeNode root = new TreeNode(val);
        int count = 0;
        int secondLeft = -1;
        for (int i = firstLeft; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                count--;
            }
            if (count == 0) {
                secondLeft = i + 1;
                break;
            }
        }
        root.left = str2tree(s.substring(firstLeft + 1, secondLeft - 1));
        if (secondLeft > 0 && secondLeft < s.length()) {
            root.right = str2tree(s.substring(secondLeft + 1, s.length() - 1));
        } else {
            root.right = null;
        }
        return root;
    }

    /**
     * 532. K-diff Pairs in an Array.
     * @param nums
     * @param k
     * @return
     */
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int right = 1;
        int count = 0;

        for (; left < nums.length; left++) {
            if (left > 0 && nums[left] == nums[left - 1]) {
                continue;
            }
            int rightNum = nums[left] + k;
            if (right <= left) {
                right = left + 1;
            }
            int result = search(nums, right, rightNum);
            if (result > 0) {
                count++;
                right = result + 1;
            }
        }
        return count;
    }

    public int search(int[] nums, int start, int target) {
        int left = start;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 537. Complex Number Multiplication.
     * @param a
     * @param b
     * @return
     */
    public String complexNumberMultiply(String a, String b) {
        String[] aArray = a.split("\\+");
        int aInt = Integer.parseInt(aArray[0]);
        int aIma = Integer.parseInt(aArray[1].substring(0, aArray[1].length() - 1));

        String[] bArray = b.split("\\+");
        int bInt = Integer.parseInt(bArray[0]);
        int bIma = Integer.parseInt(bArray[1].substring(0, bArray[1].length() - 1));

        int newInt = aInt * bInt - aIma * bIma;
        int newIma = aInt * bIma + aIma * bInt;
        return newInt + "+" + newIma + "i";
    }

    public static void main(String[] args) {
        Question_521_540 q = new Question_521_540();
        String s = "4(2(3)(1))(6(5))";
        String num1 = "1+1i";
        String num2 = "1+1i";
//        System.out.println(q.complexNumberMultiply(num1, num2));
        int[] nums = {1,3,1,5,4};
        System.out.println(q.findPairs(nums, 0));
    }
}
