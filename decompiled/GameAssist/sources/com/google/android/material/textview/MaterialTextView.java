package com.google.android.material.textview;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* loaded from: classes.dex */
public class MaterialTextView extends AppCompatTextView {
    public MaterialTextView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    private void r(Resources.Theme theme, int i2) {
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(i2, com.google.android.material.R.styleable.MaterialTextAppearance);
        int v = v(getContext(), obtainStyledAttributes, com.google.android.material.R.styleable.MaterialTextAppearance_android_lineHeight, com.google.android.material.R.styleable.MaterialTextAppearance_lineHeight);
        obtainStyledAttributes.recycle();
        if (v >= 0) {
            setLineHeight(v);
        }
    }

    private static boolean s(Context context) {
        return MaterialAttributes.b(context, com.google.android.material.R.attr.textAppearanceLineHeightEnabled, true);
    }

    private static int t(Resources.Theme theme, AttributeSet attributeSet, int i2, int i3) {
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, com.google.android.material.R.styleable.MaterialTextView, i2, i3);
        int resourceId = obtainStyledAttributes.getResourceId(com.google.android.material.R.styleable.MaterialTextView_android_textAppearance, -1);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    private void u(AttributeSet attributeSet, int i2, int i3) {
        int t;
        Context context = getContext();
        if (s(context)) {
            Resources.Theme theme = context.getTheme();
            if (w(context, theme, attributeSet, i2, i3) || (t = t(theme, attributeSet, i2, i3)) == -1) {
                return;
            }
            r(theme, t);
        }
    }

    private static int v(Context context, TypedArray typedArray, int... iArr) {
        int i2 = -1;
        for (int i3 = 0; i3 < iArr.length && i2 < 0; i3++) {
            i2 = MaterialResources.d(context, typedArray, iArr[i3], -1);
        }
        return i2;
    }

    private static boolean w(Context context, Resources.Theme theme, AttributeSet attributeSet, int i2, int i3) {
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, com.google.android.material.R.styleable.MaterialTextView, i2, i3);
        int v = v(context, obtainStyledAttributes, com.google.android.material.R.styleable.MaterialTextView_android_lineHeight, com.google.android.material.R.styleable.MaterialTextView_lineHeight);
        obtainStyledAttributes.recycle();
        return v != -1;
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView
    public void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        if (s(context)) {
            r(context.getTheme(), i2);
        }
    }

    public MaterialTextView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(MaterialThemeOverlay.c(context, attributeSet, i2, 0), attributeSet, i2);
        u(attributeSet, i2, 0);
    }

    @Deprecated
    public MaterialTextView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(MaterialThemeOverlay.c(context, attributeSet, i2, i3), attributeSet, i2);
        u(attributeSet, i2, i3);
    }
}
