package org.example.dp;


/*

Requirement:
- find min number if Jumps

- input:
nums: []


Scale:
nums.length ? [1, 10^4]
nums[i] ? [0, 1000]

Solutioning:

    Approach 1: Dijkstra
    distance[]
    pq: []
    implement dijsktra

    Approach 2: boundary wise
    step = 0, maxLimit = 0

    step = 0
    for i from 0 to n:
        updatedMaxLimit = maxLimit
        while i <= maxLimit:
            updaredMaxLimit = max(uml, i + nums[i])
        if(i == n-1) return step
        step++
        maxLimit = updatedMaxLimit

 */


public class jumpGame2 {
    public int jump(int[] nums) {
        int steps = 0;
        int maxLimit = 0;
        int i = 0;
        while(i < nums.length){
            int updatedMaxLimit = maxLimit;
            while(i < nums.length && i <= maxLimit){
                updatedMaxLimit = Math.max(updatedMaxLimit, i + nums[i]);
                i++;
            }
            if(i == nums.length){
                return steps;
            }else if(updatedMaxLimit == maxLimit) return -1;
            steps++;
            maxLimit = updatedMaxLimit;
        }
        return steps;
    }
}
