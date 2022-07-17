package top.codegzy.guessnumbergaming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart;
    private RadioButton two;
    private RadioButton three;
    private RadioButton four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonStart);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                if (!two.isChecked() && !three.isChecked() && !four.isChecked()){
                    Snackbar.make(view, "Please choose a number of digits", Snackbar.LENGTH_LONG).show();
                }else {
                    if (two.isChecked()){
                        intent.putExtra("two", true);
                    }
                    if (three.isChecked()){
                        intent.putExtra("three", true);
                    }
                    if (four.isChecked()){
                        intent.putExtra("four", true);
                    }
                    startActivity(intent);
                }
            }
        });

    }
}