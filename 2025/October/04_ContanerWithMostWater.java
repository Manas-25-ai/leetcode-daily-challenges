/**
 * Problem: Container With Most Water (LeetCode)
 * ---------------------------------------------
 * Given an array of heights, each representing a vertical line at that index,
 * find two lines that together form a container holding the maximum amount of water.
 *
 * Intuition:
 * - The water trapped between two lines is limited by the shorter line.
 * - To maximize area, we use a two-pointer approach:
 *     • Start from both ends.
 *     • Calculate current area.
 *     • Move the pointer pointing to the shorter line inward
 *       (since moving the taller one can never increase area).
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int curArea = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, curArea);

            // Move the pointer at the shorter line inward
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}
