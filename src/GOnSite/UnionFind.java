package GOnSite;

/**
 * Created by Edmond on 9/11/17.
 */
public class UnionFind {
    int[] roots;

    public UnionFind(int n) {
        roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
    }

    public int find(int id) {
        if (id != roots[id]) {
            return roots[id] = find(roots[id]);
        }
        return id;
    }

    public void union(int id1, int id2) {
        int root1 = find(id1);
        int root2 = find(id2);
        if (root1 != root2) {
            roots[root1] = root2;
        }
    }

    public boolean isConnect(int id1, int id2) {
        int root1 = find(id1);
        int root2 = find(id2);
        return root1 == root2;
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(6);
        uf.union(1,2);
        uf.union(3,4);
        uf.union(4,5);
        System.out.println(uf.isConnect(2, 5));
        System.out.println(uf.isConnect(3, 5));
        System.out.println(uf.isConnect(4, 5));
    }
}
