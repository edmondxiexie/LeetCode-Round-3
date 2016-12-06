import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question_261_280 {
    // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        StringBuilder result = new StringBuilder();
        for (String str : strs) {
            int len = str.length();
            result.append(len);
            result.append('&');
            result.append(str);
        }
        return result.toString();
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int flag = s.indexOf('&', i);
            int len = Integer.parseInt(s.substring(i, flag));
            String sub = s.substring(flag + 1, flag + 1 + len);
            result.add(sub);
            i = flag + 1 + len;
        }
        return result;
    }

    /**
     * 280. Wiggle Sort.
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int i = 1;
        while (i + 1 < nums.length) {
            int tmp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = tmp;
            i += 2;
        }
    }

    public static void main(String[] args) {
        String[] s = {"hQ/ (-OlLQ&_N","oQ=X`dm-@&;","Ti{w","-t#pDD4~Q-W%TNNZ","%W7)VH<?rJ!nHAT_z","Z+8PSgg>(?","}6H^@,_?wo6pKUFW~","D","e6y!+"};
        List<String> strs = Arrays.asList(s);

//        strs.add("abc&");
//        strs.add("sgoaks///");
//        strs.add("edmond");
        System.out.println(encode(strs));
        System.out.println(decode(encode(strs)));
    }
}
