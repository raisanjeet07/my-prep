package com.san.lld.cache;

public class TTLCache<K, V> {

    private DataStore<K, V> store;
    private long max_capacity = 100L;

    public TTLCache(long max_capacity){
        this.max_capacity = max_capacity;
        this.store = new DataStore<>();
    }

    public TTLCache(){
        this.store = new DataStore<>();
    }

    class TTLNode<K>{
        private long ttl;
        private K key;



        public long getTtl() {
            return ttl;
        }

        public K getKey() {
            return key;
        }
    }
}
