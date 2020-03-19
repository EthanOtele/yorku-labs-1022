package com.example.capsapp;

import java.util.List;
import java.util.Map;
import ca.roumani.i2c.Country;
import ca.roumani.i2c.CountryDB;

public class Game
{
    private CountryDB db;
    public Game()
    {
        this.db = new CountryDB();
    }


    public String qa()
    {
        List<String> CapList = db.getCapitals();
        int index = (int) (CapList.size() * Math.random());
        String c = CapList.get(index);
        Map<String, Country> DataList = db.getData();
        Country ref = DataList.get(c);
        String solution = "";


        if (Math.random() < 0.5)
        {
            solution = "What is the Capital of " + ref.getName() + "\n" + ref.getCapital();
        }
        else
        {
            solution = ref.getCapital() + " is the capital of?" + "\n" + ref.getName();
        }

        return solution;
    }


}