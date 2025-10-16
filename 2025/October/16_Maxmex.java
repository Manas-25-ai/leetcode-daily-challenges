/****************************************************** JAVA **************************************************************/
//Approach - CHECKING REMAINDERS THROUGH HASHMAP AND UPGRADING MEX
//T.C : O(n)
//S.C : O(n)

import java.util.*;
class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // Count occurrences of each normalized remainder
        for (int n : nums) {
            int remainder = ((n % value) + value) % value; // ensures non-negative remainder
            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }

        int MEX = 0;

        // Increment MEX while we have available remainders
        while (map.getOrDefault(MEX % value, 0) > 0) {
            map.put(MEX % value, map.get(MEX % value) - 1);
            MEX++;
        }

        return MEX;
    }
}