package org.example.dp;

/*

Requirements:
- given n houses in a row
- given cost of every house painted in color (red, blue, green)
- no two adjacent houses should have same color
- find minimum cost to paint all houses


Scale:
total houses ? [1, 100]
color cost ? [1, 20]


Solution:
    recurrence:
    total_cost(h, r): minimum cost to paint first h houses such that last house color is red
    total_cost(h, b): minimum cost to paint first h houses such that last house color is blue
    total_cost(h, g): minimum cost to paint first h houses such that last house color is green

    total_cost(h, r): min {
        total_cost(h-1, g),
        total_cost(h-1, b)
    } + cost[h][r]

    total_cost(h, b): min {
        total_cost(h-1, g),
        total_cost(h-1, r)
    } + cost[h][b]

    total_cost(h, g): min {
        total_cost(h-1, r),
        total_cost(h-1, b)
    } + cost[h][g]

    return min{ total_cost(n, r), total_cost(n, g), total_cost(n, b)}

    Base case:
    start from first house, total cost would be same as cost

    Approach 1: (use dp for recurrence)
    dp[n][3]
    Time Complexity: O(n)
    Space Complexity: O(1) - can be done in input array, or O(n)


*/

public class PaintHouse {
    public int minCost(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n+1][3];

        int R = 0, B = 1, G = 2;
        int r = 0, g = 0, b = 0;
        for(int i = 1; i <= n; i++){
            int pr = r, pg = g, pb = b;
            r = Math.min(pb, pg) + costs[i-1][R];
            b = Math.min(pr, pg) + costs[i-1][B];
            g = Math.min(pb, pr) + costs[i-1][G];
        }

        return Math.min(r, Math.min(g, b));
    }
}
