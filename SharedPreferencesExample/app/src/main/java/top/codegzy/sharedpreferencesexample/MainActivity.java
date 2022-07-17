package top.codegzy.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView viewName;
    private EditText editText;
    private Button button;
    private SharedPreferences sharedPreferences;
    String name;
    String article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewName = findViewById(R.id.textName);
        editText = findViewById(R.id.editArticle);
        button = findViewById(R.id.save);

        button.setOnClickListener(v -> {
            saveData();
        });
        retrieveData();
    }

    private void saveData() {
        sharedPreferences = getSharedPreferences("saveData", MODE_PRIVATE);
        name = viewName.getText().toString();
        article = editText.getText().toString();
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("name", name);
        edit.putString("article", article);
        edit.commit();
        Toast.makeText(getApplicationContext(), "Data is saved", Toast.LENGTH_SHORT).show();
    }


    protected void retrieveData() {

        sharedPreferences = getSharedPreferences("saveData", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", null);
        String article = sharedPreferences.getString("article", null);
        viewName.setText(name);
        editText.setText(article);

    }
}