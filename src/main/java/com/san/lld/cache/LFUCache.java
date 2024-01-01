package com.san.lld.cache;

import java.util.HashMap;
import java.util.Map;

class LFUCache {

    class Node {
        int key;
        int value;
        Node prv;
        Node next;
        int count;

        public Node(int key, int value, Node prv){
            this.key = key;
            this.value = value;
            this.prv = prv;
            if(prv != null)
                prv.next = this;
        }
        public void incrementCount(){
            this.count += 1;
            if (this.prv != null && this.prv.count <= this.count){
                Node next = this.next;
                Node prv = this.prv;

                if(prv.prv == null){
                    head = this;
                }

                this.prv = prv.prv;
                this.next = prv;

                prv.prv = this;
                prv.next = next;

                if(next != null)
                    next.prv = prv;
                else
                    last = prv;
            }
        }

        public void pick(){
            if(this.prv != null){
                this.prv.next = this.next;
            }
            if(this.next != null){
                this.next.prv = this.prv;
            }
        }
    }

    int capacity;

    Node head;
    Node last;

    int size;

    Map<Integer, Node> table;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        table = new HashMap<>();
    }
    
    public int get(int key) {
        Node node = table.get(key);
        if(node != null){
            node.incrementCount();
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        Node tmp;
        if(table.containsKey(key)){
            tmp = table.get(key);
            tmp.value = value;
            tmp.incrementCount();
            return;
        }
        while(this.capacity <= size){
            tmp = last;
            last = last.prv;
            tmp.pick();
            table.remove(tmp.key);
            size--;
        }
        Node node = new Node(key, value, head);
        head = node;
        table.put(key, node);
        if(size == 0){
            last = node;
        }
        size++;
    }


    public static void main(String[] args){
        LFUCache obj = new LFUCache(2);
        int[][]tests = {{2},{1,1},{2,1},{3,3},{2,2},{4,4},{2}};

        for(int[] test : tests){
            if(test.length == 2){
                obj.put(test[0], test[1]);
                System.out.println("null");
            }else{
                System.out.println(obj.get(test[0]));
            }
        }
    }
}