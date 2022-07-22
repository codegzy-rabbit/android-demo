package com.example.fnst.myapplication;


import android.bluetooth.BluetoothAdapter;

/**
 * double check single instance
 * mainly to get this class object
 *
 * @author fnst
 */
public class BluetoothMain {

    private volatile static BluetoothMain instance = null;
    private BluetoothAdapter mBluetoothAdapter;

    private BluetoothMain(){
        mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();

    }

    public static BluetoothMain getInstance( ) {

        if (instance == null) {
            synchronized (BluetoothMain.class) {
                if (instance == null) {
                    instance = new BluetoothMain();
                }
            }
        }
        return instance;
    }


    public BluetoothAdapter getmBluetoothAdapter() {
        return mBluetoothAdapter;
    }
}
