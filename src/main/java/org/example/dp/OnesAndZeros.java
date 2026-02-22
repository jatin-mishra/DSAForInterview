package org.example.dp;

/*

Problem: https://leetcode.com/problems/ones-and-zeroes/

Requirement:
- given binary string[]
- m (number of target zeros) and n (number of ones)
- find largest subset (not subarray) such that zeroes <= m and ones <= 1

Scale:
binary string array max size? <= 600
binary string max size? <= 100
max m and n? [1,100]


Solutioning:

Approach 1:
    recurrence relation:
    r(i, m, n): max size of subset using first i strings and having max m zeroes and n ones
        either take ith string or not?

        max of:
            if we take: r(i-1, m - zeroes(str[i]), n - ones(str[i])) + 1
            else r(i-1, m, n)

    base case:
    r(0, x, y): 0
    if there are zero strings, no number of zeros and ones can be made.

    Time Complexity: O(array_size * m * n) ~ 600 * 100 * 100 (dp size) + 600 * 100 (processing zeros and ones for all)
    Space Complexity: 6 * 10^6 but as i can be avoided, we can make it to 10^4

*/



class OnesAndZeros {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[2][m+1][n+1];
        int[][] strCounts = prepareZeroAndOneCounts(strs);
        for(int x = 1; x <= strs.length; x++){
            for(int z = 0; z <= m; z++){
                for(int o = 0; o <= n; o++){
                    int curx = getCur(x);
                    dp[curx][z][o] = dp[1-curx][z][o];
                    if(strCounts[x-1][0] <= z && strCounts[x-1][1] <= o){
                        dp[curx][z][o] = Math.max(dp[curx][z][o], dp[1-curx][z - strCounts[x-1][0]][o - strCounts[x-1][1]] + 1);
                    }
                }
            }
        }

        return dp[getCur(strs.length)][m][n];
    }

    private int[][] prepareZeroAndOneCounts(String[] strs){
        int[][] zAndo = new int[strs.length][2];
        for(int i = 0; i < strs.length; i++){
            int[] count = countZAndO(strs[i]);
            zAndo[i][0] = count[0];
            zAndo[i][1] = count[1];
        }
        return zAndo;
    }


    private int[] countZAndO(String str){
        int z = 0;
        int o = 0;
        for(char c : str.toCharArray()){
            z += (1 - (c - '0'));
            o += (c - '0');
        }
        return new int[]{z, o};
    }

    private int getCur(int x){
        if((x&1) == 1) return 1;
        return 0;
    }

    private int getPrev(int x){
        return 1 - getCur(x);
    }
}

