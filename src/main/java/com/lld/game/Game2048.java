package com.lld.game;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Game2048 {
    private int n = 4;
    private int[][] board = new int[n][n];

    public static void main(String[] args) {
        Game2048 game = new Game2048();
        game.takeCommand();
    }

    public void takeCommand(){
        String moveArrow;
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while(!exit){
            moveArrow = scanner.next();
            switch (moveArrow.toLowerCase()) {
                case "u" -> moveUp();
                case "d" -> moveDown();
                case "l" -> moveLeft();
                case "r" -> moveRight();
                case "b" -> exit = true;
            }
        }
    }

    private void moveUp(){
        for(int i =0; i < n; i++){
            moveUp(i);
        }
        addRandomTile();
        display();
    }
    private void moveDown(){
        for(int i =0; i < n; i++){
            moveDown(i);
        }
        addRandomTile();
        display();
    }
    private void moveLeft(){
        for(int i =0; i < n; i++){
            moveLeft(i);
        }
        addRandomTile();
        display();
    }
    private void moveRight(){
        for(int i =0; i < n; i++){
            moveRight(i);
        }
        addRandomTile();
        display();
    }
    public Game2048(){
        init();
        display();
    }
    private void init(){
        addRandomTile();
        addRandomTile();
        addRandomTile();
    }

    private void addRandomTile() {
        // Add a new tile (2 or 4) to a random empty cell
        Random random = new Random();
        int value = 2 ;//(random.nextInt(2) + 1) * 2; // Either 2 or 4
        int row, col;

        do {
            row = random.nextInt(4);
            col = random.nextInt(4);
        } while (board[row][col] != 0);

        board[row][col] = value;
    }

    private void display(){
        for(int[] a: board)
            System.out.println(Arrays.toString(a));
        System.out.println("----------------------------");
    }

    public void moveUp(int col){
        Stack<Integer> stack = new Stack<>();
        boolean lastMerged = false;
        int row = 0;
        while(row < n){
            if(board[row][col] > 0){
                if(!lastMerged && stack.size() > 0 && stack.peek() == board[row][col]){
                    stack.pop();
                    stack.push(board[row][col]*2);
                    lastMerged = true;
                }else{
                    stack.push(board[row][col]);
                    lastMerged = false;
                }
            }
            row++;
        }
        for(int i = n-1; i > -1; i--){
            board[i][col] = stack.size() > 0 && i == stack.size() - 1 ? stack.pop() : 0;
        }
    }

    public void moveDown(int col){
        Stack<Integer> stack = new Stack<>();
        boolean lastMerged = false;
        int row = n -1;
        while(row >= 0){
            if(board[row][col] > 0){
                if(!lastMerged && stack.size() > 0 && stack.peek() == board[row][col]){
                    stack.pop();
                    stack.push(board[row][col]*2);
                    lastMerged = true;
                }else{
                    stack.push(board[row][col]);
                    lastMerged = false;
                }
            }
            row--;
        }
        int i = 0;
        for(; i < n - stack.size(); i++){
            board[i][col] = 0;
        }
        while(stack.size() > 0){
            board[i][col] = stack.pop();
            i++;
        }
    }
    private void moveRight(int row){
        Stack<Integer> stack = new Stack<>();
        boolean lastMerged = false;
        int col = n -1;
        while(col >= 0){
            if(board[row][col] > 0){
                if(!lastMerged && stack.size() > 0 && stack.peek() == board[row][col]){
                    stack.pop();
                    stack.push(board[row][col]*2);
                    lastMerged = true;
                }else{
                    stack.push(board[row][col]);
                    lastMerged = false;
                }
            }
            col--;
        }
        int i = 0;
        for(; i < n - stack.size(); i++){
            board[row][i] = 0;
        }
        while(stack.size() > 0){
            board[row][i] = stack.pop();
            i++;
        }
    }

    private void moveLeft(int row){
        Stack<Integer> stack = new Stack<>();
        boolean lastMerged = false;
        int col = 0;
        while(col < n){
            if(board[row][col] > 0){
                if(!lastMerged && stack.size() > 0 && stack.peek() == board[row][col]){
                    stack.pop();
                    stack.push(board[row][col]*2);
                    lastMerged = true;
                }else{
                    stack.push(board[row][col]);
                    lastMerged = false;
                }
            }
            col++;
        }
        for(int i = n-1; i > -1; i--){
            board[row][i] = stack.size() > 0 && i == stack.size() - 1 ? stack.pop() : 0;
        }
    }
}
