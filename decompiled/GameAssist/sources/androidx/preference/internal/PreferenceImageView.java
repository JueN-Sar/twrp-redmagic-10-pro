package androidx.preference.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.RestrictTo;
import androidx.preference.R;
import com.google.android.gms.common.api.Api;

@RestrictTo
/* loaded from: classes.dex */
public class PreferenceImageView extends ImageView {
    private int mMaxHeight;
    private int mMaxWidth;

    public PreferenceImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.widget.ImageView
    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    @Override // android.widget.ImageView
    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE || mode == 0) {
            int size = View.MeasureSpec.getSize(i2);
            int maxWidth = getMaxWidth();
            if (maxWidth != Integer.MAX_VALUE && (maxWidth < size || mode == 0)) {
                i2 = View.MeasureSpec.makeMeasureSpec(maxWidth, Integer.MIN_VALUE);
            }
        }
        int mode2 = View.MeasureSpec.getMode(i3);
        if (mode2 == Integer.MIN_VALUE || mode2 == 0) {
            int size2 = View.MeasureSpec.getSize(i3);
            int maxHeight = getMaxHeight();
            if (maxHeight != Integer.MAX_VALUE && (maxHeight < size2 || mode2 == 0)) {
                i3 = View.MeasureSpec.makeMeasureSpec(maxHeight, Integer.MIN_VALUE);
            }
        }
        super.onMeasure(i2, i3);
    }

    @Override // android.widget.ImageView
    public void setMaxHeight(int i2) {
        this.mMaxHeight = i2;
        super.setMaxHeight(i2);
    }

    @Override // android.widget.ImageView
    public void setMaxWidth(int i2) {
        this.mMaxWidth = i2;
        super.setMaxWidth(i2);
    }

    public PreferenceImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMaxWidth = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.mMaxHeight = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceImageView, i2, 0);
        setMaxWidth(obtainStyledAttributes.getDimensionPixelSize(R.styleable.PreferenceImageView_maxWidth, Api.BaseClientBuilder.API_PRIORITY_OTHER));
        setMaxHeight(obtainStyledAttributes.getDimensionPixelSize(R.styleable.PreferenceImageView_maxHeight, Api.BaseClientBuilder.API_PRIORITY_OTHER));
        obtainStyledAttributes.recycle();
    }
}
