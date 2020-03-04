package com.example.kryptoapp;



public class Cipher {
    // Provided by the Implementation section of the Lab
    // Input variable key
    // If your code prints out B's where a whitespace should be, place a whitespace in front of the ALPHABET
    private String key;
    public static final String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Default constructor
    public Cipher(String k)
    {
            this.key = k;
    }

    // Provided by the textbook
    // Responsible for assigning each character in String note with a value
    public String makePad(String note)
    {
        String pad;
        for (pad = this.key; pad.length() < note.length(); pad += this.key);
        return pad;
    }

    // Provided by the textbook
    public String encrypt(String note)
    {
        String pad = makePad(note);
        String result = "";
        // For loop that iterates over each character in String note.
        // position variable grabs the characters index value from the alphabet
        // shift variable is how much the character will move forward within the alphabet, uses the value assigned in makePad
        // newPosition variable is the new index of the original character
        // result variable uses the new index to grab a character from alphabet and adds it to the result string
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

    // Same code as encrypt, but it is used to decrypt an encrypted string
    public String decrypt(String note)
    {
        String pad = makePad(note);
        String result = "";
        for (int i = 0; i < note.length(); i++)
        // For loop that iterates over each character in String note.
        // position variable grabs the characters index value from the alphabet
        // shift variable is how much the character will move backward within the alphabet, uses the value assigned in makePad
        // newPosition variable is the new index of the original character, Math.abs is used to prevent negatives, will crash if you don't use it
        // result variable uses the new index to grab a character from alphabet and adds it to the result string
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
