package com.example.fnst.myapplication;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.BlueToothViewHolder>{

    private ArrayList<BluetoothDevice> bluetoothDevices;

    public RecyclerAdapter(ArrayList<BluetoothDevice> bluetoothDevices) {
        this.bluetoothDevices = bluetoothDevices;
    }

    @NonNull
    @Override
    public BlueToothViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new BlueToothViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlueToothViewHolder blueToothViewHolder, int i) {
        blueToothViewHolder.blueToothName.setText(bluetoothDevices.get(i).getAddress());
        blueToothViewHolder.blueToothImage.setImageResource(R.mipmap.ic_launcher_bt);
    }

    @Override
    public int getItemCount() {
        return bluetoothDevices.size();
    }

    class BlueToothViewHolder extends RecyclerView.ViewHolder {
        private TextView blueToothName;
        private ImageView blueToothImage;
        private CardView blueToothCard;

        public BlueToothViewHolder(@NonNull View itemView) {
            super(itemView);
            blueToothName = itemView.findViewById(R.id.blueToothName);
            blueToothImage = itemView.findViewById(R.id.bluetoothImage);
            blueToothCard = itemView.findViewById(R.id.blueToothCard);
        }
    }
}
