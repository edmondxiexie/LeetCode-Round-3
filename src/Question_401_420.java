import java.util.*;

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

    /**
     * 417. Pacific Atlantic Water Flow.
     * @param matrix
     * @return
     */
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix.length == 0) {
            return res;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] p = new boolean[rows][cols];
        boolean[][] a = new boolean[rows][cols];

        boolean[][] visited;
        for (int i = 0; i < rows; i++) {
            p[i][0] = true;
            a[i][cols - 1] = true;
            visited = new boolean[rows][cols];
            DFSHelper(matrix, p, i, 0, visited);
            visited = new boolean[rows][cols];
            DFSHelper(matrix, a, i, cols - 1, visited);
        }

        for (int i = 0; i < cols; i++) {
            p[0][i] = true;
            a[rows - 1][i] = true;
            visited = new boolean[rows][cols];
            DFSHelper(matrix, p, 0, i, visited);
            visited = new boolean[rows][cols];
            DFSHelper(matrix, a, rows - 1, i, visited);
        }

        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++)
                if(a[i][j] && p[i][j])
                {
                    int[] temp = new int[2];
                    temp[0] = i;
                    temp[1] = j;
                    res.add(temp);
                }
        return res;
    }

    public void DFSHelper(int[][] matrix,boolean[][] map,int row, int col, boolean[][] visited) {

        if (visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        map[row][col] = true;
        int cur = matrix[row][col];

        if (row > 0 && matrix[row - 1][col] >= cur) {
            DFSHelper(matrix, map, row - 1, col, visited);
        }
        if (col > 0 && matrix[row][col-1] >= cur) {
            DFSHelper(matrix, map, row, col - 1, visited);
        }
        if (row < map.length-1 && matrix[row + 1][col] >= cur) {
            DFSHelper(matrix, map, row + 1, col, visited);
        }
        if (col < map[0].length-1 && matrix[row][col + 1] >= cur) {
            DFSHelper(matrix, map, row, col + 1, visited);
        }


    }










}
