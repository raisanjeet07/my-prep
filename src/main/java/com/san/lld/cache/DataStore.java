package com.san.lld.cache;

import java.util.HashMap;
import java.util.Map;

public class DataStore <K, V> {
    private Map<K, V> store;

    public DataStore(){
        this.store = new HashMap<>();
    }

    public V get(K k){
        return this.store.get(k);
    }

    public void put(K k , V v){
        store.put(k, v);
    }
}


