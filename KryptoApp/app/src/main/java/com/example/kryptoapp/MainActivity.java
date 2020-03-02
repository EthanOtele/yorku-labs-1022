package com.example.kryptoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onSave(View v)
    {
        try
        {
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileWriter fw = new FileWriter(file);
            fw.write(((EditText) findViewById(R.id.data)).getText().toString());
            fw.close();
            Toast.makeText(this, "Note Saved", Toast.LENGTH_LONG).show();
        }

        catch (IOException e)
        {
            Toast.makeText(this, "Invalid File Name!", Toast.LENGTH_LONG).show();
        }
    }


    public void onLoad(View v)
    {
        try
        {
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileReader fr = new FileReader(file);
            String show = "";

            for (int c = fr.read(); c != -1; c = fr.read())
            {
                show += (char) c;
            }
            fr.close();
            ((EditText)findViewById(R.id.data)).setText(show);
            Toast.makeText(this, "Note Loaded", Toast.LENGTH_LONG).show();
        }

        catch (IOException e)
        {
            Toast.makeText(this, "File Does not Exist!", Toast.LENGTH_LONG).show();
        }

    }


    public void onEncrypt(View v)
    {
        String k = ((EditText) findViewById(R.id.key)).getText().toString();
        String d = ((EditText) findViewById(R.id.data)).getText().toString();

        try
        {
            if (k.isEmpty() == true)
            {
                throw new RuntimeException("Enter a Key");
            }

            else if(d.isEmpty())
            {
                throw new RuntimeException("Enter some Text!");
            }

            Cipher ck = new Cipher(k);
            String data = ck.encrypt(((EditText) findViewById(R.id.data)).getText().toString());
            ((TextView) findViewById(R.id.data)).setText(data);
        }

        catch (RuntimeException e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public void onDecrypt(View v)
    {
        String k = ((EditText) findViewById(R.id.key)).getText().toString();
        String d = ((EditText) findViewById(R.id.data)).getText().toString();

        try
        {
            if (k.isEmpty() == true)
            {
                throw new RuntimeException("Enter a Key");
            }

            Cipher ck = new Cipher(k);
            String data = ck.decrypt(((EditText) findViewById(R.id.data)).getText().toString());
            ((TextView) findViewById(R.id.data)).setText(data);
        }

        catch (RuntimeException e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }












}
