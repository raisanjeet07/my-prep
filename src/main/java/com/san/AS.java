package com.san;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class AS {

    static class SerA{
        public String getData(){
            return "a";
        }
    }

    public int solution(String S, int[] X, int[] Y) {
        // Implement your solution here
        List<Point> points = new ArrayList<>();
        double rDistance;
        for(int i = 0; i < S.length(); i++){
            rDistance = Math.sqrt(Math.pow(X[i], 2) + Math.pow(Y[i], 2));
            points.add(new Point(S.charAt(i)+"", rDistance));
        }

        Collections.sort(points);
        Map<String, Point> checkedPoints = new HashMap<>();
        for(Point p : points) {
            if (checkedPoints.containsKey(p.name)) {
                if (p.rDistance == checkedPoints.get(p.name).rDistance) {
                    return checkedPoints.size() - 1;
                } else {
                    return checkedPoints.size();
                }
            }
            checkedPoints.put(p.name, p);
        }
        return checkedPoints.size();
    }

    static class Point implements Comparable<Point>{
        double rDistance;
        String name;
        public Point(String name, double rDistance){
            this.name = name;
            this.rDistance = rDistance;
        }

        @Override
        public int compareTo(@NotNull Point o) {
            return Double.compare(this.rDistance, o.rDistance);
        }
    }

    public int solution(String S) {
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> c = new HashSet<>(Arrays.asList('B', 'A','N'));
        for(int i = 0; i < S.length(); i++){
            if(c.contains(S.charAt(i))){
                map.put(S.charAt(i), map.getOrDefault(S.charAt(i), 0) + 1);
            }
        }
        int min = map.get('B');
        int tmp = map.get('A')/3;
        if(min > tmp)
            min = tmp;
        tmp = map.get('N')/2;
        if(min > tmp)
            min = tmp;
        return min;
    }


    public String largestGoodInteger(String num) {
        Map<String, Integer> map = new HashMap<>();
        String max = "";
        int si = 0;
        int ei = 0;
        String s;
        for(int i = 2; i < num.length(); i++){
            ei = i;
            s= num.charAt(i) +"";
            map.put(s, map.getOrDefault(s, 0) + 1);
            while(map.size() > 1){
                s = num.charAt(si) + "";
                if(map.get(s) > 1){
                    map.put(s, map.get(s) - 1);
                }else{
                    map.remove(s);
                }
                si++;
            }
            if (ei - si == 2) {
                if (!max.equals("")) {
                    max = (num.charAt(si) + "");
                }else{
                    max = max.compareTo(num.charAt(si) + "") > 0 ? max : num.charAt(si) + "";
                }
            }
        }
        return max+max+max;
    }


    public long power(int x, int n){
        if(n == 0)
            return 1L;
        long pTem = power(x, n/2);
        return n%2 == 0 ? pTem *pTem : x * pTem *pTem;
    }

    public static void main(String[] args) {
//        List<SerA> services = new ArrayList<>();
//        services.add(new SerA());
//        System.out.println(services.parallelStream().map( serA -> getData(serA)).collect(Collectors.toList()));
        System.out.println(new AS().power(2, 10));
    }

    public static <T> T getData(SerA a){
        try{
            return (T)(a.getData());
        }catch (Exception e){
            return null;
        }
    }
}
