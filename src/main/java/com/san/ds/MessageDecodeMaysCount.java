package com.san.ds;

public class MessageDecodeMaysCount {
    public static void main(String[] args) {
        String ciphered = "1234";
        System.out.println(waysToDecodeMsg(ciphered));

    }

    private void wrongAns(String ciphered){
        int sum = 0;
        int currentIndex = ciphered.length() - 1;
        boolean lastCharValid = false;
        lastCharValid = isValidRange(ciphered.substring(currentIndex, currentIndex+1));
        if(lastCharValid)
            sum++;
        currentIndex--;
        boolean currentValid = false;
        boolean isValid = true;
        while(currentIndex >= 0){
            currentValid = isValidRange(ciphered.substring(currentIndex, currentIndex+1));
            if(currentValid)
                sum++;
            if(isValidRange(ciphered.substring(currentIndex, currentIndex+2))){
                sum++;
            }else if(!currentValid || !lastCharValid){
                isValid = false;
                System.out.println("Invalid String");
                break;
            }
            lastCharValid = currentValid;
            currentIndex--;
        }
        if(isValid)
            System.out.println(sum);
    }

    private static int waysToDecodeMsg(String msg){
        int n = msg.length();
        int[] arr = new int[n+1];
        arr[n] = 1;
        arr[n - 1] = isValidRange(msg.substring(n-1)) ? 1 : 0;
        n = n - 2;
        int totalWays;
        while(n >= 0){
            totalWays = 0;
            if(isValidRange(msg.substring(n, n+1)))
                totalWays += arr[n+1];
            if(isValidRange(msg.substring(n, n+2)))
                totalWays += arr[n+2];
            arr[n] = totalWays;
            n--;
        }
        return arr[0];
    }

    private static boolean isValidRange(String code){
        int tmp = Integer.parseInt(code);
        return !"0".equals(code.charAt(0)+"") && 0 < tmp && tmp <26;
    }

}
