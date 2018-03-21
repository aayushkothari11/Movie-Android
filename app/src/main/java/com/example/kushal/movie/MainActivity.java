package com.example.kushal.movie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigbangbutton.editcodeview.EditCodeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> movies=new ArrayList<>();
    ArrayList<String> actors=new ArrayList<>();
    ArrayList<String> releaseYear=new ArrayList<>();
    ArrayList<String> genre=new ArrayList<>();
    ArrayList<String> hints=new ArrayList<>();
    HashMap<String,ArrayList<String>> movie=new HashMap<>();
    TextView display,hint;
    EditText input;
    StackedLayout stackedLayout,displayLayout;
    String randomMovie;
    char temp[];
    ArrayList<Character> used;
    int hintNo=0;
    String HOLLYWOOD="HOLLYWOOD";
    Stack<Character> life=new Stack<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display=(TextView)findViewById(R.id.display);
        hint=(TextView)findViewById(R.id.hint);
        stackedLayout=new StackedLayout(this);
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.life);
        linearLayout.addView(stackedLayout);
        used=new ArrayList<>();
        //LinearLayout display=(LinearLayout)findViewById(R.id.display);
       // display.addView(displayLayout);

        Intent intent=getIntent();
        try {
            JSONObject movieJson=new JSONObject(loadJSONFromAsset());
            JSONArray movieArray=movieJson.getJSONArray("movies");
            for(int i=0;i<movieArray.length();i++)
            {
                JSONObject obj=movieArray.getJSONObject(i);
                String movieName=obj.getString("title");
                String release=obj.getString("year");
                String cast=obj.getString("cast");
                String movieGenre=obj.getString("genre");
                movies.add(movieName);
                actors.add(cast);
                releaseYear.add(release);
                genre.add(movieGenre);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=0;i<movies.size();i++)
       {
           movie.put(movies.get(i),new ArrayList<String>());
           movie.get(movies.get(i)).add(actors.get(i));
           movie.get(movies.get(i)).add(releaseYear.get(i));
           movie.get(movies.get(i)).add(genre.get(i));

       }
        Log.i("move",movie.toString());

    }
    public String loadJSONFromAsset()
    {
        String json=null;
        try {
            InputStream is=getAssets().open("movies.json");
            int size=is.available();
            byte[] buffer=new byte[size];
            is.read(buffer);
            is.close();
            json=new String(buffer,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
    public void start(View view)
    {
        Log.i("tag",movie.toString()+"");
        display.setText("");

        hint.setText("");
        hintNo=0;
        while(!stackedLayout.empty())
        {
            stackedLayout.pop();

        }
        used.clear();

        for(int i=0;i<HOLLYWOOD.length();i++)
        {
            Log.i("k",HOLLYWOOD.charAt(i)+"");
            stackedLayout.push(new LetterTile(this,HOLLYWOOD.charAt(i)));
        }





        input=(EditText)findViewById(R.id.input);
        Random random=new Random();

        randomMovie=movies.get(random.nextInt(movies.size()));
        Log.i("tag",randomMovie);
        char randomMovieArr[]=randomMovie.toCharArray();
         temp=new char[randomMovie.length()];
        Arrays.fill(temp,'#');

        Log.i("",temp+" "+randomMovie);
        for(int i=0;i<temp.length;i++)
        {
           // displayLayout.push(new LetterTile(this,temp[i]));
         display.append(temp[i]+"");
        }


    }


    public void hint(View view)
    {

        ArrayList<String> hints=movie.get(randomMovie);

        char check[]=display.getText().toString().toCharArray();
        int c=0;
        Log.i("ayush",display.getText().toString());
        Log.i("ayush",check.length+"");

        for(int i=0;i<check.length;i++)
        {   Log.i("check",check[i]+" ");
            if(check[i]!='#')
            {
                c++;
            }
        }

        Log.i("tag",c+""+randomMovie.length());
        if(hintNo<3) {
            if (c <=randomMovie.length()/3&& hintNo==0) {
                hint.append(hints.get(hintNo)+"\n");
                hintNo++;
            } else if (c >= (randomMovie.length() / 3 )&&(hintNo ==1)&& (c <= (randomMovie.length() / 2))) {
                Log.i("",c+"");
                hint.append(hints.get(hintNo)+"\n");
                hintNo++;

            } else if(c>=randomMovie.length()/2){
                hint.append(hints.get(hintNo)+"\n");
                hintNo++;
            }
        }
        else
        {
            Toast.makeText(this, "YOU HAVE USED ALL YOUR HINTS", Toast.LENGTH_LONG).show();
        }

    }
    public void check(View view)
    {


        if(stackedLayout.empty())
        {
            Toast.makeText(this, "YOU LOST!!!", Toast.LENGTH_SHORT).show();

            //display.setText(randomMovie);
        }

        if(!display.getText().toString().contains("#"))
        {
            if(display.getText().toString().equals(randomMovie))
            {
                Toast.makeText(this, "YOU WON!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "TRY AGAIN!", Toast.LENGTH_SHORT).show();
            }
        }
        input=(EditText)findViewById(R.id.input);

        if(input.getText().toString().length()>1)
        {
            Toast.makeText(this, "PLEASE ENTER ONLY ONE LETTER", Toast.LENGTH_SHORT).show();
            input.setText("");
        }
        else if(!input.getText().toString().equals("")){
            char ch = input.getText().toString().charAt(0);
            if(!used.contains(ch)) {
                used.add(ch);

                if (!randomMovie.contains(ch + "")) {
                    stackedLayout.pop();
                    input.setText("");

                } else {
                    for (int i = 0; i < randomMovie.length(); i++) {
                        if (randomMovie.charAt(i) == ch) {
                            temp[i] = ch;
                        }
                        input.setText("");
                    }
                    display.setText("");

                    for (int i = 0; i < temp.length; i++) {

                        display.append(temp[i] + "");
                    }
                }
            }
            else
            {
                Toast.makeText(this, "ULLU BANAYEGA KYA!", Toast.LENGTH_SHORT).show();
                input.setText("");
            }
        }
    }


}


