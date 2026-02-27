package org.example.graph.topological;

/*


Problem: Jump game 5

Requirements:
input: nums: [], d: int
can choose any one index to start
from index i, you can jump to index j if:
- i + d >= j >= i - d
- and nums[i] > nums[j] and i != j and all between i and j are also less than nums[i]

return max number of indices you can visit

Scale:
- nums length ? [1, 1000]
- nums[i] ? [1, 10^5]
- d ? [1, nums.length]


Solution:
    Approach 1: Brute force
    - start from every number, do the bfs and mark reachable nodes
    - in the end take max of all

    Approach 2: bfs in topological order
    prepare graph (directed)
    start following bfs with size pattern
    start with 0 out degrees and call one and keep removing levels
    max levels is the answer
 */


import java.util.*;

public class JumpGame5 {
    public int maxJumps(int[] arr, int d) {
        int[] in = new int[arr.length];

        Map<Integer, List<Integer>> g = prepareGraph(arr, d, in);
        int step = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < arr.length; i++){
            if(in[i] == 0) q.add(i);
        }

        if(q.isEmpty()) return 1;

        while(!q.isEmpty()){
            int sz = q.size();
            step++;
            while(sz-- > 0){
                int node = q.poll();
                for(int childnode : g.getOrDefault(node, new ArrayList<>())){
                    in[childnode]--;
                    if(in[childnode] == 0){
                        q.add(childnode);
                    }
                }
            }
        }

        return step;
    }

    private Map<Integer, List<Integer>> prepareGraph(int[] arr, int  d, int[] in){

        Map<Integer, List<Integer>> ans = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            int x = 1;
            while(x <= d && i + x < arr.length && arr[i] > arr[i+x]){
                ans.putIfAbsent(i+x, new ArrayList<>());
                ans.get(i+x).add(i);
                in[i]++;
                x++;
            }

            x = 1;
            while(x <= d && i - x >= 0 && arr[i] > arr[i-x]){
                ans.putIfAbsent(i-x, new ArrayList<>());
                ans.get(i-x).add(i);
                in[i]++;
                x++;
            }
        }

        return ans;
    }
}
