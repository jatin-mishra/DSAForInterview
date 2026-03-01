package org.example.dp;

import java.util.Deque;
import java.util.LinkedList;

/*

Requirements:
- given nums; [] and k
- start = index 0
- one jump , max k steps forward without going out
- score is sum of num[j]
- return max score

Scale:
- nums.length ? [1, 10^5]
- nums[i] ? [-10^4, 10^4]

[ 1, -1, -2, 4, -7, 3], k=2

Solution:
    Approach 1: (deque/priority queue)
    score[i] : max score using first i+1 taking ith number
    score[i] : max(score[i-1], ..., score[i-k]) + nums[i]
    score[i+1] : clean and pick first (dq) + nums[i]
    O(n*k)
    Approach 2: dequeue with max size as k
    sorted: [maintain decreasing order (score, index)], remove from first until inside the window

    with Deque:
    Time Complexity: O(n)
    Space Complexity: O(k) (if only kept in deque)

    With PQ:
    Time Complexity: O(n log n)
    Space Complexity: O(n)


    Approach 2: Segment Tree / Fenwich tree
    TODO (Simple yet not needed for interviews)

 */
public class JumpGame6 {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] score = new int[n];
        score[0] = nums[0];
        Deque<Integer> dq = new LinkedList<>();
        dq.offerLast(0);
        for (int i = 1; i < n; i++) {
            // pop the old index
            while (dq.peekFirst() != null && dq.peekFirst() < i - k) {
                dq.pollFirst();
            }
            score[i] = score[dq.peek()] + nums[i];
            // pop the smaller value
            while (dq.peekLast() != null && score[i] >= score[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        return score[n - 1];
    }
}
