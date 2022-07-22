package com.example.fnst.myapplication;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;


/**
 * @author fnst
 */
public class MainActivity extends AppCompatActivity {

    /**
     * define components
     */
    private Button sendMessage;
    private BluetoothAdapter mBluetoothAdapter;
    private String mBluetoothDeviceName;
    private String mBluetoothAddress;
    private ArrayList<BluetoothDevice> mBluetoothDevices;
    private RecyclerView recyclerView;
    private String tmpBluetoothDeviceName ="74995588";


    /**
     * define intentFilter
     *
     * @return a new Filter
     */
    private IntentFilter makeFilter() {
        IntentFilter filter = new IntentFilter();
        //discover is finished
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        // found devices
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        // bond state change
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        // state change
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        // discover start
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        return filter;
    }

    /**
     * app init method
     */
    private void appInit(){
        //init array list
        mBluetoothDevices = new ArrayList<>();
        //init bluetooth adapter
        mBluetoothAdapter = BluetoothMain.getInstance().getmBluetoothAdapter();
        // send message button
        sendMessage= findViewById(R.id.sendMessage);


        //register receiver by intent filter
        registerReceiver(mReceiver,makeFilter());


         //if sdk version higher 6 then request permission
        if (Build.VERSION.SDK_INT >= Constants.SDK_VERSION) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION},Constants.REQUESTCODE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialization
        appInit();


        sendMessage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                // if mBluetoothAddress is null, prompt set bluetooth message
                if (mBluetoothAddress == null){
                    Constants.toastShort(getApplicationContext(), Constants.SET_BLUETOOTHADDRESS);
                    return;
                }

                Intent i = new Intent(MainActivity.this, MainActivity2.class);

                // pass mBluetoothAddress
                i.putExtra("BluetoothAddress",mBluetoothAddress);

                // start up activity
                startActivity(i);
            }
        });

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        //unregister
        unregisterReceiver(mReceiver);
        Log.e(Constants.DESTROY_TAG,Constants.UNREGISTER);
    }

    /**
     * define a BroadcastReceiver
     */
    private BroadcastReceiver mReceiver=new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {

            //get action type
            String action=intent.getAction();

            // get devices that have been searched
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                // fill the mBluetoothDevices array
                mBluetoothDevices.add(device);
                // refresh the mBluetoothDevices array to display
                openBlueTooth();
                // if device is bonded
                if(device.getBondState() == BluetoothDevice.BOND_BONDED) {

                    Log.i("broadcast", "sch onReceive:BOND_BONDED= "+ device.getName());

                    //  verify the name of device is not null and contains the tmpBluetoothDeviceName
                    if (Objects.nonNull(device.getName()) && device.getName().contains(tmpBluetoothDeviceName)){
                        // get device address
                        mBluetoothAddress = device.getAddress();
                        //get device name
                        mBluetoothDeviceName = device.getName();

                        // define intent
                        Intent i = new Intent(MainActivity.this, MainActivity2.class);
                        // pass bluetooth address information
                        i.putExtra("BluetoothAddress",mBluetoothAddress);
                        // start up
                        startActivity(i);

                        Log.i("broadcast", "sch target device:BOND_BONDED= "+ device.getName());
                        return;
                    }


                    // if device is not bond
                }else if(device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    Log.i("broadcast", "sch onReceive:not BOND_BONDED= "+ device.getName());

                    if (Objects.nonNull(device.getName()) && device.getName().contains(tmpBluetoothDeviceName)){
                        // get device address
                        mBluetoothAddress = device.getAddress();
                        //get device name
                        mBluetoothDeviceName = device.getName();

                        // define intent
                        Intent i = new Intent(MainActivity.this, MainActivity2.class);
                        // pass bluetooth address information
                        i.putExtra("BluetoothAddress",mBluetoothAddress);
                        // start up
                        startActivity(i);


                        Log.i("broadcast", "sch target device:not BOND_BONDED= "+ device.getName());
                        return;
                    }

                }

                // if bluetooth adapter the discover action is finished
            }else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                // the mBluetoothAddress and mBluetoothDeviceName is not null
                Log.i("SIZE", "onReceive: " + mBluetoothDevices.size());
                Log.i("Devices", "onReceive: " + mBluetoothDevices);

                if(Objects.nonNull(mBluetoothAddress) && Objects.nonNull(mBluetoothDeviceName)){
                    //display a message
                    Constants.toastShort(getApplicationContext(), String.format("%s = %s", mBluetoothDeviceName, mBluetoothAddress));
                }else {
                    //display a message
                    Constants.toastShort(getApplicationContext(), "搜索完成，没有找到需要的蓝牙设备");
                }

            }

        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        switch (menuId) {
            case R.id.bluetoothSearch:
                // if the bluetooth function is not open, open it
                if(!mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.enable();
                }
                // start searching for bluetooth devices around you
                mBluetoothAdapter.startDiscovery();
                Constants.toastLong(this, Constants.IS_SEARCHING);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openBlueTooth() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(mBluetoothDevices);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
    }
}

