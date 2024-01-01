package com.san.ds.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Trie {
    private static class Node{
        String c;
        boolean isLeaf;
        private final Map<String, Node> children;

        public Node(String character){
            this.c = character;
            children = new HashMap<>();
        }

        public void setLeaf(boolean isLeaf){
            this.isLeaf = isLeaf;
        }

        public void addChild(Node node){
            if(!children.containsKey(node.c))
                children.put(node.c, node);
        }

        public Node getChild(String character){
            return children.get(character);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "c='" + c + '\'' +
                    ", isLeaf=" + isLeaf +
                    ", children=" + children +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Trie{" +
                "root=" + root +
                '}';
    }

    private final Node root = new Node("");
    public void addString(String str){
        addString(str, 0, root);
    }

    public void addString(String s, int currentIndex, Node node){
        if(currentIndex == s.length()){
            node.setLeaf(true);
            return;
        }
        String ch = s.charAt(currentIndex) + "";
        Node child = node.children.containsKey(ch)? node.getChild(ch) : new Node(ch);
        node.addChild(child);
        addString(s, currentIndex+1, child);
    }

    public List<String> getAllWordWithPrefix(String prefix){
        List<String> result = new ArrayList<>();
        getAllWordsWithPrefix(prefix, 0, root, result);
        return result;
    }

    private void getAllWordsWithPrefix(String prefix, int currentIndex, Node node,  List<String> resultContainer){
        if(prefix.length() == currentIndex){
            getAllWordsByDFS(prefix, node, resultContainer);
            return;
        }
        Node tmp = node.getChild(prefix.charAt(currentIndex) +"");
        if(tmp == null)
            return;
        getAllWordsWithPrefix(prefix, currentIndex+1, tmp, resultContainer);
    }

    private void getAllWordsByDFS(String prefix, Node node, List<String> resultContainer){
        if(node.isLeaf){
            resultContainer.add(prefix);
        }
        node.children.keySet().forEach(key -> {
            getAllWordsByDFS(prefix+key, node.children.get(key), resultContainer);
        });
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.addString("carpet");
        trie.addString("cart");
        trie.addString("car");
//        trie.addString("camera");
//        trie.addString("crate");
//        trie.addString("cart");
        System.out.println(trie);
        System.out.println(trie.getAllWordWithPrefix("ca").stream().sorted().limit(5).collect(Collectors.toList()));
        System.out.println(Stream.iterate(new int[]{0, 1}, x -> new int[]{x[1], x[0] + x[1]})
                .limit(8)
                .map(x -> x[0])
                .toList()
                .stream()
                .mapToInt(i -> i)
                .sum());

        System.out.println(Stream.iterate("as", str -> str+ "-"+"as").limit(5).toList());
        System.out.println(Stream.iterate(1, x -> x < 100, x -> 2*x).collect(Collectors.toList()));
    }


}
