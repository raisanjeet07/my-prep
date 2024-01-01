package com.bloomfliter;

import lombok.Builder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
public class BloomFilter<T> {
    private List<BloomLayer<T>> bloomLayers;

    private int size;

    protected BloomFilter(List<BloomLayer<T>> layers, int size){
        this.bloomLayers = layers;
        this.size = size;
    }

    public boolean isSeen(T data){
        return !bloomLayers.stream().anyMatch(layer -> !layer.isSeen(data));
    }

    public void markSeen(T data){
        bloomLayers.forEach(layer -> layer.markSeen(data));
    }

    public static <T> BloomFilterBuilder<T> builder() {
        return new BloomFilterBuilder<>();
    }

    public static class BloomFilterBuilder<T> {
        private final List<BloomLayer<T>> layers = new ArrayList<>();
        private int size;

        BloomFilterBuilder() {
        }

        public BloomFilterBuilder<T> addAlgo(final BloomAlgo<T> algo) {
            this.layers.add(new BloomLayer<>(algo, size));
            return this;
        }

        public BloomFilterBuilder<T> size(final int size) {
            this.size = size;
            return this;
        }

        public BloomFilter<T> build() {
            return new BloomFilter<>(this.layers, this.size);
        }

        public String toString() {
            return "BloomFilter.BloomFilterBuilder(layers=" + this.layers + ", size=" + this.size + ")";
        }
    }
}

class BloomLayer<T>{
    BloomAlgo<T> algo;
    BloomData bloomData;

    public BloomLayer(BloomAlgo<T> algo, int size){
        this.bloomData = new BloomData(size);
        this.algo = algo;
    }

    public boolean isSeen(T data){
        int hash = getHash(data);
        return bloomData.isBitSet(hash);
    }

    public void markSeen(T data){
        int hash = getHash(data);
        bloomData.setBit(hash);
    }

    private int getHash(T data){
        return Math.abs(algo.getHash(data));
    }
}

class BloomData{
    private final Map<Integer, Integer> bitBucket;
    private final int size;
    private final int INT_BIT_LENGTH = 31;

    private int buckets;
    BloomData(int size) {
        this.size = size;
        this.bitBucket = new HashMap<>();
        initializeBitBucket(size);
        System.out.println("Total consumed Memory:");

    }

    private void initializeBitBucket(int size){
        buckets = size / INT_BIT_LENGTH + (size%INT_BIT_LENGTH == 0 ? 0 : 1);
        int bucket = 0;
        while(bucket < buckets)
            bitBucket.put(bucket++, 0);
    }

    private int getBucketForHash(int hash){
        return hash%buckets;
    }

    private int getBucketBit(int hash){
        return hash%INT_BIT_LENGTH;
    }

    public boolean isBitSet(int hash){
        int bucket = getBucketForHash(hash);
        int bitPosition = getBucketBit(hash);
        int bitMap = bitBucket.get(bucket);
        int positionToCheck = 1 << bitPosition;
        return (bitMap & positionToCheck) > 0;
    }

    public void setBit(int hash){
        int bucket = getBucketForHash(hash);
        int bitPosition = getBucketBit(hash);
        int bitMap = bitBucket.get(bucket);
        int positionToCheck = 1 << bitPosition;
        bitBucket.put(bucket, bitMap | positionToCheck);
    }

}
