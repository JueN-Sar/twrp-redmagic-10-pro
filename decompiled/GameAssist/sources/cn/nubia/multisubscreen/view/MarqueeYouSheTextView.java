package cn.nubia.multisubscreen.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.nubia.gameassist.utils.Utils;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes.dex */
public class MarqueeYouSheTextView extends TextView {
    private static Typeface mYouSheTypeface;
    private boolean mIsAttachedToWindow;

    public MarqueeYouSheTextView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void a() {
        setTypeface(getYouSheHei());
    }

    public static Typeface getYouSheHei() {
        if (mYouSheTypeface == null) {
            mYouSheTypeface = Utils.h("YouSheBiaoTiHei-2.ttf");
        }
        return mYouSheTypeface;
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.mIsAttachedToWindow;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsAttachedToWindow = true;
        setFocusable(true);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIsAttachedToWindow = false;
        setFocusable(false);
    }

    public MarqueeYouSheTextView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setSingleLine();
        setMarqueeRepeatLimit(-1);
    }
}
