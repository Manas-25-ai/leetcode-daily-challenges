/**
 * Problem: Given an array of integers, find the total number of elements 
 *          that appear with the highest frequency.
 *
 * Approach:
 * 1. Use a HashMap to count the frequency of each number.
 * 2. Track the maximum frequency encountered during the count.
 * 3. Iterate through the frequency map and sum the frequencies of all numbers 
 *    that match the maximum frequency.
 *
 * Time Complexity:
 * - O(n): We traverse the array once to count frequencies.
 * - O(k): We traverse the frequency map once to sum values (k = number of unique elements).
 * - Overall: O(n), since k ≤ n.
 *
 * Space Complexity:
 * - O(k): Space used by the HashMap to store frequencies.
 *
 * Notes:
 * - This solution is efficient for large arrays with repeated elements.
 * - It avoids sorting or nested loops, making it optimal for frequency analysis.
 */
import java.util.HashMap;

class Solution {
    public int maxFrequencyElements(int[] nums) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        int maxFreq = 0;

        // Step 1: Count frequencies and track max frequency
        for (int num : nums) {
            int count = freq.getOrDefault(num, 0) + 1;
            freq.put(num, count);
            maxFreq = Math.max(maxFreq, count);
        }

        // Step 2: Sum frequencies of all numbers with max frequency
        int result = 0;
        for (int count : freq.values()) {
            if (count == maxFreq) {
                result += count;
            }
        }

        return result;
    }
}