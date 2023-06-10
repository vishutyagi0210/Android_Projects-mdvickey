package com.example.animationtesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[] boardArr={1,2,3,4,5,6,7,8,9};
    int[] gridChildPositions = {0,1,2,3,4,5,6,7};
    int[] bPosition = {0,1,2,3,4,5,6,7};
    Button[] buttons = new Button[9];
    LinearLayout linearLayout;
    GridLayout gridLayout;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons[0]= findViewById(R.id.btnView1);
        buttons[1]= findViewById(R.id.btnView2);
        buttons[2]= findViewById(R.id.btnView3);
        buttons[3]= findViewById(R.id.btnView4);
        buttons[4]= findViewById(R.id.btnView5);
        buttons[5]= findViewById(R.id.btnView6);
        buttons[6]= findViewById(R.id.btnView7);
        buttons[7]= findViewById(R.id.btnView8);
        linearLayout = findViewById(R.id.root_layout);
        cardView = findViewById(R.id.card_id);
        gridLayout = findViewById(R.id.grid_layout);
        ResetBoard board = new ResetBoard();
        boardArr = board.getArray();
        setBoard(boardArr);

    }

    private void printRandomArray1() {
        for (int i=0;i<9;i++){
            Log.d("Array ", " Array is = "+gridChildPositions[i]);
        }
    }

    private void printRandomArrayNew() {
        for (int i=0;i<9;i++){
            Log.d("ArrayNew ", " Array is = "+boardArr[i]);
        }
    }

    private void setBoard(int[] arrRandomNumbers) {
        buttons[0].setText(String.valueOf(arrRandomNumbers[0]));
        buttons[1].setText(String.valueOf(arrRandomNumbers[1]));
        buttons[2].setText(String.valueOf(arrRandomNumbers[2]));
        buttons[3].setText(String.valueOf(arrRandomNumbers[3]));
        buttons[4].setText(String.valueOf(arrRandomNumbers[4]));
        buttons[5].setText(String.valueOf(arrRandomNumbers[5]));
        buttons[6].setText(String.valueOf(arrRandomNumbers[6]));
        buttons[7].setText(String.valueOf(arrRandomNumbers[7]));
    }

    public void ButtonClicked(View view) {
        Movement movement = new Movement();
        Button button = (Button) view;
        String buttonValue = button.getText().toString();
        Log.d("button  value is ","button = "+buttonValue);
        int intValueOfButton = Integer.valueOf(buttonValue);
        movement.setValue(intValueOfButton);
        Movement m1 = movement.findMovement();
        if (m1.getDirection() == "left"){
            float lastTranslationX = view.getTranslationX();
            ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationX", lastTranslationX + -315f);
            animation.setDuration(200);
            animation.start();
            movement.change99position();
            if(movement.winner()){
                linearLayout.setBackgroundColor(getResources().getColor(R.color.winner_color));
                Toast.makeText(this, "Please Reset the board", Toast.LENGTH_SHORT).show();
            }

        } else if (m1.getDirection() == "right") {
            float lastTranslationX = view.getTranslationX();
            ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationX", lastTranslationX + 315f);
            animation.setDuration(200);
            animation.start();
            movement.change99position();
            if(movement.winner()){
                linearLayout.setBackgroundColor(getResources().getColor(R.color.winner_color));
                Toast.makeText(this, "Please Reset the board", Toast.LENGTH_SHORT).show();
            }

        } else if (m1.getDirection() == "upper") {
            float lastTranslationY = view.getTranslationY();
            ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationY", lastTranslationY + -315f);
            animation.setDuration(200);
            animation.start();
            movement.change99position();
            if(movement.winner()){
                linearLayout.setBackgroundColor(getResources().getColor(R.color.winner_color));
                Toast.makeText(this, "Please Reset the board", Toast.LENGTH_SHORT).show();
            }

        }
        else if (m1.getDirection() == "lower"){
            float lastTranslationY = view.getTranslationY();
            ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationY", lastTranslationY + 315f);
            animation.setDuration(200);
            animation.start();
            movement.change99position();
            if(movement.winner()){
                linearLayout.setBackgroundColor(getResources().getColor(R.color.winner_color));
                Toast.makeText(this, "Please Reset the board", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void resetBoard(View view) {
        gridChildPositions = boardArr;
        setBPositionArray();
        ResetBoard board1 = new ResetBoard();
        boardArr = board1.getArray();
        setNewBoard(boardArr);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.black));
    }

    private void setBPositionArray() {
        for (int i=0;i<8;i++){
            String newIndex = buttons[i].getText().toString();
            int newIndexInInt = Integer.parseInt(newIndex);
            bPosition[newIndexInInt-1] = i;
        }
    }

    private void setNewBoard(int[] arrRandomNumbers) {
        printRandomArray1();
        for (int i=0; i<8;i++){

            int takeFromRandomAt = searchForButtonIndex(i);
            buttons[i].setText(String.valueOf(arrRandomNumbers[takeFromRandomAt]));
        }
        printRandomArrayNew();
    }
    private int searchForButtonIndex(int target) {
        for (int i=0;i<8;i++){
            if (bPosition[i] == target){
                return i;
            }
        }
        return -1;
    }
}