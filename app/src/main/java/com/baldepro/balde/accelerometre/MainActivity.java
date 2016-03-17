package com.baldepro.balde.accelerometre;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{
    private SensorManager sensorManager;
    private Sensor accelerometre;
    private TextView lx;
    private TextView ly;
    private TextView lz;
    private LinearLayout layout;
    private float vx,vy, vz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        vx = 0;
        vy = 0;
        vz = 0;
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        lx = new TextView(this);
        //lx.setText(String.valueOf(vx));
        ly = new TextView(this);
        lz = new TextView(this);
        layout.addView(lx);
        layout.addView(ly);
        layout.addView(lz);
        //lx.setText(String.valueOf(vx));
        //ly.setText(String.valueOf(vy));
        //lz.setText(String.valueOf(vz));
        setContentView(layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this,accelerometre);
    }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, accelerometre, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        // nous voici on est à charbonner !!
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //float vX, vY, vZ;
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
          //  vX = event.values[0];
            //String s = String.valueOf(vX);
            //CharSequence cs = s;
            this.lx.setText("X : "+String.valueOf(event.values[0]));
            this.ly.setText("Y : "+String.valueOf(event.values[1]));
            this.lz.setText("Z : "+String.valueOf(event.values[2]));
            //ly.setText("waww");
            //lz.setText("bordel");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
