package com.example.kushal.movie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }
    public void play(View view)
    {
        Intent in=new Intent(this,MainActivity.class);
        startActivity(in);
    }
    public void rules(View view)
    {
        Intent in =new Intent(this,Main2Activity.class);
        startActivity(in);
    }
}
