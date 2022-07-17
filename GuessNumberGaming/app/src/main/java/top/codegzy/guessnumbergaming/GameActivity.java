package top.codegzy.guessnumbergaming;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView textViewRight, textViewLast, textViewHint;
    Button buttonConfirm;
    EditText editTextGuess;
    boolean twoDigits, threeDigits, fourDigits;
    Random random = new Random();
    int remainingRight = 10;
    int r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewRight = findViewById(R.id.textViewRight);
        textViewLast = findViewById(R.id.textViewLast);
        textViewHint = findViewById(R.id.textViewHint);

        buttonConfirm = findViewById(R.id.buttonConfirm);
        editTextGuess = findViewById(R.id.editTextGuess);

        twoDigits = getIntent().getBooleanExtra("two", false);
        threeDigits = getIntent().getBooleanExtra("three", false);
        fourDigits = getIntent().getBooleanExtra("four", false);

        if (twoDigits){
            r = random.nextInt(90) + 10;
        }
        if (threeDigits){
            r = random.nextInt(900) + 100;
        }
        if (fourDigits){
            r = random.nextInt(9000) + 1000;
        }

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userGuess = editTextGuess.getText().toString();
                if ("".equals(userGuess)){
                    Toast.makeText(GameActivity.this, "Please enter a number", Toast.LENGTH_LONG).show();
                }else {
                    textViewHint.setVisibility(View.VISIBLE);
                    textViewRight.setVisibility(View.VISIBLE);
                    textViewLast.setVisibility(View.VISIBLE);
                    remainingRight--;
                    int guess = Integer.parseInt(userGuess);
                    textViewRight.setText(remainingRight);
                    textViewLast.setText(guess);
                    if (r == guess){
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(GameActivity.this);
                        alertDialog.setTitle("Number Guess Game");
                        alertDialog.setCancelable(false);
                        alertDialog.setMessage("Congratulation!!!\n" + "Would try again?\n");
                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        alertDialog.create().show();
                    }
                    if (r > guess) {
                        textViewHint.setText("too smaller");
                    }
                    if (r < guess){
                        textViewHint.setText("too biger");
                    }
                    if (remainingRight == 0) {
                        Toast.makeText(GameActivity.this, "Game Over", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}