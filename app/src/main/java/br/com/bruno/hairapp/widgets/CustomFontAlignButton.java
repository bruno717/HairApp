package br.com.bruno.hairapp.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

import br.com.bruno.hairapp.utils.FontManager;

/**
 * Created by eric on 11/18/14.
 */

public class CustomFontAlignButton extends Button {

    private static final Integer PADDING = 20;

    private Drawable mTopDrawable;
    private int mIntrinsicWidth;
    private int mIntrinsicHeight;

    public CustomFontAlignButton(Context context) {
        super(context);

        setTypeface(context, null);
    }

    public CustomFontAlignButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        setTypeface(context, attrs);
    }

    public CustomFontAlignButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setTypeface(context, attrs);
    }

    private void setTypeface(Context context, AttributeSet attrs) {
        if(!isInEditMode()) {
            FontManager.setTypeFace(this, context, attrs);
        }
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        if(top == null) return;
        mIntrinsicWidth = top.getIntrinsicWidth();
        mIntrinsicHeight = top.getIntrinsicHeight();

        mTopDrawable = top;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int[] state = getDrawableState();
        mTopDrawable.setState(state);
        mTopDrawable.setBounds(0, 0, mIntrinsicWidth, mIntrinsicHeight);

        canvas.save();
        canvas.translate(0, mIntrinsicHeight / 2);
        super.onDraw(canvas);
        canvas.restore();
        canvas.save();
        canvas.translate(getWidth() / 2 - mIntrinsicWidth / 2, (getHeight() - mIntrinsicHeight) / 2 - PADDING);
        mTopDrawable.draw(canvas);
        canvas.restore();
    }
}
