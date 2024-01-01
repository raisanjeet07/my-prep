package com.san.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReverseLinkedList {

    static class LinkedList<T>{

        Node<T> head;
        Node<T> tail;
        int size = 0;

        public LinkedList(List<T> list){
            this.head = build(list);
        }
        class Node<T>{
            T value;
            Node next;
            public Node(T t){
                this.value = t;
            }
        }

        public void traverse(){
            Node<T> tmp = head;
            while(tmp != null){
                System.out.print(tmp.value + " ");
                tmp = tmp.next;
            }
            System.out.println();
        }

        public Node<T> build(List<T> list){
            Node<T> head = new Node<>(list.get(0));
            size++;
            tail = head;
            if(list.size() > 1){
                Node<T> tmp = head;
                int i = 1;
                while(i < list.size()){
                    tmp.next = new Node(list.get(i));
                    size++;
                    i++;
                    tmp = tmp.next;
                }
                tail = tmp;
            }
            return head;
        }

        public void reverse(){
            Node<T> current = head.next;
            Node<T> next;
            Node<T> prv = head;
            prv.next = null;
            tail = prv;
            while(current != null){
                next = current.next;
                current.next = prv;
                prv = current;
                current = next;
            }
            head = prv;
        }

        public void add(T t){
            tail.next = new Node<>(t);
            tail = tail.next;
            size++;
        }
    }


    public static void main(String[] args){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,0);
        LinkedList<Integer> ll = new LinkedList<>(list);
        ll.traverse();
        ll.reverse();
        ll.traverse();
        ll.add(11);
        ll.traverse();

    }


}
