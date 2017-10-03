package GOnSite;

import LeetCode.Question;

import java.util.*;

/**
 * Created by Edmond on 9/29/17.
 */
public class Question_9_29 {

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        boolean[][] visited = new boolean[nums1.length][nums2.length];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (nums1[o1[0]] + nums2[o1[1]]) - (nums1[o2[0]] + nums2[o2[1]]);
            }
        });
        List<int[]> result = new ArrayList<>();
        pq.offer(new int[]{0, 0});
        visited[0][0] = true;
        while (result.size() < k) {
            int[] cur = pq.poll();
            result.add(new int[]{nums1[cur[0]], nums2[cur[1]]});
            if (cur[0] + 1 < nums1.length && !visited[cur[0] + 1][cur[1]]) {
                pq.offer(new int[]{cur[0] + 1, cur[1]});
                visited[cur[0] + 1][cur[1]] = true;
            }
            if (cur[1] + 1 < nums2.length && !visited[cur[0]][cur[1] + 1]) {
                pq.offer(new int[]{cur[0], cur[1] + 1});
                visited[cur[0]][cur[1] + 1] = true;
            }
        }
        return result;
    }

    /**
     * 324. Wiggle Sort II
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        getKth(nums, len / 2);
        int left = 0;
        int right = ((len - 1) % 2 == 1) ? len - 1 : len - 2;
        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left += 2;
            right -= 2;
        }
    }

    public int getKth(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return getKth(nums, 0, nums.length - 1, k);
    }

    public int getKth(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }
        int position = partition(nums, start, end);
        if (position + 1 == k) {
            return nums[position];
        } else if (position + 1 < k) {
            return getKth(nums, position + 1, end, k);
        } else {
            return getKth(nums, start, position - 1, k);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int left = start;
        int right = end;
        int pivot = nums[start];
        while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] < pivot) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }

    // 机经
    public <T extends Comparable<T>> ListNode<T> mergeSortedList(List<ListNode<T>> lists) {
        ListNode<T> dummy = new ListNode<>();
        ListNode<T> cur = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.compareTo(o2);
            }
        });
        for (ListNode head : lists) {
            if (head != null) {
                pq.offer(head);
            }
        }
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            cur.next = node;
            cur = cur.next;
            node = node.next;
            if (node != null) {
                pq.offer(node);
            }
        }
        return dummy.next;
    }

    public static class ListNode<T extends Comparable<T>> implements Comparable<ListNode<T>> {
        public T val;
        public ListNode next;
        public ListNode(T x) {
            val = x;
        }
        public ListNode() {}

        @Override
        public int compareTo(ListNode<T> o) {
            return this.val.compareTo(o.val);
        }
    }

    public boolean hasLoop(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(n);
        return true;
    }


    /**
     * 口袋宝石OA

     链接: https://instant.1point3acres.com/thread/285003
     来源: 一亩三分地
     */
    private int[] fathers;
    private int[] sizes;

    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        int n = M.length;
        fathers = new int[n];
        sizes = new int[n];
        for (int i = 0; i < n; i++) {
            fathers[i] = i;
            sizes[i] = 1;
        }
        int circles = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) {
                    if (!isConnected(i, j)) {
                        union(i, j);
                        circles--;
                    }
                }
            }
        }
        return circles;
    }

    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            fathers[rootA] = rootB;
            sizes[rootB] += sizes[rootA];
        }
    }

    public int find(int a) {
        if (fathers[a] == a) {
            return a;
        }
        return fathers[a] = find(fathers[a]);
    }

    public boolean isConnected(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        return rootA == rootB;
    }

    /**
     * 口袋宝石OA

     链接: https://instant.1point3acres.com/thread/285003
     来源: 一亩三分地 args
     */
    static public boolean canReach(int[] p1, int[] p2) {
        Cell start = new Cell(p1[0], p1[1]);
        Cell end = new Cell(p2[0], p2[1]);
        Set<Cell> visited = new HashSet<>();
        Queue<Cell> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            Cell cur = queue.poll();
            if (cur.equals(end)) {
                return true;
            }
            if (cur.x > end.x || cur.y > end.y) {
                continue;
            }
            Cell next = new Cell(cur.x + cur.y, cur.y);
            if (!visited.contains(next)) {
                queue.add(next);
                visited.add(next);
            }
            next = new Cell(cur.x, cur.x + cur.y);
            if (!visited.contains(next)) {
                queue.add(next);
                visited.add(next);
            }
        }
        return false;
    }

    public static class Cell {
        public int x;
        public int y;
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Cell cell = (Cell) o;

            if (x != cell.x)
                return false;
            return y == cell.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    public static void main(String[] args) {
        Question_9_29 q = new Question_9_29();
        int[] nums = {1, 4, 5, 6, 8, 2, 7, 3};
        q.wiggleSort(nums);
//        System.out.println(Arrays.toString(nums));
        ListNode<Integer> n1 = new ListNode<Integer>(1);

        ListNode<Integer> n2 = new ListNode<Integer>(4);
        ListNode<Integer> n3 = new ListNode<Integer>(5);
        n2.next = n3;

        ListNode<Integer> n4 = new ListNode<Integer>(2);
        ListNode<Integer> n5 = new ListNode<Integer>(7);
        ListNode<Integer> n6 = new ListNode<Integer>(8);
        n4.next = n5;
        n5.next = n6;

        List<ListNode<Integer>> list = new ArrayList<>();
        list.add(n1);
        list.add(n2);
        list.add(n4);

        ListNode result = q.mergeSortedList(list);
//
//        while (result != null) {
//            System.out.println(result.val + " -> ");
//            result = result.next;
//        }
        int[] start = {1, 4};
        int[] end = {5, 9};
        System.out.println(canReach(start, end));

    }
}
