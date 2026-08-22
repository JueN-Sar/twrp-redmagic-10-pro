package com.zte.mifavor.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.annotation.NonNull;

/* loaded from: classes2.dex */
public class SeekBarZTE extends SeekBar {
    private static final int[] STATE_DISABLE = {-16842910};
    private static final int[] STATE_ENABLED_PRESSED = {R.attr.state_enabled, R.attr.state_pressed};
    private static final int[] STATE_ENABLED_UNPRESSED = {R.attr.state_enabled, -16842919};

    @NonNull
    private BitmapDrawable disThumbDrawable;

    @NonNull
    private LayerDrawable mProgressDrawable;

    @NonNull
    private BitmapDrawable thumbPressDrawable;

    @NonNull
    private BitmapDrawable thumbUnPressDrawable;

    public SeekBarZTE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.seekBarStyle);
    }

    public void a(int i2, int i3, int i4) {
        AnimatedStateListDrawable animatedStateListDrawable = new AnimatedStateListDrawable();
        ((DrawableContainer.DrawableContainerState) animatedStateListDrawable.getConstantState()).setConstantSize(true);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(i4);
        this.disThumbDrawable = bitmapDrawable;
        bitmapDrawable.setGravity(17);
        animatedStateListDrawable.addState(STATE_DISABLE, this.disThumbDrawable, 0);
        BitmapDrawable bitmapDrawable2 = (BitmapDrawable) getResources().getDrawable(i2);
        this.thumbPressDrawable = bitmapDrawable2;
        bitmapDrawable2.setGravity(17);
        animatedStateListDrawable.addState(STATE_ENABLED_PRESSED, this.thumbPressDrawable, 0);
        BitmapDrawable bitmapDrawable3 = (BitmapDrawable) getResources().getDrawable(i3);
        this.thumbUnPressDrawable = bitmapDrawable3;
        bitmapDrawable3.setGravity(17);
        animatedStateListDrawable.addState(STATE_ENABLED_UNPRESSED, this.thumbUnPressDrawable, 0);
        setThumb(animatedStateListDrawable);
    }

    public SeekBarZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.thumbPressDrawable = (BitmapDrawable) getResources().getDrawable(com.zte.extres.R.drawable.seekbar_thumb_pressed);
        this.thumbUnPressDrawable = (BitmapDrawable) getResources().getDrawable(com.zte.extres.R.drawable.seekbar_thumb_unpressed);
        this.disThumbDrawable = (BitmapDrawable) getResources().getDrawable(com.zte.extres.R.drawable.progressbar_slider_disabled_light);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.zte.extres.R.styleable.SeekBar);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(com.zte.extres.R.styleable.SeekBar_android_thumbOffset, -1);
        obtainStyledAttributes.recycle();
        boolean z = dimensionPixelSize != -1;
        a(com.zte.extres.R.drawable.seekbar_thumb_pressed, com.zte.extres.R.drawable.seekbar_thumb_unpressed, com.zte.extres.R.drawable.progressbar_slider_disabled_light);
        setProgressDrawable(getResources().getDrawable(com.zte.extres.R.drawable.seekbar_progress_progressdrawable_anim));
        if (getTickMark() == null && !z) {
            setThumbOffset(Math.round((this.thumbPressDrawable.getIntrinsicWidth() - this.thumbUnPressDrawable.getIntrinsicWidth()) / 2.0f));
        }
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
    }
}
