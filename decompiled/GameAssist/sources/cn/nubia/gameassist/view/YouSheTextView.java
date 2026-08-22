package cn.nubia.gameassist.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.nubia.gameassist.utils.Utils;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes.dex */
public class YouSheTextView extends TextView {
    private static Typeface mYouSheTypeface;

    public YouSheTextView(Context context) {
        this(context, null);
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

    public YouSheTextView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public YouSheTextView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }
}
