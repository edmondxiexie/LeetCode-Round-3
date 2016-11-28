import java.util.*;

/**
 * Created by Edmond on 11/19/16.
 */
public class Question_441_460 {
    /**
     * 451. Sort Characters By Frequency
     * @param s string
     * @return sorted string
     */
    public String frequencySort(String s) {
        char[] chars = s.toCharArray();

        // key is character, value is count
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : chars) {
            if (countMap.containsKey(c)) {
                countMap.put(c, countMap.get(c) + 1);
            } else {
                countMap.put(c, 1);
            }
        }

        List<Character>[] listArray = new List[s.length() + 1];
        for (char c : countMap.keySet()) {
            int freq = countMap.get(c);
            if (listArray[freq] == null) {
                listArray[freq] = new ArrayList<>();
            }
            listArray[freq].add(c);
        }

        StringBuilder result = new StringBuilder();
        for (int i = listArray.length - 1; i > 0; i--) {
            if (listArray[i] != null) {
                for (char c : listArray[i]) {
                    for (int j = 0; j < i; j++) {
                        result.append(c);
                    }
                }
            }
        }

        return result.toString();






//        // index is character, value is count
//        int[] count = new int[128];
//        int max = 0;
//        for (char e : chars) {
//            count[e]++;
//            max = Math.max(max, count[e]);
//        }
//
//        Map<Integer, List<Character>> freq = new HashMap<>();
//
//        for (int i = 0; i < count.length; i++) {
//            char letter = (char)i;
//            if (count[i] > 0) {
//                if (freq.get(count[i]) == null) {
//                    List<Character> list = new ArrayList<>();
//                    list.add(letter);
//                    freq.put(count[i], list);
//                } else {
//                    List<Character> list = freq.get(count[i]);
//                    list.add(letter);
//                    freq.put(count[i], list);
//                }
//            }
//        }
//
//        Arrays.sort(count);
//        int last = 0;
//
//        String result = "";
//        for (int i = 0; i < count.length; i++) {
//            if (count[i] == last) {
//                last = count[i];
//                continue;
//            }
//            List<Character> cur = freq.get(count[i]);
//            for (char c : cur) {
//                for (int l = 0; l < count[i]; l++) {
//                    result = c + result;
//                }
//            }
//            last = count[i];
//        }
//        return result;
    }
}