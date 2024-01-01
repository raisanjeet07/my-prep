package com.san.ds.recurssion;

public class PrintAllAbbreviation {
    public static void main(String[] args) {
        printAllAbbreviation("pep");
    }

    public static void printAllAbbreviation(String str){
        printAllAbbreviation(str, 0, 0, "");
    }

    public static void printAllAbbreviation(String str, int index, int count, String answer){
        if(index >= str.length()){
            if(count == 0)
                System.out.println(answer);
            else
                System.out.println(answer + count);
            return;
        }
        index++;
        printAllAbbreviation(str, index, count + 1, answer);
        answer += (count > 0 ? count+"" : "") + str.charAt(index - 1) +"";
        printAllAbbreviation(str, index, 0, answer);
    }
}
