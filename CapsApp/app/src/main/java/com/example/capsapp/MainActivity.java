package com.example.capsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{
    private Game game;
    private String question;
    private String answer;
    private int score ;
    private int qNum ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.game = new Game();
        game.qa();

        this.question = (findViewById(R.id.question)).toString();
        this.answer =  (findViewById(R.id.answer)).toString();
        this.score = 0;
        this.qNum = 1;
        ask();
    }


    public void onDone (View v)
    {
        String LogUpdater ="";
        if (qNum == 10) finish();
        String UserInput = ((EditText)findViewById(R.id.answer)).getText().toString();

        if (UserInput.equals(answer)) score++;

        if (qNum < 10)
        {
            String LogQuestion = ((TextView)findViewById(R.id.qNum)).getText().toString() + ":" + question +"\n";
            String LogUserAnswer = "Your Answer:" + UserInput.toUpperCase();
            String LogRightAnswer = "Correct Answer:" + answer;
            LogUpdater +=  LogQuestion + "\n" + LogUserAnswer + "\n" + LogRightAnswer + "\n\n";
            ((TextView) findViewById(R.id.log)).append(LogUpdater);
        }
        qNum++;
        ask();
    }



    private void ask()
    {
        Game game = new Game();
        String s = game.qa();
        question = (s.substring(0, s.lastIndexOf("\n")));
        answer = s.substring(s.lastIndexOf("\n")+1);

        ((TextView) findViewById(R.id.score)).setText("SCORE: " + score);
        ((TextView) findViewById(R.id.qNum)).setText("Q#" + qNum );
        ((TextView) findViewById(R.id.question)).setText(question);
    }
}
