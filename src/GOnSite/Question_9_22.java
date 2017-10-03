package GOnSite;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Edmond on 9/22/17.
 */
public class Question_9_22 {
    /**
     * 505. The Maze II
     *  Dijkstra algorithm
     */
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] dists = new int[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                dists[i][j] = Integer.MAX_VALUE;
            }
        }
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.dist - o2.dist;
            }
        });
        pq.offer(new Point(start[0], start[1], 0));
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            if (dists[p.x][p.y] <= p.dist) {
                continue;
            }
            dists[p.x][p.y] = p.dist;
            for (int i = 0; i < 4; i++) {
                int nx = p.x;
                int ny = p.y;
                int ndist = p.dist;
                while (nx >= 0 && nx < maze.length && ny >= 0 && ny < maze[0].length && maze[nx][ny] == 0) {
                    nx += dirs[i][0];
                    ny += dirs[i][1];
                    ndist++;
                }
                nx -= dirs[i][0];
                ny -= dirs[i][1];
                ndist--;
                pq.offer(new Point(nx, ny, ndist));
            }
        }
        return dists[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dists[destination[0]][destination[1]];
    }

    public class Point {
        public int x;
        public int y;
        public int dist;
        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
