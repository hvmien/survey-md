package com.example.mienhv1.survey.utils.font;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by Forev on 9/8/2016.
 */
public class FontCache {
    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    public static Typeface getTypeface(String font_name, Context context) {
        Typeface typeface = fontCache.get(font_name);
        String path = "fonts/" + font_name;
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), path);
            fontCache.put(font_name, typeface);
        }

        return typeface;
    }
}
