package com.example.kushal.movie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView rules=(TextView)findViewById(R.id.textView2);
        String rulesString="\n"+"\n"+"1.You have 3 hints . First hint(cast) will be given at the start of the game."+"\n"+"\n"+
                "2.You can use second hint(release year) only after guessing one-third of movie correctly."+"\n"+"\n"
                +"3.And the third hint(genre) after guessing half of the movie correctly"+"\n"+"\n"+
                "4.If you feel the movie has multiple words press space bar since its a valid character"+"\n"+"\n"+
                "5.Use only lowercase letters to guess the movie."+"\n"+
                "Guess correctly before the HOLLYWOOD block finishes.";
        rules.setText(rulesString);

        Intent in =getIntent();
    }
    public void play(View view)
    {
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("Start",1);
        startActivity(intent);
    }
}
