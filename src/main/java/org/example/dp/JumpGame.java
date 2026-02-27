package org.example.dp;


/*


Problem: Jump game

Requirements:
Given nums, where each index i means you can jump at most nums[i] steps forward from i
Find if you can reach the last index starting from the first index

Scale:
nums length ? [1, 10^4]


Solution's:

    Approach 1: (bfs) / (bfs)
    start bfs and try out all next index from 1 to nums[i]
    if you find last index then return true
    else return false

    Approach 2: Approach 1 + dp (storing visited/seen)


    Approach 3:
    maxJump = 0
    for i = 0 to n:
        if i > maxJump; return false
        maxJump = max(maxJump, i + nums[i])
    return true
 */

public class JumpGame {
    public boolean canJump(int[] nums) {
        int mxreach = 0;
        for(int i = 0; i < nums.length; i++){
            if(i > mxreach) return false;
            mxreach = Math.max(mxreach, i + nums[i]);
        }
        return true;
    }
}
