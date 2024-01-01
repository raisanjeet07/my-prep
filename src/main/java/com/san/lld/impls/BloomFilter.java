package com.san.lld.impls;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Random;

/**
 * it helps to check a key is present or not based on some approximation or accuracy.
 * steps are as follows* create n hash function that return int/long values
 *
 */
public class BloomFilter {

    private int murmurFunctionCount;

    private int[] table;

    private int rows;

    private final int intSizeAsCols = 32;

    private MurMurFun[] murMurFuns;

    public BloomFilter(int hashFunctionCount, int rows){
        this.table = new int[rows];
        this.rows = rows - 1;
        this.murmurFunctionCount = hashFunctionCount;
        initMurMurFunctions();
    }

    public void addKey(String key){
        if(StringUtils.hasLength(key)){
            int hashCode;
            byte[] bytes = key.getBytes();
            for(MurMurFun fun : murMurFuns){
                hashCode = fun.murmur2(bytes);
                updateTable(Math.abs(hashCode));
            }
        }
    }

    public boolean existKey(String key){
        if(StringUtils.hasLength(key)){
            int hashCode;
            byte[] bytes = key.getBytes();
            for(MurMurFun fun : murMurFuns){
                hashCode = fun.murmur2(bytes);
                if(!exist(Math.abs(hashCode)))
                    return false;
            }
        }else
            return false;
        return true;
    }

    private boolean exist(int hashCode) {
        int row = hashCode % rows;
        int col = hashCode % intSizeAsCols;
        return (table[row] & 1 << col) > 0;
    }

    private void updateTable(int hashCode) {
        int row = hashCode % rows;
        int col = hashCode % intSizeAsCols;
        table[row] = (table[row] | 1 << col);
    }

    private void initMurMurFunctions(){
        murMurFuns = new MurMurFun[murmurFunctionCount];
        int i = 1;
        while(i <= murmurFunctionCount){
            murMurFuns[i - 1] = new MurMurFun(Math.abs((int)(Math.random() * System.currentTimeMillis() * i++)));
        }
    }

    public void printTable(){
        System.out.println(Arrays.toString(table));
    }

    public static void main(String[] args) {
        BloomFilter bloomFilter = new BloomFilter(10, 10);
        bloomFilter.addKey("1234231423");
        System.out.println(bloomFilter.existKey("1234231423"));
        System.out.println(bloomFilter.existKey("12342qe123rd31423"));
        bloomFilter.printTable();
    }

    static class MurMurFun{
        int seed;
        public MurMurFun(int seed){
            this.seed = seed;
        }
        public int murmur2(byte[] data) {
            int length = data.length;
            int h = seed ^ length;
            int length4 = length / 4;

            for(int i = 0; i < length4; ++i) {
                int i4 = i * 4;
                int k = (data[i4 + 0] & 255) + ((data[i4 + 1] & 255) << 8) + ((data[i4 + 2] & 255) << 16) + ((data[i4 + 3] & 255) << 24);
                k *= 1540483477;
                k ^= k >>> 24;
                k *= 1540483477;
                h *= 1540483477;
                h ^= k;
            }

            switch (length % 4) {
                case 3:
                    h ^= (data[(length & -4) + 2] & 255) << 16;
                case 2:
                    h ^= (data[(length & -4) + 1] & 255) << 8;
                case 1:
                    h ^= data[length & -4] & 255;
                    h *= 1540483477;
                default:
                    h ^= h >>> 13;
                    h *= 1540483477;
                    h ^= h >>> 15;
                    return h;
            }
        }
    }
}
