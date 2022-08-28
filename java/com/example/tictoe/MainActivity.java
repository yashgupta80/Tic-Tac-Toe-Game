package com.example.tictoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0: = x, 1: = O, 2: = empty;
    int activePlayer = 0;
    int [] gameState = {2,2,2,2,2,2,2,2,2,};
    boolean gameActive = true;


    // possibilities of player winning
    // 2D Array :
    int[][] WinningPositions = {{0,1,2}, {3,4,5}, {6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};



    public void dropIn(View view){
        ImageView imageView = (ImageView) view;

        int tapedImage = Integer.parseInt(imageView.getTag().toString());
        if (gameState[tapedImage] == 2 && gameActive) {
            gameState[tapedImage] = activePlayer;

            if (activePlayer == 0) {
                imageView.setImageResource(R.drawable.x);
                activePlayer = 1;
            } else {
                imageView.setImageResource(R.drawable.o);
                activePlayer = 0;
            }
            imageView.setTranslationY(-1500);

            imageView.animate().translationYBy(1500).rotation(3600).setDuration(300);


            for (int[] winningPosition : WinningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] ==
                        gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    gameActive = false;
                    String Winner = "";
                    if (activePlayer == 1) {
                        Winner = "x";
                    } else {
                        Winner = "O";
                    }
                    TextView textView = findViewById(R.id.winningTextView);
                    Button button = findViewById(R.id.playAgainButtton);
                    textView.setText(Winner + " has won");
                    textView.setVisibility(view.VISIBLE);
                    button.setVisibility(view.VISIBLE);



                }

            }
        }
    }
    // Method for playAgain Method:
    public void playAgain(View view){
        TextView textView = findViewById(R.id.winningTextView);
        Button button = findViewById(R.id.playAgainButtton);
        textView.setVisibility(view.INVISIBLE);
        button.setVisibility(view.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.myGridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
            // do stuff with child view
        }
        for (int i = 0; i<gameState.length; i++){
            gameState[i] = 2;
        }
         activePlayer = 0;
        gameActive = true;



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}