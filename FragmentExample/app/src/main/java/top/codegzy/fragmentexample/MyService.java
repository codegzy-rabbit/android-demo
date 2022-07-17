package top.codegzy.fragmentexample;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends IntentService {

    public MyService() {
        super("Hello");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("handle", "onHandleIntent: ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("create", "onCreate: ");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d("start", "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        Log.d("Destroy", "onDestroy: ");
    }
}
