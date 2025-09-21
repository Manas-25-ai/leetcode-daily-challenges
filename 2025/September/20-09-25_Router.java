//  Leetcode 3508. Implement Router............Difficulty : Medium 
/*
 * 2025-09-20 LeetCode challenge solution
 * Router class: manages network packets with memory limit
 *
 * Functions:
 * - addPacket(src, dst, t): adds a packet if it does not exist
 * - forwardPacket(): removes the oldest packet
 * - getCount(dst, t1, t2): counts packets to destination within [t1,t2]
 *
 * Complexity:
 * Time:
 *   addPacket: O(1) avg, eviction O(1)
 *   forwardPacket: O(1)
 *   getCount: O(log n) per query
 * Space: O(N) for storing packets
 */

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Router {
    private int limit;                          // max packets allowed
    private Map<String, int[]> packets;         // key -> {src, dst, time}
    private Map<Integer, ArrayList<Integer>> times; // dst -> timestamps
    private Map<Integer, Integer> ptr;          // dst -> pointer of first valid
    private Queue<String> q;                    // FIFO for eviction

    public Router(int memoryLimit) {
        limit = memoryLimit;
        packets = new HashMap<>();
        times = new HashMap<>();
        ptr = new HashMap<>();
        q = new LinkedList<>();
    }
    
    public boolean addPacket(int src, int dst, int t) {
        String key = src + "#" + dst + "#" + t;
        if (packets.containsKey(key)) return false;
        
        if (packets.size() >= limit) {
            forwardPacket();
        }
        
        packets.put(key, new int[]{src, dst, t});
        q.offer(key);
        
        times.putIfAbsent(dst, new ArrayList<>());
        times.get(dst).add(t);
        
        ptr.putIfAbsent(dst, 0);
        return true;
    }
    
    public int[] forwardPacket() {
        if (packets.isEmpty()) return new int[0];
        
        String key = q.poll();
        int[] pkt = packets.remove(key);
        
        int dst = pkt[1];
        ptr.put(dst, ptr.get(dst) + 1);
        
        return pkt;
    }
    
    public int getCount(int dst, int t1, int t2) {
        if (!times.containsKey(dst)) return 0;
        
        ArrayList<Integer> list = times.get(dst);
        int start = ptr.get(dst);
        
        int left = lower(list, t1, start);
        int right = upper(list, t2, start);
        
        return right - left;
    }
    
    private int lower(ArrayList<Integer> list, int x, int s) {
        int lo = s, hi = list.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (list.get(mid) >= x) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
    
    private int upper(ArrayList<Integer> list, int x, int s) {
        int lo = s, hi = list.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (list.get(mid) > x) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}
