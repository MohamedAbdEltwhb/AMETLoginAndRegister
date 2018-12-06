package com.example.mm.ametloginandregister.Fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

public class TitleFont extends TextView {

    public TitleFont(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "font_a.ttf"));

    }
}
