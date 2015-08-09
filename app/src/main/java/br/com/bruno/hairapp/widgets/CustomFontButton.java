package br.com.bruno.hairapp.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import br.com.bruno.hairapp.utils.FontManager;


/**
 * Created by marcus.costa on 11/5/14.
 */
public class CustomFontButton extends Button {
    public CustomFontButton(Context context) {
        super(context);

        setTypeface(context, null);
    }

    public CustomFontButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        setTypeface(context, attrs);
    }

    public CustomFontButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setTypeface(context, attrs);
    }

    private void setTypeface(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            FontManager.setTypeFace(this, context, attrs);
        }
    }
}
