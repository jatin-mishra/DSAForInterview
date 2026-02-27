package org.example.graph.bfs;

/*
Problem: Jump game 3

Requirements:
- given arr and start index
- from index i, you can jump to i + arr[i] or i - arr[i]
- find if you can reach any index with value 0

Scale:
- arr length ? [1, 5 * 10^4]
- arr[i] ? [0, 5 * 10^4]

Solutioning:

    Approach 1: bfs
    queue: []
    seen: []
    add start to queue and seen
    while queue is not empty:
        idx = queue.poll()
        if arr[idx] == 0 return true
        nextIdx1 = idx + arr[idx]
        nextIdx2 = idx - arr[idx]
        if nextIdx1 < n and not in seen:
            add nextIdx1 to queue and seen
        if nextIdx2 >= 0 and not in seen:
            add nextIdx2 to queue and seen
    return false


 */

import java.util.LinkedList;
import java.util.Queue;

public class JumpGame3 {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int node = q.poll();
            // check if reach zero
            if (arr[node] == 0) {
                return true;
            }
            if (arr[node] < 0) {
                continue;
            }

            // check available next steps
            if (node + arr[node] < n) {
                q.offer(node + arr[node]);
            }
            if (node - arr[node] >= 0) {
                q.offer(node - arr[node]);
            }
            // mark as visited
            arr[node] = -arr[node];
        }
        return false;
    }
}
