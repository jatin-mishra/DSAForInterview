package org.example.dp;
import java.util.Arrays;

/*

Requirement:
- given target and unique nums[]
- find unique combinations (looks like permutation to me)
- infinite supply

Scale:
- nums length max size? 200
- min and max target? [1, 1000]
- min and max nums[i]? [1, 1000]


Solutioning:

Approach 1:
    r(target) =
        cnt = 0
        for all num:
            cnt += r(target - num)
        return cnt

    r(4)
        cnt = 0
        [1,2,3]
        cnt + (r(3) + r(2) + r(1)) ()
        return cnt (4 + 2 + 1) = 7 ( {1,1,1,1}, {2,1,1}, {1,2,1}, {3,1}, {1,1,2}, {2,2}, {1,3})

    r(3)
        cnt = 0
        [1,2,3]
        cnt + (r(2) + r(1) + r(0)) (2 + 1 + 1)
        return cnt = 4. ( {1,1,1}, {2,1}, {1,2}, {3})

    r(2)
        cnt = 0
        [1,2,3]
        cnt + r(1) + r(0) (0 + 1 + 1)
        return cnt -> 2 ( {1,1}, {2} )

    r(1)
        cnt = 0
        [1,2, 3]
        cnt + r(0)
        return cnt -> 1 -> ( {1} )

    r(0)
        return {} -> 1

    Time Complexity: O(target * n)
    Space Complexity: O(n)


Follow up:
- if neg elements are allowed

Changes
dp size needs to allocated at max_number + abs(neg)
zero needs to taken care of.
adding zero create inf solutions

*/



public class CombinationSum4 {
    int dp[];
    public int combinationSum4(int[] nums, int target) {
        dp = new int[target+1];
        Arrays.fill(dp, -1);
        return ans(nums, target);
    }

    private int ans(int[] nums, int target){
        if(target == 0) return 1;
        else if(dp[target] != -1) return dp[target];
        int sum = 0;
        for(int num : nums){
            if(num <= target) sum += ans(nums, target-num);
        }
        return dp[target] = sum;
    }
}
