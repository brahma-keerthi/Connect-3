package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    Button playAgain = (Button) findViewById(R.id.playAgainButton);
    int turn = 0;

    // To continue the game until game is not won by some one
    boolean gameWon = false;

    // turn 0 -> blue , turn 1 -> yellow and turn 2 -> not played
    int[] gameState = { 2, 2,2 ,2, 2,2 ,2, 2,2 };

    int[][] winState = { {0, 3, 6} , {1, 4, 7} , {2, 5, 8} , {0, 1, 2} , {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {
        // Gives the view of image
        ImageView tappedOn = (ImageView) view;
        LinearLayout gameEnd = (LinearLayout) findViewById(R.id.linearLayout);
        TextView gameEndText = (TextView) findViewById(R.id.gameEndText);

        // Gives the tag of the view
        int tappedView = Integer.parseInt(tappedOn.getTag().toString());

        if (gameState[tappedView] == 2 && !gameWon) {
            gameState[tappedView] = turn % 2;

            //        initially the coin is above the screen
            tappedOn.setTranslationY(-1000f);

            if (turn % 2 == 0 && turn < 9) {
                tappedOn.setImageResource(R.drawable.blue);
                ++turn;
            } else if (turn % 2 == 1 && turn < 9) {
                tappedOn.setImageResource(R.drawable.red);
                ++turn;
            }

            tappedOn.animate().translationYBy(1000f).rotation(360f).setDuration(500);

            for (int[] winningPos : winState) {
                // When game is won by any one color
                if (gameState[winningPos[0]] == gameState[winningPos[1]] &&
                        gameState[winningPos[1]] == gameState[winningPos[2]] &&
                        gameState[winningPos[0]] != 2) {
                    System.out.println(gameState[winningPos[0]]);

                    if (gameState[winningPos[0]] == 0) {
                        // BLUE won
                        Toast.makeText(MainActivity.this, "Thanks For Using...", Toast.LENGTH_SHORT).show();
                        gameEndText.setText("BLUE WON !!!");
                        gameEnd.setBackgroundColor(Color.BLUE);
                        gameEnd.setVisibility(View.VISIBLE);

                    } else if (gameState[winningPos[0]] == 1) {
                        // RED won
                        Toast.makeText(MainActivity.this, "Thanks For Using...", Toast.LENGTH_SHORT).show();
                        gameEndText.setText("RED WON !!!");
                        gameEnd.setBackgroundColor(Color.RED);
                        gameEnd.setVisibility(View.VISIBLE);
                    }
                    gameWon = true;
                }
            }

            if (turn > 8 && !gameWon) {
//                 Game is draw
                Toast.makeText(MainActivity.this, "Thanks For Using...", Toast.LENGTH_SHORT).show();
                gameWon = true;
                gameEndText.setText("DRAW !!!");
                gameEnd.setBackgroundColor(Color.YELLOW);
                gameEnd.setVisibility(View.VISIBLE);
            }
        }
    }

    public void reset(View view){
        LinearLayout gameEnd = (LinearLayout) findViewById(R.id.linearLayout);
        Button button = (Button)findViewById(R.id.button);

        turn = 0;
        gameWon = false;
        for(int i = 0; i<gameState.length ; ++i){
            gameState[i] = 2;
        }
        androidx.gridlayout.widget.GridLayout grid = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i<9; ++i) {
            ImageView imageView = (ImageView) grid.getChildAt(i);
            imageView.setImageResource(0);
        }
        gameEnd.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}