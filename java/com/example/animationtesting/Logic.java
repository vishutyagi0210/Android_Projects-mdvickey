package com.example.animationtesting;

import android.util.Log;
import java.util.Random;

public class Logic {
    private static int randomArray[][]={{1,2,3}, {1,2,3}, {1,2,3}};
    private Movement move = new Movement();
    private int index=-1;
    private static int[] arrIndex;


    //Tells About Movement of the view
    public Movement findNeighbourElement(int value){
        index = value;
        Log.d("button  value is ","button = "+index);
        arrIndex = searchIndex(value);
        Log.d("Search value is ","row = "+arrIndex[0]+" col = "+arrIndex[1]);
        int row = arrIndex[0];
        int col = arrIndex[1];
        int leftNeighbor=-1;
        int rightNeighbor=-1;
        int upperNeighbor=-1;
        int lowerNeighbor=-1;

        //printRandomArray();

        // Check left neighbor
        if (col > 0) {
            leftNeighbor = randomArray[row][col - 1];
        }

        // Check right neighbor
        if (col < 2) {
            rightNeighbor = randomArray[row][col + 1];
        }

        // Check upper neighbor
        if (row > 0) {
            upperNeighbor = randomArray[row - 1][col];
        }

        // Check lower neighbor
        if (row < 2) {
            lowerNeighbor = randomArray[row + 1][col];
        }


        //finding direction
        if (leftNeighbor == 99){
            move.setDirection("left");
            move.setCanMove(true);
        } else if (rightNeighbor ==99) {
            move.setDirection("right");
            move.setCanMove(true);
        } else if (upperNeighbor ==99) {
            move.setDirection("upper");
            move.setCanMove(true);
        }
        else if (lowerNeighbor == 99){
            move.setDirection("lower");
            move.setCanMove(true);
        }
        /*swapRandomArray(row,col);*/
        return move;
    }
    //Changing Positions
    private void swapRandomArray(int row, int col) {
        int[] indexOf99 = search99();
        int temp = randomArray[row][col];
        randomArray[row][col] = randomArray[indexOf99[0]][indexOf99[1]];
        randomArray[indexOf99[0]][indexOf99[1]] = temp;
    }
    private int[] search99() {
        int[] arrLocal={-1,-1};
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
               if (randomArray[i][j] == 99){
                    arrLocal[0]=i;
                    arrLocal[1] = j;
               }
            }
        }
        //Log.d("checking arrLocal","value is "+arrLocal[0]+" second value is "+arrLocal[1]);
        return arrLocal;
    }
    private int[] searchIndex(int value) {
        int indexArr[]={-1,-1};
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (randomArray[i][j] == value){
                    indexArr[0]=i;
                    indexArr[1]=j;
                }
            }
        }
        return indexArr;
    }

    //Random Array Generators
    private int[] generateRandomNumbers(int size) {
        int[] numbers = new int[size];
        Random random = new Random();

        // Initialize the array with numbers from 1 to 8
        size = size-1;
        for (int i = 0; i < size; i++) {
            numbers[i] = i + 1;
        }
        // Shuffle the array using Fisher-Yates algorithm
        for (int i = size - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
        return numbers;
    }
    public int[] getInvCount() {
        //printRandomArray();
        int[] arr;
        int max_inv_index = -1;
        arr = generateRandomNumbers(9);
        arr[8] = 99;

        int inv_count = 0;
        int max_inv_count = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 8; j++) {
                if (arr[i] < arr[j])
                    inv_count++;
            }
            if (inv_count > max_inv_count) {
                max_inv_count = inv_count;
                max_inv_index = i;
            }
        }
        if (inv_count % 2 == 0) {
            populateRandomArray(arr);
        }
        else {
            for (int i = 0; i < 8; i++) {
                if (arr[i] < arr[max_inv_index]) {
                    int temp = arr[i];
                    arr[i] = arr[max_inv_index];
                    arr[max_inv_index] = temp;
                    break;
                }
            }
            populateRandomArray(arr);
        }
        //printRandomArray();
        return arr;
    }
    private void populateRandomArray(int[] arr) {
        int count = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                randomArray[row][col] = arr[count];
                count++;
            }
        }
    }

    //Just for Testing purpose
    /*private void printRandomArray(){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                Log.d("Array values","Array "+randomArray[i][j]);
            }
        }
    }*/
    public void change99Index() {
        int row = arrIndex[0];
        int col = arrIndex[1];
        swapRandomArray(row,col);
    }
    public boolean winOrNot() {
        int[][] sortedArray ={{1,2,3}, {4,5,6}, {7,8,99}};
        int count=0;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (sortedArray[i][j] == randomArray[i][j]){
                    count++;
                }
            }
        }
        if (count == 9){
            Log.d("Returning ","Value is = true");
            return true;
        }
        Log.d("Returning ","Value is = false");
        return false;
    }
}
