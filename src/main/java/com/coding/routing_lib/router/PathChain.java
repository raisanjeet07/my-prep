package com.coding.routing_lib.router;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathChain {
    private final String uri;
    private final List<PathToken> pathTokens;

    public PathChain(String uri){
        this.uri = uri;
        this.pathTokens = initChain();
    }

    private List<PathToken> initChain(){
        List<PathToken> pathTokens = new ArrayList<>();
        if(uri == null) throw new NullPointerException("path is null");
        String[] pathTokenStrings = this.uri.split("/");
        for(String token : pathTokenStrings)
            if(!token.equals(""))
                pathTokens.add(new PathToken(token));
        return Collections.unmodifiableList(pathTokens);
    }

    public boolean isPathMatching(PathChain pathChain){
        if(this.pathTokens.size() != pathChain.pathTokens.size()) return false;
        int i = 0;
        while(i < this.pathTokens.size() && this.pathTokens.get(i).isTokenMatching(pathChain.pathTokens.get(i)))
            i++;
        return i == this.pathTokens.size();
    }

    static class PathToken{
        private final String token;
        private boolean isRegEx;
        private boolean isPathVariable;

        public PathToken(String token){
            this.token = token;
            if(token.contains("{") && token.contains("}"))
                this.isPathVariable = true;
            else if(!token.matches("[A-Za-z0-9]+")){
                this.isRegEx = true;
            }
        }

        private boolean isTokenMatching(PathToken reqPathToken){
            return reqPathToken.token.matches(this.token);
        }
    }
}
