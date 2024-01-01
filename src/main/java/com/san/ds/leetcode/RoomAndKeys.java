package com.san.ds.leetcode;

import java.util.*;

public class RoomAndKeys {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> roomsKeys = new HashSet<>();
        Queue<Integer> keyQue = new ArrayDeque<>();
        roomsKeys.add(0);
        int i = 0;
        for(int roomKey : rooms.get(0)){
            roomsKeys.add(roomKey);
            keyQue.add(roomKey);
        }
        boolean haveKey[] = new boolean[rooms.size()];
        int room;
        while(keyQue.size() > 0){
            room = keyQue.poll();
            haveKey[i++] = true;
            for(int roomKey : rooms.get(room)){
                if(!roomsKeys.contains(roomKey)){
                    keyQue.add(roomKey);
                    roomsKeys.add(roomKey);
                }
            }

        }

        boolean allRooms = true;
        for(boolean t : haveKey)
            allRooms &= t;
        return allRooms;
    }
}
