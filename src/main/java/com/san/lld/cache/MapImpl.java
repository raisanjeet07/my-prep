package com.san.lld.cache;

public class MapImpl {


}

class HashMap<K, V>{
    class Entry<K, V>{
        K key;
        V value;

        Entry next;
        Entry prv;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }
        public int hashCode(){
            return key.hashCode();
        }

        public boolean equals(Object obj){
            if(obj == null)
                return false;
            return key.equals(obj);
        }
    }


    int capacity;

    int size;

    Entry<K, V>[] table;

    float sizeFactor;

    public HashMap(int initialSize){
        this.capacity = initialSize;
        this.table = new Entry[this.capacity];
    }

    public void put(K key, V value){
        if(key == null){
            throw new NullPointerException("null key not allowed");
        }

        Entry<K, V> entry = new Entry<>(key, value);
        add(entry);
    }

    private void add(Entry<K, V> entry) {
        int bucket = getBucket(entry);

    }

    private int getBucket(Entry<K, V> entry) {
        return entry.hashCode()%table.length;
    }
}