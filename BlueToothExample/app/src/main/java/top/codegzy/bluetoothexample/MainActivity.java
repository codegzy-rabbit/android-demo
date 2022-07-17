package top.codegzy.bluetoothexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BlueToothController mBlueToothController;
    private Button support;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBlueToothController = new BlueToothController();
        isSupport();
    }

    private void isSupport() {
        support = findViewById(R.id.isSupport);

        support.setOnClickListener(v -> {
            boolean supportBlueTooth = mBlueToothController.isSupportBlueTooth();
            Toast.makeText(this, "The Device is support bluetooth? " + supportBlueTooth, Toast.LENGTH_SHORT).show();
        });
    }
}