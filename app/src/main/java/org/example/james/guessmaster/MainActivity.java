package org.example.james.guessmaster;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void startGame(View v) {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Lets play a game!");
        alertDialogBuilder.setMessage("Think of a number between 1000 and 9999.\n\nClick OK when you are ready...");

        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                gamer.GuessingGame();
                runGame();

            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    GuessingGame gamer = new GuessingGame();
    int count = 0;
    int myGuess = 0;
    int numMatches = 0;

    public void runGame() {


        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        myGuess = gamer.myGuessIs();
        alertDialogBuilder.setTitle("I guess your number is " + myGuess);
        alertDialogBuilder.setMessage("How many digits did I guess correctly?");
        final EditText input = new EditText(this);
        alertDialogBuilder.setView(input);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);

        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

                numMatches = Integer.valueOf(input.getText().toString());

                if (myGuess == -1) {
                    error();
                }

                if (numMatches < 4 && numMatches >= 0) {
                    gamer.updateMyGuess(numMatches);
                    runGame();
                }

                if (numMatches == 4) {
                    count++;
                    win();
                    updateBoard("Game " + count,gamer.totalNumGuesses());

                } 
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void win(){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("I win!");
        if(gamer.totalNumGuesses() == 1){
            alertDialogBuilder.setMessage("Aha! I guessed your number in 1 guess! How lucky?");
        } else {
            alertDialogBuilder.setMessage("Aha! I guessed your number in " + gamer
                    .totalNumGuesses() + " guesses!");
        }
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                gamer.GuessingGame();
            }
        });
        AlertDialog win = alertDialogBuilder.create();
        win.show();
    }
    public void error(){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("I dont think your number exists.\nOr maybe you input the wrong number of matches...\n\n " +
                "Try again?");
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                gamer.GuessingGame();
                runGame();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gamer.GuessingGame();
                dialog.cancel();
            }
        });
        AlertDialog error = alertDialogBuilder.create();
        error.show();
    }

    public void error2(){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Thats not a valid input. Please input a number of matches " +
                "between 0 and 4.");
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                runGame();
            }
        });

        AlertDialog error = alertDialogBuilder.create();
        error.show();
    }
    public void updateBoard(String game, int score) {

        TextView textView = (TextView) findViewById(R.id.textView2);
        String updating  = textView.getText().toString();
        if(score == 1){
            textView.setText(updating + "\n" + game + " completed in 1 guess");
        } else {
            textView.setText(updating + "\n" + game + " completed in " + score + " guesses");
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}

