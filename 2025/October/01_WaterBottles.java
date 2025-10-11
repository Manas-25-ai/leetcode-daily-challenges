//  EXAMPLE -----Input: numBottles = 15, numExchange = 4
// Output: 19
// Explanation: You can exchange 4 empty bottles to get 1 full water bottle. 
// Number of water bottles you can drink: 15 + 3 + 1 = 19.

// ------------------------JAVA SOLUTION-------------------------------


class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int count = numBottles;
        int empty = numBottles;
        while(empty >= numExchange){
            int exchange = empty / numExchange;
            count += exchange;
            empty %= numExchange;
            empty += exchange;
        }
        return count;  
    }
}