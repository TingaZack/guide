package com.zack.tinga.satvguide.typo;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by admin on 2017/08/16.
 */

public class LightRowTextview extends TextView {

    public LightRowTextview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setType(context);
    }

    public LightRowTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        setType(context);
    }

    public LightRowTextview(Context context) {
        super(context);
        setType(context);
    }

    private void setType(Context context){
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),
                "fonts/GeosansLight.ttf"));
    }

}
