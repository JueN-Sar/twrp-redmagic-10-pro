package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.R;
import androidx.core.widget.PopupWindowCompat;

/* loaded from: classes.dex */
class AppCompatPopupWindow extends PopupWindow {

    /* renamed from: b, reason: collision with root package name */
    private static final boolean f785b = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f786a;

    public AppCompatPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        super(context, attributeSet, i2);
        a(context, attributeSet, i2, 0);
    }

    private void a(Context context, AttributeSet attributeSet, int i2, int i3) {
        TintTypedArray v = TintTypedArray.v(context, attributeSet, R.styleable.PopupWindow, i2, i3);
        if (v.s(R.styleable.PopupWindow_overlapAnchor)) {
            b(v.a(R.styleable.PopupWindow_overlapAnchor, false));
        }
        setBackgroundDrawable(v.g(R.styleable.PopupWindow_android_popupBackground));
        v.x();
    }

    private void b(boolean z) {
        if (f785b) {
            this.f786a = z;
        } else {
            PopupWindowCompat.a(this, z);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i2, int i3) {
        if (f785b && this.f786a) {
            i3 -= view.getHeight();
        }
        super.showAsDropDown(view, i2, i3);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i2, int i3, int i4, int i5) {
        if (f785b && this.f786a) {
            i3 -= view.getHeight();
        }
        super.update(view, i2, i3, i4, i5);
    }

    public AppCompatPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        super(context, attributeSet, i2, i3);
        a(context, attributeSet, i2, i3);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i2, int i3, int i4) {
        if (f785b && this.f786a) {
            i3 -= view.getHeight();
        }
        super.showAsDropDown(view, i2, i3, i4);
    }
}
