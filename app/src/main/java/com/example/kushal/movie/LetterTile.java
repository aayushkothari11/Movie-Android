package com.example.kushal.movie;

import android.content.Context;
import android.graphics.Color;

/**
 * Created by kushal on 20-03-2018.
 */

public class LetterTile extends android.support.v7.widget.AppCompatTextView {

    public static final int TILE_SIZE = 70;
    private Character letter;


    public LetterTile(Context context, Character letter) {
        super(context);
        this.letter = letter;
        setText(letter.toString());
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        setHeight(TILE_SIZE);
        setWidth(TILE_SIZE);
        setTextSize(30);
        setBackgroundColor(Color.rgb(155, 155, 155));
    }
}