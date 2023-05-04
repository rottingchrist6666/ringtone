package com.example.aula0305;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
TextView proximitySensor;

@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        proximitySensor = findViewById(R.id.oi);

    SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    if(sensorManager != null){
        Sensor proxysensor = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(proxysensor!=null){
            sensorManager.registerListener(this, proxysensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    else{
        Toast.makeText(this, "sensor não encontrado", Toast.LENGTH_SHORT).show();
    }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        RingtoneManager ringtoneManager = new RingtoneManager(this);
        Ringtone ring = ringtoneManager.getRingtone(0);
if(sensorEvent.sensor.getType()==Sensor.TYPE_PROXIMITY){
    String sensorValue="Proximity Value: "+sensorEvent.values[0];
    proximitySensor.setText(sensorValue);
    if(sensorEvent.values[0]>0){
        Toast.makeText(this, "LONGE", Toast.LENGTH_SHORT).show();
        ring.stop();
    }
    else {
        Toast.makeText(this, "PERTO", Toast.LENGTH_SHORT).show();

        ring.play();
    }
}
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}