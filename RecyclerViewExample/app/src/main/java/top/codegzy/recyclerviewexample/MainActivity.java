package top.codegzy.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> countryNames = new ArrayList<>();
    private ArrayList<String> countryDetails = new ArrayList<>();
    private ArrayList<Integer> imageViews = new ArrayList<>();
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 20; i++) {
            countryNames.add("China");


            countryDetails.add("China is a niubi country");


            imageViews.add(R.drawable.girl);
        }


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerAdapter = new RecyclerAdapter(countryNames, countryDetails, imageViews, MainActivity.this);
        recyclerView.setAdapter(recyclerAdapter);

    }
}