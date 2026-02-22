package org.example.dp;

/*

Requirement:
- given n houses which can be colored with any of k  colors
- color all houses in such a way that no adjacent house have same color
- cost of coloring a house h with color k is given at cost[h][k]

- inputs: cost[][]
- output: min cost

Scale:
total houses <= 100
total colors <= 20

Solutions:
    Recurrence relation:
    total_cost(n, k):
        total cost to color first n houses following rules
        where last house color = k

    total_cost(n, k): min {
        all total_cost(n-1, colors - {k})
    } + cost[n][k]

    Approach 1: (Use DP with recurrence relation)
    we know we always need min total_cost
    except for one color, we need second min

    min = min of all cost of house 0, min_color = k1
    secMin = min of all costs except the one considered as min, smin_color = k2

    for all next house:
        curMin, curSecMin
        for all color k:
            if color != k1: ans = min + cost[house][color]
            else ans = secondMin + cost[house][color]
            maintain curMin and curSecMin
        replace min and secMin
    return min

    Time Complexit: O(n * k)
    Space Complexity: O(1)
*/

public class PaintHouse2 {
    public int minCostII(int[][] costs) {
        int fc=Integer.MAX_VALUE, fclr=-1;
        int sc=Integer.MAX_VALUE, sclr=-1;

        for(int i = 0; i < costs[0].length; i++){
            if(costs[0][i] < fc){
                sc = fc;
                sclr = fclr;
                fc = costs[0][i];
                fclr = i;
            }else if(costs[0][i] < sc){
                sc = costs[0][i];
                sclr = i;
            }
        }


        for(int i = 1; i < costs.length; i++){
            int pfc = fc, pfclr = fclr, psc = sc, psclr = sclr;
            fc=Integer.MAX_VALUE;
            fclr=-1;
            sc=Integer.MAX_VALUE;
            sclr=-1;
            for(int j = 0; j < costs[i].length; j++){
                int ccost = Integer.MAX_VALUE;
                if(j == pfclr){
                    ccost = costs[i][j] + psc;
                }else{
                    ccost = costs[i][j] + pfc;
                }
                if(ccost < fc){
                    sc = fc;
                    sclr = fclr;
                    fc = ccost;
                    fclr = j;
                }else if(ccost < sc){
                    sc = ccost;
                    sclr = j;
                }
            }
        }
        return fc;
    }
}
