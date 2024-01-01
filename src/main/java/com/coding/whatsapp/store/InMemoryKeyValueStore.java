package com.coding.whatsapp.store;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Stream;

public class InMemoryKeyValueStore <K, E> extends AbstractStore<E>{
    private final Map<K, E> data = new HashMap<>();
    @Override
    public Stream<E> getStream() {
        return new HashSet<>(data.values()).stream();
    }

    public E getByKey(K key){
        return data.get(key);
    }

    public void add(K key, E value){
        data.put(key, value);
    }

}
