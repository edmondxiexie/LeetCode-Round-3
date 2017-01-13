/**
 * Created by Edmond on 12/23/16.
 */
public class Question_301_320 {
    public class NumArray {

        private int[] sums;
        private int[] nums;

        public NumArray(int[] nums) {
            sums = new int[nums.length];
            this.nums = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                this.nums[i] = nums[i];
                if (i == 0) {
                    sums[i] = nums[i];
                } else {
                    sums[i] = sums[i - 1] + nums[i];
                }
            }
        }

        public int sumRange(int i, int j) {
            return sums[j] - sums[i] + nums[i];
        }
    }

    /**
     * 306. Additive Number.
     * @param num
     * @return
     */
    public boolean isAdditiveNumber(String num) {
        for (int i = 1; i <= num.length() / 2 + 1; i++) {
            for (int j = i + 1; j <= num.length(); j++) {
                String str1 = num.substring(0, i);
                String str2 = num.substring(i, j);
                String str3 = strAdd(str1, str2);
                if (num.substring(j).startsWith(str3)) {
                    if (DFSIsAdditiveNumber(i, j, num)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean DFSIsAdditiveNumber(int i, int j, String num) {
        if (num.length() == j) {
            return true;
        }
        String str1 = num.substring(0, i);
        String str2 = num.substring(i, j);
        if (str1.startsWith("0") && !str1.equals("0")
                || str2.startsWith("0") && !str2.equals("0")) {
            return false;
        }

        String str3 = strAdd(str1, str2);
        if (num.substring(j).startsWith(str3)) {
            num = num.substring(i);
            int len = i;
            i = j - len;
            j = j + str3.length() - len;
            if (DFSIsAdditiveNumber(i, j, num)) {
                return true;
            }
        }
        return false;
    }

    public String strAdd(String n1, String n2) {
        StringBuilder res = new StringBuilder();
        int i1 = n1.length() - 1;
        int i2 = n2.length() - 1;
        int carry = 0;
        while (i1 >= 0 || i2 >= 0 || carry > 0) {
            if (i1 < 0 && i2 < 0) {
                res.append(carry % 10);
                carry = carry / 10;
            } else if (i1 < 0) {
                int num = n2.charAt(i2) - '0' + carry;
                res.append(num % 10);
                carry = num / 10;
                i2--;
            } else if (i2 < 0) {
                int num = n1.charAt(i1) - '0' + carry;
                res.append(num % 10);
                carry = num / 10;
                i1--;
            } else {
                int num = n1.charAt(i1) - '0' + n2.charAt(i2) - '0' + carry;
                res.append(num % 10);
                carry = num / 10;
                i1--;
                i2--;
            }
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        Question_301_320 c = new Question_301_320();
        System.out.println(c.isAdditiveNumber("198019823962"));
    }
}
