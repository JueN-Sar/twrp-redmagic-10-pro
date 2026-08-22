package com.google.android.material.snackbar;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MotionUtils;

@RestrictTo
/* loaded from: classes.dex */
public class SnackbarContentLayout extends LinearLayout implements ContentViewCallback {
    private Button actionView;
    private final TimeInterpolator contentInterpolator;
    private int maxInlineActionWidth;
    private TextView messageView;

    public SnackbarContentLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.contentInterpolator = MotionUtils.g(context, R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.f13815b);
    }

    private static void c(View view, int i2, int i3) {
        if (ViewCompat.P(view)) {
            ViewCompat.y0(view, ViewCompat.z(view), i2, ViewCompat.y(view), i3);
        } else {
            view.setPadding(view.getPaddingLeft(), i2, view.getPaddingRight(), i3);
        }
    }

    private boolean d(int i2, int i3, int i4) {
        boolean z;
        if (i2 != getOrientation()) {
            setOrientation(i2);
            z = true;
        } else {
            z = false;
        }
        if (this.messageView.getPaddingTop() == i3 && this.messageView.getPaddingBottom() == i4) {
            return z;
        }
        c(this.messageView, i3, i4);
        return true;
    }

    @Override // com.google.android.material.snackbar.ContentViewCallback
    public void a(int i2, int i3) {
        this.messageView.setAlpha(0.0f);
        long j2 = i3;
        long j3 = i2;
        this.messageView.animate().alpha(1.0f).setDuration(j2).setInterpolator(this.contentInterpolator).setStartDelay(j3).start();
        if (this.actionView.getVisibility() == 0) {
            this.actionView.setAlpha(0.0f);
            this.actionView.animate().alpha(1.0f).setDuration(j2).setInterpolator(this.contentInterpolator).setStartDelay(j3).start();
        }
    }

    @Override // com.google.android.material.snackbar.ContentViewCallback
    public void b(int i2, int i3) {
        this.messageView.setAlpha(1.0f);
        long j2 = i3;
        long j3 = i2;
        this.messageView.animate().alpha(0.0f).setDuration(j2).setInterpolator(this.contentInterpolator).setStartDelay(j3).start();
        if (this.actionView.getVisibility() == 0) {
            this.actionView.setAlpha(1.0f);
            this.actionView.animate().alpha(0.0f).setDuration(j2).setInterpolator(this.contentInterpolator).setStartDelay(j3).start();
        }
    }

    public Button getActionView() {
        return this.actionView;
    }

    public TextView getMessageView() {
        return this.messageView;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.messageView = (TextView) findViewById(R.id.snackbar_text);
        this.actionView = (Button) findViewById(R.id.snackbar_action);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (getOrientation() == 1) {
            return;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical_2lines);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical);
        Layout layout = this.messageView.getLayout();
        boolean z = layout != null && layout.getLineCount() > 1;
        if (!z || this.maxInlineActionWidth <= 0 || this.actionView.getMeasuredWidth() <= this.maxInlineActionWidth) {
            if (!z) {
                dimensionPixelSize = dimensionPixelSize2;
            }
            if (!d(0, dimensionPixelSize, dimensionPixelSize)) {
                return;
            }
        } else if (!d(1, dimensionPixelSize, dimensionPixelSize - dimensionPixelSize2)) {
            return;
        }
        super.onMeasure(i2, i3);
    }

    public void setMaxInlineActionWidth(int i2) {
        this.maxInlineActionWidth = i2;
    }
}
