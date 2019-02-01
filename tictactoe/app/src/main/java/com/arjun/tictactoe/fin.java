package com.arjun.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class fin extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin);

        String xvals = getIntent().getStringExtra("Player1");
        String yvals = getIntent().getStringExtra("Player2");

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer1.setText(xvals);
        textViewPlayer2 = findViewById(R.id.text_view_p2);
        textViewPlayer2.setText(yvals);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xvals = getIntent().getStringExtra("Player1");
                String yvals = getIntent().getStringExtra("Player2");
                resetGame(xvals,yvals);
            }
        });
    }

    @Override
    public void onClick(View v) {

        String xvals = getIntent().getStringExtra("Player1");
        String yvals = getIntent().getStringExtra("Player2");
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins(xvals,yvals);
            } else {
                player2Wins(xvals,yvals);
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins(String xvals,String yvals) {
        player1Points++;
        Toast.makeText(this, xvals+" wins!", Toast.LENGTH_SHORT).show();
        updatePointsText(xvals,yvals);
        resetBoard();
    }

    private void player2Wins(String xvals,String yvals) {
        player2Points++;
        Toast.makeText(this, yvals+" wins!", Toast.LENGTH_SHORT).show();
        updatePointsText(xvals,yvals);
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText(String xvals,String yvals) {
        textViewPlayer1.setText(xvals + player1Points);
        textViewPlayer2.setText(yvals + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame(String xvals,String yvals) {
        player1Points = 0;
        player2Points = 0;
        updatePointsText(xvals,yvals);
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String xvals = getIntent().getStringExtra("Player1");
        String yvals = getIntent().getStringExtra("Player2");
        outState.putInt("roundCount", roundCount);
        outState.putInt(xvals, player1Points);
        outState.putInt(yvals, player2Points);
        outState.putBoolean(xvals, player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}








