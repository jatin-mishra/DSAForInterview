package org.example.dp;

/*

Problem: Jump game 4

Requirements:
Given arr
In one step you can jump from index i to index:
i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.

Scale:
- arr length ? [1, 5 * 10^4]
- arr[i] ? [- 10^8, 10^8]


Solution:
    Approach 1: BFS (TLE)
    Approach 2: BFS (keep single flag for all duplicates)
        - don't go and check every number in duplicates if added or not
        - keep single flag which tells if duplicates are added already


 */


import java.util.*;

public class JumpGame4 {

    public int minJumps(int[] arr) {

        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        int steps = 0;

        boolean[] seen = new boolean[arr.length];
        seen[0] = true;

        Map<Integer, List<Integer>> numberIndices = buildMap(arr, seen);
        Set<Integer> duplicateAdded = new HashSet<>();

        while(!q.isEmpty()){
            int sz = q.size();
            while(sz-- > 0){
                int node = q.poll();
                if(node + 1 == arr.length) return steps;
                if(node + 2 == arr.length) return steps+1;

                if(node + 1 < arr.length && !seen[node+1] && arr[node] != arr[node+1]){
                    q.add(node+1);
                    seen[node+1] = true;
                }
                if(node - 1 >= 0 && !seen[node-1] && arr[node-1] != arr[node]) {
                    q.add(node-1);
                    seen[node-1] = true;
                }
                if(duplicateAdded.contains(arr[node])) continue;
                for(int idx = numberIndices.getOrDefault(arr[node], new ArrayList<>()).size() - 1; idx >= 0; idx--){
                    int childnode = numberIndices.getOrDefault(arr[node], new ArrayList<>()).get(idx);
                    if(seen[childnode]) continue;
                    if(childnode + 1 == arr.length) return steps+1;
                    q.add(childnode);
                    seen[childnode] = true;
                    duplicateAdded.add(arr[node]);
                }
            }
            steps++;
        }

        return -1;
    }

    private Map<Integer, List<Integer>> buildMap(int[] nums, boolean[] seen){
        Map<Integer, List<Integer>> ans = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(!ans.containsKey(nums[i])) ans.put(nums[i], new ArrayList<>());
            ans.get(nums[i]).add(i);
        }
        return ans;
    }
}
