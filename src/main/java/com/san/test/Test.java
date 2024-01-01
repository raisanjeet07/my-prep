package com.san.test;

import java.util.*;

class Test {

    public static String Foo(String param) {
        return param;
    }

    public static void main (String[] args) {
        Stream startStream = getStartStream(1,2,3,4,4,5,6,7,7,8,89,1);
        PriorityQueue<Star> heap = new PriorityQueue<>();
        int k = 10;
        while(startStream.hasNext()){
            heap.add(startStream.next());
            if(heap.size() > k)
                heap.remove();
        }
        System.out.println(heap);

    }

    static Stream getStartStream(int... distances){
        List<Star> stars = new ArrayList<>(distances.length);

        for(int distance : distances){
            stars.add(Star.ofDistance(distance));
        }
        return Stream.of(stars);
    }


    // A stream of stars
    static class Stream {
        // Removes and returns the next Star in the stream. Throws if there are no more stars
        private List<Star> data;

        private Stream(List<Star> stars){
            this.data = stars;
        }

        public static Stream of(List<Star> data){
            List<Star> stars = new ArrayList<>(data.size());
            for(Star star : data){
                stars.add(star);
            }
            return new Stream(stars);
        }

        int currentIndex = -1;

        public Star next(){
            if(currentIndex >= data.size() - 1)
                throw new RuntimeException("Stream out of starts");
            return data.get(++currentIndex);
        }
        // Returns true if there is at least one star remaining in the stream
        public boolean hasNext(){
            return currentIndex < data.size() - 1;
        }
    }

    static class Star implements Comparable<Star>{
        int distance;
        private Star(int distance){
            this.distance = distance;
        }
        public static Star ofDistance(int distance){
            return new Star(distance);
        }

        public int compareTo(Star star){
            return -(this.distance - star.distance);
        }

        public String toString(){
            return "star: " +distance;
        }
    }

}