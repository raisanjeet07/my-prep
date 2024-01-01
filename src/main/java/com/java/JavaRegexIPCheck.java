package com.java;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaRegexIPCheck {
    static List<String> blackListedIpRegex = List.of("*.123.23.*", "145.*", "*.234", "*.32.*", "123.*");

    static List<String> ips = List.of("123.123.123.323");
    static List<List<Integer>> parsedBlackListedIps = new ArrayList<>();

    public static void init(){
        blackListedIpRegex.forEach(str -> parsedBlackListedIps.add(parseIpRegEx(str)));
    }

    public static List<Integer> parseIpRegEx(String ipRegex){
        String[] p = ipRegex.split("\\.");
        List<Integer> partsList = new ArrayList<>();
        int l = p.length;
        String star = "*";
        int any = -1;
        if(l == 2){
            if(star.equals(p[0]))
                partsList = List.of(any, any, any, d(p[1])); // "*.123"
            else
                partsList = List.of(d(p[0]), any, any, any); // "123.*"
        }else if(l == 3){
            if(star.equals(p[0]) && star.equals(p[2]))
                partsList = List.of(any, any*d(p[1]), any*d(p[1]), any); // *.123.*
            else if(star.equals(p[0]))
                partsList = List.of(any, any, d(p[1]), d(p[2])); // *.123.123
            else if (star.equals(p[2]))
                partsList = List.of(d(p[0]), d(p[1]), any, any); // 123.123.*
            else
                partsList = List.of(d(p[0]), any, any, d(p[2])); // 123.*.123
        }else {
            partsList = List.of(d(p[0]),d(p[1]),d(p[2]),d(p[3])); // *.*.*.* or 1.1.1.* or *.1.1.1
        }
        return partsList;
    }

    public static int d(String str){
        try{
            return Integer.parseInt(str);
        }catch (Exception e){
            return -1;
        }

    }
    static String zeroTo255
            = "(\\d{1,2}|(0|1)\\"
            + "d{2}|2[0-4]\\d|25[0-5])";

    static String regex
            = zeroTo255 + "\\."
            + zeroTo255 + "\\."
            + zeroTo255 + "\\."
            + zeroTo255;

    static Pattern p = Pattern.compile(regex);

    public static boolean isBlackListed(String ip){
        if(!p.matcher(ip).matches())
            throw new RuntimeException("Not a valid Ip");
        return parsedBlackListedIps.stream().anyMatch(parsedBlackListIp -> cMatch(ip, parsedBlackListIp)) ;
    }

    public static boolean cMatch(String ip, List<Integer> coms){
        String[] components = ip.split("\\.");
        return IntStream.range(0,4).allMatch(index -> match(coms.get(index), components[index]));
    }

    public static boolean match(int bc, String ci){
        return bc == -1 || Math.abs(bc) == d(ci);
    }

    public static void main(String[] args) {
        init();
        System.out.println(parsedBlackListedIps);
        System.out.println(ips.stream().filter(JavaRegexIPCheck::isBlackListed).collect(Collectors.toList()));
    }
}
