package br.com.bruno.hairapp.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import br.com.bruno.hairapp.utils.FontManager;


/**
 * Created by marcus.costa on 11/4/14.
 */
public class CustomFontTextView extends TextView {

    public CustomFontTextView(Context context) {
        super(context);

        setTypeface(context, null);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setTypeface(context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setTypeface(context, attrs);
    }

    private void setTypeface(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            FontManager.setTypeFace(this, context, attrs);
        }
    }
}
