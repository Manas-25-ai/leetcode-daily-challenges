/**
 * Problem: Water Bottles II (LeetCode)
 * ------------------------------------
 * You start with `numBottles` full bottles. You can exchange `x` empty bottles
 * for 1 full bottle, but after each exchange, the number of bottles required
 * for the next exchange increases by 1.
 *
 * Goal: Find the total number of bottles you can drink.
 *
 * Logic:
 * - Initially drink all full bottles.
 * - While you have enough bottles to exchange:
 *     • Spend (x - 1) bottles effectively after each exchange (since you gain 1 back and drink it).
 *     • Increment x (next exchange becomes harder).
 *     • Increment total count of bottles drunk.
 *
 * Time Complexity: O(numBottles)
 * Space Complexity: O(1)
 */

class Solution {
    public int maxBottlesDrunk(int numBottles, int x) {
        int ans = numBottles;
        while (numBottles >= x) {
            numBottles -= x - 1;  // spend (x - 1) bottles effectively
            x++;                   // next exchange needs more bottles
            ans++;                 // one more bottle drunk
        }
        return ans;
    }
}
