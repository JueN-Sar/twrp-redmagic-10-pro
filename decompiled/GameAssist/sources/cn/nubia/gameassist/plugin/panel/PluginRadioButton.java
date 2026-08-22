package cn.nubia.gameassist.plugin.panel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.theme.Theme;
import cn.nubia.gameassist.theme.ThemeController;
import cn.nubia.gameassist.theme.ThemeWidget;

/* loaded from: classes.dex */
public class PluginRadioButton extends RadioButton implements ThemeWidget {
    private static final int SWITCH_CARD = 1;
    private static final int SWITCH_LIST = 0;
    private static final String TAG = "PluginRadioButton";
    private int mBackDrawableId;
    private int mButtonDrawableId;
    private Context mContext;
    private boolean mIsHorizontal;
    private int mMode;
    private int mSwitchType;
    private Theme mTheme;

    public PluginRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMode = -1;
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PluginRadioButton);
        this.mSwitchType = obtainStyledAttributes.getInteger(R.styleable.PluginRadioButton_switchType, 0);
        obtainStyledAttributes.recycle();
    }

    public void b(int i2) {
        if (this.mMode == i2) {
            return;
        }
        d(this.mTheme);
        this.mMode = i2;
    }

    @Override // cn.nubia.gameassist.theme.ThemeWidget
    public void d(Theme theme) {
        int i2;
        int i3;
        this.mTheme = theme;
        if (theme != null) {
            int i4 = this.mSwitchType;
            if (i4 == 0) {
                i2 = theme.e(isChecked() ? 2 : 1, this.mIsHorizontal);
                i3 = this.mTheme.f(this.mIsHorizontal);
            } else if (i4 == 1) {
                i2 = theme.b(isChecked() ? 2 : 1, this.mIsHorizontal);
                i3 = this.mTheme.c(this.mIsHorizontal);
            } else {
                i2 = theme.e(isChecked() ? 2 : 1, this.mIsHorizontal);
                i3 = this.mTheme.f(this.mIsHorizontal);
            }
        } else {
            i2 = 0;
            i3 = 0;
        }
        if (this.mBackDrawableId != i2) {
            setBackground(this.mContext.getResources().getDrawable(i2));
            this.mBackDrawableId = i2;
        }
        if (this.mButtonDrawableId != i3) {
            setCompoundDrawablesRelativeWithIntrinsicBounds(this.mContext.getResources().getDrawable(i3), (Drawable) null, (Drawable) null, (Drawable) null);
            this.mButtonDrawableId = i3;
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ThemeController.m().h(this);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mMode = -1;
        ThemeController.m().p(this);
    }

    public void setIsHorizontal(boolean z) {
        this.mIsHorizontal = z;
    }

    public PluginRadioButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMode = -1;
        a(context, attributeSet);
    }

    public PluginRadioButton(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mMode = -1;
        a(context, attributeSet);
    }
}
