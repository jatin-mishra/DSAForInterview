package org.example.dp;


/*
Problem: https://leetcode.com/problems/perfect-squares/description/?envType=study-plan-v2&envId=dynamic-programming

Code Structure to complete:
class Solution {
    public int numSquares(int n) {
    }
}

-------------------

Requirement:
- given n
- find least number of square numbers that sum to n


Scale:
- TODO


Solution and Base cases:
Approach 1:
    num(n): minimium number of squares need to sum upto n
    num(n)
        if n is perfect square:
            return 1

        int cnt = n
        for tn : 1 to root(n):
            cnt = min(cnt, num(n - tn*tn) + 1)

        return cnt

    base case:
        precalculate all suare number sets and just check

    Time Complexity: O(n*root(n)) ~ 10^6
    Space Complexity: O(n) ~ 10^4 * 8 bytes


Approach 2:
    divided(n, c): can n be devided into c squares

    main(n)
        for c from 1 to n:
            if(divided(n, c)) return c

    divided(n, c):
        if c == 1 and n is square: return true
        else if c == 1: return false

        for i from 1 to root(n)
            if(divided(n - i*i, c-1)) return true

        return false

    Base case:
        if c == 1: then n has to be square else not possible

    Time Complexity: O(n^h/2)
    Space Complexity: O(n) <- we keep squares (can make root n by keeping only squares)

Approach 3:
    bfs(n):
        >> either keep (n, cnt) or do size wise bfs
        >> going with size bfs

    pre calculation:
        prepare squares

    algo:
        q: (n) <- set
        cnt = 1

        while !q.isEmpty
            sz = q.size
            for sz--:
                num = q.pop
                if sq[num]:
                    return cnt+1
                for i*i < num:
                    q.add(num - i*i)

        return n

    base case: NA
    Time Complexity: O(n * root(n))
        1, root(n), root(n) * root(root(n)), ....
        h -> root(n)^h

        taking root(n) as x
        x + x^2 + x^3 + x^4 ... + x^h ~ n^h/2
        n * root(n) because max h = 3
    space complexity:
        last index root(n) ^ h ~ n * root(n) -> but using set will keep it max n


Final Call: Implementing Approach 2
 */


public class PerfectSquare {

    boolean[] sq = new boolean[10001];

    public PerfectSquare(){
        for(int i = 1; i * i < 10001; i++){
            sq[i*i] = true;
        }
    }

    public int numSquares(int n) {
        for(int c = 1; c <= n; c++){
            if(divided(n, c)) return c;
        }
        return n;
    }

    private boolean divided(int n, int c){
        if(c == 1) return sq[n];

        for(int i = 1; i * i <= n; i++){
            if(divided(n - i*i, c-1)) return true;
        }
        return false;
    }
}
