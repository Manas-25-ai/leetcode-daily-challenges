/**
 * Problem: Convert a fraction (numerator/denominator) into its decimal string representation.
 *          If the decimal is repeating, enclose the repeating part in parentheses.
 *
 * Approach:
 * 1. Handle sign: If numerator and denominator have opposite signs, prefix result with "-".
 * 2. Convert both values to long to avoid overflow (e.g., Integer.MIN_VALUE edge case).
 * 3. Append the integer part of the division.
 * 4. If there's a remainder, compute the fractional part:
 *    - Use a HashMap to track remainders and their positions in the result.
 *    - If a remainder repeats, it means the decimal starts repeating from that index.
 *    - Insert "(" at the start of the repeating part and ")" at the end.
 *
 * Time Complexity:
 * - O(n): In worst case, we may process up to n unique remainders before detecting a repeat.
 * - Each digit in the fractional part is generated once until repetition is found.
 *
 * Space Complexity:
 * - O(n): HashMap stores positions of remainders to detect cycles.
 *
 * Notes:
 * - Handles negative numbers and zero correctly.
 * - Avoids floating-point arithmetic to maintain precision.
 * - Useful for formatting fractions in calculators or math apps.
 */
import java.util.*;

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        StringBuilder result = new StringBuilder();

        // Step 1: Handle sign before abs
        boolean isNegative = (numerator < 0) ^ (denominator < 0);
        if (isNegative) {
            result.append("-");
        }

        // Convert to long to prevent overflow
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // Step 2: Integer part
        result.append(num / den);
        num %= den;

        // If no fractional part
        if (num == 0) return result.toString();

        // Step 3: Fractional part
        result.append(".");
        Map<Long, Integer> map = new HashMap<>();

        while (num != 0) {
            if (map.containsKey(num)) {
                int index = map.get(num);
                result.insert(index, "(");
                result.append(")");
                break;
            }

            map.put(num, result.length());
            num *= 10;
            result.append(num / den);
            num %= den;
        }

        return result.toString();
    }
}