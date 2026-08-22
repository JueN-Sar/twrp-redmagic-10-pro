package com.google.android.material.snackbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.google.android.material.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class Snackbar extends BaseTransientBottomBar<Snackbar> {
    private static final int[] G;
    private static final int[] H;

    public static class Callback extends BaseTransientBottomBar.BaseCallback<Snackbar> {
        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void a(Snackbar snackbar, int i2) {
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(Snackbar snackbar) {
        }
    }

    @RestrictTo
    public static final class SnackbarLayout extends BaseTransientBottomBar.SnackbarBaseLayout {
        public SnackbarLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.widget.FrameLayout, android.view.View
        protected void onMeasure(int i2, int i3) {
            super.onMeasure(i2, i3);
            int childCount = getChildCount();
            int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getLayoutParams().width == -1) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), View.MeasureSpec.makeMeasureSpec(childAt.getMeasuredHeight(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME));
                }
            }
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setBackground(@Nullable Drawable drawable) {
            super.setBackground(drawable);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setBackgroundDrawable(@Nullable Drawable drawable) {
            super.setBackgroundDrawable(drawable);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setBackgroundTintList(@Nullable ColorStateList colorStateList) {
            super.setBackgroundTintList(colorStateList);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
            super.setBackgroundTintMode(mode);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
            super.setLayoutParams(layoutParams);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
            super.setOnClickListener(onClickListener);
        }
    }

    static {
        int i2 = R.attr.snackbarButtonStyle;
        G = new int[]{i2};
        H = new int[]{i2, R.attr.snackbarTextViewStyle};
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public void z() {
        super.z();
    }
}
