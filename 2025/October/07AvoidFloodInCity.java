// The Core Idea:
// We need to track which lakes are full, and when it rains again on a full lake, we must have already dried it on a previous dry day.
// So we:
// - Track when each lake was last filled.
// - Keep a sorted list of dry days.
// - When a lake is about to flood, we look for the earliest dry day after it was last filled and use it to dry that lake.


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class Solution {
    public int[] avoidFlood(int[] rains) {
        int[] ans = new int[rains.length];
        Arrays.fill(ans, -1); // Initialize rainy days with -1

        Map<Integer, Integer> lakeIdToFullDay = new HashMap<>(); // Stores last day a lake became full
        TreeSet<Integer> emptyDays = new TreeSet<>(); // Stores indices of days with no rain

        for (int i = 0; i < rains.length; ++i) {
            final int lakeId = rains[i];

            if (lakeId == 0) {
                emptyDays.add(i); // Add empty day to set
            } else {
                if (lakeIdToFullDay.containsKey(lakeId)) {
                    final int fullDay = lakeIdToFullDay.get(lakeId);
                    Integer dryDay = emptyDays.higher(fullDay); // Find earliest dry day after fullDay

                    if (dryDay == null) {
                        return new int[] {}; // Flood unavoidable
                    }

                    ans[dryDay] = lakeId; // Dry the lake on this day
                    emptyDays.remove(dryDay);
                }
                lakeIdToFullDay.put(lakeId, i); // Update last full day for this lake
            }
        }

        // Fill remaining empty days with arbitrary drying operations (e.g., dry lake 1)
        for (final int emptyDay : emptyDays) {
            ans[emptyDay] = 1;
        }

        return ans;
    }
}