package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import com.google.android.material.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class CircularProgressIndicator extends BaseProgressIndicator<CircularProgressIndicatorSpec> {
    public static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_CircularProgressIndicator;
    public static final int INDICATOR_DIRECTION_CLOCKWISE = 0;
    public static final int INDICATOR_DIRECTION_COUNTERCLOCKWISE = 1;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface IndicatorDirection {
    }

    public CircularProgressIndicator(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.circularProgressIndicatorStyle);
    }

    private void s() {
        CircularDrawingDelegate circularDrawingDelegate = new CircularDrawingDelegate((CircularProgressIndicatorSpec) this.spec);
        setIndeterminateDrawable(IndeterminateDrawable.t(getContext(), (CircularProgressIndicatorSpec) this.spec, circularDrawingDelegate));
        setProgressDrawable(DeterminateDrawable.v(getContext(), (CircularProgressIndicatorSpec) this.spec, circularDrawingDelegate));
    }

    public int getIndicatorDirection() {
        return ((CircularProgressIndicatorSpec) this.spec).f14897j;
    }

    @Px
    public int getIndicatorInset() {
        return ((CircularProgressIndicatorSpec) this.spec).f14896i;
    }

    @Px
    public int getIndicatorSize() {
        return ((CircularProgressIndicatorSpec) this.spec).f14895h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    /* renamed from: r, reason: merged with bridge method [inline-methods] */
    public CircularProgressIndicatorSpec i(Context context, AttributeSet attributeSet) {
        return new CircularProgressIndicatorSpec(context, attributeSet);
    }

    public void setIndicatorDirection(int i2) {
        ((CircularProgressIndicatorSpec) this.spec).f14897j = i2;
        invalidate();
    }

    public void setIndicatorInset(@Px int i2) {
        S s2 = this.spec;
        if (((CircularProgressIndicatorSpec) s2).f14896i != i2) {
            ((CircularProgressIndicatorSpec) s2).f14896i = i2;
            invalidate();
        }
    }

    public void setIndicatorSize(@Px int i2) {
        int max = Math.max(i2, getTrackThickness() * 2);
        S s2 = this.spec;
        if (((CircularProgressIndicatorSpec) s2).f14895h != max) {
            ((CircularProgressIndicatorSpec) s2).f14895h = max;
            ((CircularProgressIndicatorSpec) s2).e();
            requestLayout();
            invalidate();
        }
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackThickness(int i2) {
        super.setTrackThickness(i2);
        ((CircularProgressIndicatorSpec) this.spec).e();
    }

    public CircularProgressIndicator(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        super(context, attributeSet, i2, DEF_STYLE_RES);
        s();
    }
}
