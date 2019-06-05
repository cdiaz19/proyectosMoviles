package com.example.oscar.lab12smart;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {
    ImageButton transaccionBlue;
    ListView listdevice;
    Vibrator vibrator;
    ArrayList<String> listaBT= new ArrayList<>();
    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        transaccionBlue= (ImageButton) findViewById(R.id.llamarBLUETOOTH);
        listdevice= (ListView)findViewById(R.id.ListView);


        listaBT.add("kevin");
        final ArrayAdapter mArrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaBT);
        transaccionBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                Vibrator vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
                if(!mBluetoothAdapter.isEnabled()){

                    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
// If there are paired devices
                    if (pairedDevices.size() > 0) {
                        // Loop through paired devices
                        for (BluetoothDevice device : pairedDevices) {
//ArrayAdapter<String> mArrayAdapter=ArrayAdapter.createFromResource(this,R.)
                            // Add the name and address to an array adapter to show in a ListView
                            listaBT.add(device.getName() + "\n" + device.getAddress());
                            listdevice.setAdapter((ListAdapter) listaBT);

                        }
                    }
                    Intent discoverableIntent = new
                            Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                    startActivity(discoverableIntent);
                    vibrator.vibrate(100);


                }
                else{
                    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    mBluetoothAdapter.disable();
                    vibrator.vibrate(300);
                }
            }
        });
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                listaBT.add(device.getName() + "\n" + device.getAddress());
                listdevice.setAdapter((ListAdapter) listaBT);
            }
        }
    };

}
