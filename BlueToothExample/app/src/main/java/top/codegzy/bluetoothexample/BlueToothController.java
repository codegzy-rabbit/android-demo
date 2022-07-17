package top.codegzy.bluetoothexample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

import java.util.Objects;

public class BlueToothController {

    private BluetoothAdapter mAdapter;

    public BlueToothController() {
        mAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public boolean isSupportBlueTooth() {
        return !Objects.isNull(mAdapter);
    }

    public boolean getBlueToothStatus() {
        assert (mAdapter != null);
        return mAdapter.isEnabled();
    }

    @SuppressLint("MissingPermission")
    public void turnOnBlueTooth(Activity activity, int requestCode) {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);;
        activity.startActivityForResult(intent, requestCode);
    }
}
