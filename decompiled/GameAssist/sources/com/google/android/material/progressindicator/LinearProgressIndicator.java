package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class LinearProgressIndicator extends BaseProgressIndicator<LinearProgressIndicatorSpec> {
    public static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_LinearProgressIndicator;
    public static final int INDETERMINATE_ANIMATION_TYPE_CONTIGUOUS = 0;
    public static final int INDETERMINATE_ANIMATION_TYPE_DISJOINT = 1;
    public static final int INDICATOR_DIRECTION_END_TO_START = 3;
    public static final int INDICATOR_DIRECTION_LEFT_TO_RIGHT = 0;
    public static final int INDICATOR_DIRECTION_RIGHT_TO_LEFT = 1;
    public static final int INDICATOR_DIRECTION_START_TO_END = 2;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface IndeterminateAnimationType {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface IndicatorDirection {
    }

    public LinearProgressIndicator(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.linearProgressIndicatorStyle);
    }

    private void s() {
        LinearDrawingDelegate linearDrawingDelegate = new LinearDrawingDelegate((LinearProgressIndicatorSpec) this.spec);
        setIndeterminateDrawable(IndeterminateDrawable.u(getContext(), (LinearProgressIndicatorSpec) this.spec, linearDrawingDelegate));
        setProgressDrawable(DeterminateDrawable.w(getContext(), (LinearProgressIndicatorSpec) this.spec, linearDrawingDelegate));
    }

    public int getIndeterminateAnimationType() {
        return ((LinearProgressIndicatorSpec) this.spec).f14946h;
    }

    public int getIndicatorDirection() {
        return ((LinearProgressIndicatorSpec) this.spec).f14947i;
    }

    @Px
    public int getTrackStopIndicatorSize() {
        return ((LinearProgressIndicatorSpec) this.spec).f14949k;
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void o(int i2, boolean z) {
        S s2 = this.spec;
        if (s2 != 0 && ((LinearProgressIndicatorSpec) s2).f14946h == 0 && isIndeterminate()) {
            return;
        }
        super.o(i2, z);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        S s2 = this.spec;
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) s2;
        boolean z2 = true;
        if (((LinearProgressIndicatorSpec) s2).f14947i != 1 && ((ViewCompat.v(this) != 1 || ((LinearProgressIndicatorSpec) this.spec).f14947i != 2) && (ViewCompat.v(this) != 0 || ((LinearProgressIndicatorSpec) this.spec).f14947i != 3))) {
            z2 = false;
        }
        linearProgressIndicatorSpec.f14948j = z2;
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        int paddingLeft = i2 - (getPaddingLeft() + getPaddingRight());
        int paddingTop = i3 - (getPaddingTop() + getPaddingBottom());
        IndeterminateDrawable<LinearProgressIndicatorSpec> indeterminateDrawable = getIndeterminateDrawable();
        if (indeterminateDrawable != null) {
            indeterminateDrawable.setBounds(0, 0, paddingLeft, paddingTop);
        }
        DeterminateDrawable<LinearProgressIndicatorSpec> progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            progressDrawable.setBounds(0, 0, paddingLeft, paddingTop);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    /* renamed from: r, reason: merged with bridge method [inline-methods] */
    public LinearProgressIndicatorSpec i(Context context, AttributeSet attributeSet) {
        return new LinearProgressIndicatorSpec(context, attributeSet);
    }

    public void setIndeterminateAnimationType(int i2) {
        if (((LinearProgressIndicatorSpec) this.spec).f14946h == i2) {
            return;
        }
        if (q() && isIndeterminate()) {
            throw new IllegalStateException("Cannot change indeterminate animation type while the progress indicator is show in indeterminate mode.");
        }
        S s2 = this.spec;
        ((LinearProgressIndicatorSpec) s2).f14946h = i2;
        ((LinearProgressIndicatorSpec) s2).e();
        if (i2 == 0) {
            getIndeterminateDrawable().y(new LinearIndeterminateContiguousAnimatorDelegate((LinearProgressIndicatorSpec) this.spec));
        } else {
            getIndeterminateDrawable().y(new LinearIndeterminateDisjointAnimatorDelegate(getContext(), (LinearProgressIndicatorSpec) this.spec));
        }
        invalidate();
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setIndicatorColor(@NonNull int... iArr) {
        super.setIndicatorColor(iArr);
        ((LinearProgressIndicatorSpec) this.spec).e();
    }

    public void setIndicatorDirection(int i2) {
        S s2 = this.spec;
        ((LinearProgressIndicatorSpec) s2).f14947i = i2;
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) s2;
        boolean z = true;
        if (i2 != 1 && ((ViewCompat.v(this) != 1 || ((LinearProgressIndicatorSpec) this.spec).f14947i != 2) && (ViewCompat.v(this) != 0 || i2 != 3))) {
            z = false;
        }
        linearProgressIndicatorSpec.f14948j = z;
        invalidate();
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackCornerRadius(int i2) {
        super.setTrackCornerRadius(i2);
        ((LinearProgressIndicatorSpec) this.spec).e();
        invalidate();
    }

    public void setTrackStopIndicatorSize(@Px int i2) {
        S s2 = this.spec;
        if (((LinearProgressIndicatorSpec) s2).f14949k != i2) {
            ((LinearProgressIndicatorSpec) s2).f14949k = Math.min(i2, ((LinearProgressIndicatorSpec) s2).f14868a);
            ((LinearProgressIndicatorSpec) this.spec).e();
            invalidate();
        }
    }

    public LinearProgressIndicator(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        super(context, attributeSet, i2, DEF_STYLE_RES);
        s();
    }
}
