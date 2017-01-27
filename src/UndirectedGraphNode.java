import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edmond on 1/27/17.
 */
public class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
