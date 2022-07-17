package top.codegzy.broadcastreceiverexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastExample extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean state = intent.getBooleanExtra("state", false);
        if (state){
            Toast.makeText(context, "已开启飞行模式", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "已关闭飞行模式", Toast.LENGTH_SHORT).show();
        }
    }
}
