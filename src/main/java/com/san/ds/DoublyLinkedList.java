package com.san.ds;

public class DoublyLinkedList {


    class Node<Value>{
        private Value v;
        Node prv;
        Node nxt;

        public Node(Value v) {
            this.v = v;
        }

        public Value getV() {
            return v;
        }

        public Node getPrv() {
            return prv;
        }

        public Node getNxt() {
            return nxt;
        }

        public void setPrv(Node prv) {
            this.prv = prv;
        }

        public void setNxt(Node nxt) {
            this.nxt = nxt;
        }
    }
}
