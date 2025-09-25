/**
 * Problem: Compare two version strings (e.g., "1.0.3" vs "1.0.7") and determine which one is greater.
 *
 * Approach:
 * - Split both version strings by '.' to isolate each revision number.
 * - Iterate through both arrays up to the longer length.
 * - Convert each revision to an integer (default to 0 if missing).
 * - Compare corresponding revisions:
 *     - If one is greater, return 1 or -1 accordingly.
 *     - If all revisions are equal, return 0.
 *
 * Time Complexity:
 * - O(n): where n is the maximum number of revisions in either version string.
 * - Each comparison is constant time, and we loop once through the longest array.
 *
 * Space Complexity:
 * - O(n): for storing split arrays of version strings.
 *
 * Notes:
 * - Handles unequal lengths by treating missing revisions as 0.
 * - Avoids floating-point pitfalls by using integer comparison.
 * - Useful for software versioning, semantic checks, or update logic.
 */
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int n = Math.max(v1.length, v2.length);

        for (int i = 0; i < n; i++) {
            // If index exceeds array length, treat as 0
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;

            // Compare current revision numbers
            if (num1 < num2) return -1;
            if (num1 > num2) return 1;
        }

        // All revisions matched
        return 0;
    }
}