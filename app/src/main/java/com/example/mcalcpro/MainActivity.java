package com.example.mcalcpro;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import ca.roumani.i2c.MPro;


//import java.util.Locale;
//import android.speech.tts.TextToSpeech;


public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    private SensorManager sensorManager;
    private Sensor mLight;
//    private TextToSpeech tts;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      this.tts = new TextToSpeech(this, this);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }


//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//        sensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
//    }

    public void buttonClicked(View v){

        MPro mp = new MPro();
        try
        {
            mp.setPrinciple(((EditText)findViewById(R.id.pBox)).getText().toString());
            mp.setAmortization(((EditText)findViewById(R.id.aBox)).getText().toString());
            mp.setInterest(((EditText)findViewById(R.id.iBox)).getText().toString());
        }

        catch (RuntimeException ex )
        {
            Toast label = Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT);
            label.show();
        }

        System.out.println(mp.computePayment("%.2f"));
        System.out.println(mp.outstandingAfter(2, "%,16.0f"));

        String s = "Monthly Payment = " + mp.computePayment("%,.2f");


        s += "\n\n";
        s += "By making this payments monthly for " + mp.getAmortization() + " years, ";
        s += "the mortgage will be paid in full. But if" + "you terminate the mortgage on its nth";
        s += " anniversary, the balance still owing depends " + "on n as shown below:";

        for (int i =0; i <=20; i++)
        {

             if (i > 5)
             {
                 i = i + 4;
                 s += "\n\n" + String.format("%d", i ) + mp.outstandingAfter(i, "%16.0f");
             }

             else
                 {
                    s += "\n\n" + String.format("%d", i ) + mp.outstandingAfter(i, "%16.0f");
                 }
        }


        ((TextView) findViewById(R.id.output)).setText(s);
//      tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);

    }

//    public void onInit(int initStatus)
//    {
//        this.tts.setLanguage(Locale.US);
//    }



    public void onAccuracyChanged(Sensor arg0, int arg1)
    {

    }

    public void onSensorChanged(SensorEvent event)
    {
        double ax = event.values[0];
        double ay = event.values[1];
        double az = event.values[2];
        double a = Math.sqrt(ax*ax + ay*ay + az*az);
        if (a > 10)
        {
            ((EditText) findViewById(R.id.pBox)).setText("");
            ((EditText) findViewById(R.id.aBox)).setText("");
            ((EditText) findViewById(R.id.iBox)).setText("");
            ((TextView) findViewById(R.id.output)).setText("");

        }

    }





}
