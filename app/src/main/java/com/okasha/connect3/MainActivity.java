package com.okasha.connect3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //black player = 0,  red player =1
    int player = 0;

    //array to hold the state of each cell, 2 is unchecked state
    int gameState[] = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};
    boolean gameIsActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        int clickedCell = Integer.parseInt(view.getTag().toString());



        if(gameState[clickedCell] == 2 && gameIsActive) {
            gameState[clickedCell] = player;
            counter.setTranslationY(-1000);
            if (player == 0) {
                counter.setImageResource(R.drawable.black);
                player = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                player = 0;
            }
            counter.animate().translationYBy(1000).setDuration(1000);
        }
        for(int[] winningposition : winningPositions){
            if(gameState[winningposition[0]] == gameState[winningposition[1]] &&
               gameState[winningposition[1]] == gameState[winningposition[2]] &&
               gameState[winningposition[0]] != 2)
            {
                String winner;
                if (gameState[winningposition[0]] == 0){
                    winner = "black";
                } else {
                    winner = "red";
                }
                LinearLayout newGameLayout = (LinearLayout) findViewById(R.id.newGame_layout);

                TextView winnerMessage = (TextView) findViewById(R.id.winner_message);
                winnerMessage.setText( winner + "wins");
                newGameLayout.setVisibility(View.VISIBLE);
                gameIsActive = false;
            }
        }

    }

    public void newGame(View view){

        //reset player and game state
        player = 0;
        for(int x = 0; x<gameState.length; x++){
            gameState[x]=2;
        }
        gameIsActive = true;

        //set all image resources to null
        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);
        for(int x =0; x < grid.getChildCount(); x++){
            ( (ImageView) grid.getChildAt(x)).setImageResource(0);
        }
        LinearLayout newGameLayout = (LinearLayout) findViewById(R.id.newGame_layout);
        newGameLayout.setVisibility(View.INVISIBLE);
    }
}
