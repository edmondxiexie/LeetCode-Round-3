import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Question_401_420 {
    
    /**
     * 406. Queue Reconstruction by Height.
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        // people <height, k>
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return - (o1[0] - o2[0]);
                }
            }
        });
        List<int[]> result = new ArrayList<>();
        for (int[] e : people) {
            result.add(e[1], e);
        }
        return result.toArray(new int[people.length][]);
    }
}
