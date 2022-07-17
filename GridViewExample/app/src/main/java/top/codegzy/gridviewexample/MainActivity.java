package top.codegzy.gridviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<String> text = new ArrayList<>();
    private ArrayList<Integer> image = new ArrayList<>();
    private GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fill();
        gridView = findViewById(R.id.gridView);
        gridAdapter = new GridAdapter(text, image, MainActivity.this);
        gridView.setAdapter(gridAdapter);

    }

    private void fill() {
        for (int i = 0; i < 9; i++) {
            text.add("Girl");
            image.add(R.drawable.girl);
        }
    }
}