package org.example.dp;

/*

Requirement:
- given amount and []coins
- find ways to build the amount by coins
- Assumption:
    - infinite number of coins of each type


Scale:
- size of amount? <= 5000
- size of coins array? <= 300
- biggest and smallest coin <= 500


Solution:

    Recurrence relation:
        ways(am, i): total number of ways of building total am amount and using first i coins
        either i take this coin or don't.
        ways(am, i) =
            if u take this coin this time then = ways(am - coins[i], i)
            else if u don't = ways(am, i-1)

    Approach 1: (knapsack dp with given recurrence)
    Base case:
        ways(am=0, i) : 1 (only way by picking no coin, we make amount = 0)
        ways(am, 0) : 0

    Time Complexity: O(am * size_of_coins) ~ 5000 * 300 ~ 1.5 * 10^6
    Space Complexity: O(am * size_of_coins) but can be optimized to O(am)
        - given than for coin, state is needed for c-1 and c, only two, hence only these two or even
        one can be maintained.

    can be down with bottom up and top down too.

*/


public class CoinChange2 {
    public int change(int amount, int[] coins) {

        int[][] dp = new int[amount+1][coins.length+1];
        for(int i = 0; i <= coins.length; i++) dp[0][i] = 1;

        for(int am = 1; am <= amount; am++){
            for(int c = 1; c <= coins.length; c++){
                int coin = coins[c-1];
                dp[am][c] = dp[am][c-1]; // not taking it
                if(am >= coin){
                    dp[am][c] += dp[am - coin][c];
                }
            }
        }
        return dp[amount][coins.length];
    }
}
