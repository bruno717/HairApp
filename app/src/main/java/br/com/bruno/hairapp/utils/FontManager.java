package br.com.bruno.hairapp.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.TextView;

import br.com.bruno.hairapp.R;


/**
 * Created by marcus.costa on 11/4/14.
 */
public class FontManager {

    private final static SparseArray<android.graphics.Typeface> mTypefaces = new SparseArray<android.graphics.Typeface>(6);

    public static void setTypeFace(TextView textView, Context context, AttributeSet attrs) {
        android.graphics.Typeface typeface;

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomFontTextView);

            if (a.hasValue(R.styleable.CustomFontTextView_app_typeface)) {
                int typefaceValue = a.getInt(R.styleable.CustomFontTextView_app_typeface, Typeface.HELVETICANEUE_CN);
                typeface = obtainTypeface(context, typefaceValue);

                a.recycle();
            } else {
                typeface = obtainTypeface(context, Typeface.HELVETICANEUE_CN);
            }

            setTypeface(textView, typeface);
        }
    }

    private static android.graphics.Typeface obtainTypeface(Context context, int typefaceValue) {
        android.graphics.Typeface typeface = mTypefaces.get(typefaceValue);
        if (typeface == null) {
            typeface = createTypeface(context, typefaceValue);
            mTypefaces.put(typefaceValue, typeface);
        }
        return typeface;
    }

    private static android.graphics.Typeface createTypeface(Context context, int typefaceValue) throws IllegalArgumentException {
        String typefacePath;
        switch (typefaceValue) {

            case Typeface.HELVETICANEUE_BDCN:
                typefacePath = "fonts/HelveticaNeueLTStd-BdCn.otf";
                break;

            case Typeface.HELVETICANEUE_MDCN:
                typefacePath = "fonts/HelveticaNeueLTStd-MdCn.otf";
                break;

            case Typeface.HELVETICANEUE_LT:
                typefacePath = "fonts/HelveticaNeueLTStd-Lt.otf";
                break;

            case Typeface.HELVETICANEUE_LTCN:
                typefacePath = "fonts/HelveticaNeueLTStd-LtCn.otf";
                break;

            case Typeface.HELVETICANEUE_CN:
                typefacePath = "fonts/HelveticaNeueLTStd-Cn.otf";
                break;


            default:
                throw new IllegalArgumentException("Unknown `typeface` attribute value " + typefaceValue);
        }

        return android.graphics.Typeface.createFromAsset(context.getAssets(), typefacePath);
    }

    public static void setTypeface(TextView textView, android.graphics.Typeface typeface) {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        textView.setTypeface(typeface);
    }

    public static void setTypeface(Paint paint, android.graphics.Typeface typeface) {
        paint.setFlags(paint.getFlags() | Paint.SUBPIXEL_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        paint.setTypeface(typeface);
    }

    public class Typeface {
        public final static int HELVETICANEUE_BDCN = 0;
        public final static int HELVETICANEUE_MDCN = 1;
        public final static int HELVETICANEUE_LT = 2;
        public final static int HELVETICANEUE_LTCN = 3;
        public final static int HELVETICANEUE_CN = 4;
    }
}


