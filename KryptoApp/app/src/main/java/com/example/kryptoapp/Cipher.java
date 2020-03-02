package com.example.kryptoapp;

import android.widget.Toast;

public class Cipher {

    private String key;
    public static final String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Cipher(String k)
    {
            this.key = k;
    }




    public String makePad(String note)
    {
        String pad;
        for (pad = this.key; pad.length() < note.length(); pad += this.key);
        return pad;
    }



    public String encrypt(String note)
    {
        String pad = makePad(note);
        String result = "";
        for (int i = 0; i < note.length(); i++)
        {
            String c = note.substring(i, i + 1);
            int position = ALPHABET.indexOf(c);
            int shift = Integer.parseInt(pad.substring(i, i + 1));
            int newPosition = (position + shift) % ALPHABET.length();
            result = result + ALPHABET.substring(newPosition, newPosition + 1);
        }

        return result;
    }



    public String decrypt(String note)
    {
        String pad = makePad(note);
        String result = "";
        for (int i = 0; i < note.length(); i++)
        {
            String c = note.substring(i, i + 1);
            int position = ALPHABET.indexOf(c);
            int shift = Integer.parseInt(pad.substring(i, i + 1));
            int newPosition = Math.abs(position - shift) % ALPHABET.length();
            result = result + ALPHABET.substring(newPosition, newPosition + 1);
        }
        return result;

    }
}
