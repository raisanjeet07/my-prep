package com.san.lld;

public class CustomHashMap<K, V> {
    private class Node<K, V>{
        K key;
        V value;
        Node<K, V> nxt;
        Node<K, V> prv;
    }
}
