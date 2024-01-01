package com.san.ds;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Node{
    @JsonProperty("p")
    String currentNodePriFix;
    @JsonProperty("l")
    boolean isLeaf;
    @JsonProperty("c")
    Map<String, Node> children;

    public Node(){}
    public Node(String prefix){
            currentNodePriFix = prefix;
        }
    }