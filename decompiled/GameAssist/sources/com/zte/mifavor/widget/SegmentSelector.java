package com.zte.mifavor.widget;

import android.content.Context;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.zte.extres.R;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes2.dex */
public class SegmentSelector extends LinearLayout {
    public static boolean DBG = false;
    private String TAG;
    private boolean mNightStyle;
    private int mSegmentItemWidthDefault;
    private int mSegmentWidthMax;
    private int mSelectedPosition;

    public final class SegmentItem extends LinearLayout {
        private TextView mTextView;
        final /* synthetic */ SegmentSelector this$0;

        @Override // android.widget.LinearLayout, android.view.View
        protected void onMeasure(int i2, int i3) {
            super.onMeasure(i2, i3);
        }

        public final void setEllipsize(@NonNull TextUtils.TruncateAt truncateAt) {
            TextView textView = this.mTextView;
            if (textView != null) {
                textView.setEllipsize(truncateAt);
            }
        }

        @Override // android.view.View
        public void setFocusable(boolean z) {
            TextView textView = this.mTextView;
            if (textView != null) {
                textView.setFocusable(z);
            }
            super.setFocusable(z);
        }

        public final void setNightStyle(boolean z) {
            if (this.mTextView == null || !z) {
                return;
            }
            Log.d(this.this$0.TAG, "Item setNightStyle true");
            this.mTextView.setBackgroundResource(R.drawable.segment_item_bg_selector_dark);
            this.mTextView.setTextColor(getContext().getResources().getColorStateList(R.color.segment_item_text_color_selector_dark));
        }

        public final void setText(@NonNull String str) {
            TextView textView = this.mTextView;
            if (textView != null) {
                textView.setText(str);
                Log.d(this.this$0.TAG, "setText text " + str + " textView " + this.mTextView);
            }
        }
    }

    public SegmentSelector(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "SS#SegmentSelector";
        this.mNightStyle = false;
        b(context);
    }

    private void b(Context context) {
        this.mSegmentItemWidthDefault = context.getResources().getDimensionPixelSize(R.dimen.mfvc_segmented_item_default_width);
        setOrientation(0);
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
    }

    public final void c(int i2, boolean z) {
        if (this.mSelectedPosition != i2) {
            this.mSelectedPosition = i2;
        }
        if (z) {
            int i3 = 0;
            while (i3 < getChildCount()) {
                ((TextView) getChildAt(i3).findViewById(R.id.segment_text_item)).setEnabled(i2 == i3);
                i3++;
            }
        }
    }

    public int getSegmentWidthMax() {
        return this.mSegmentWidthMax;
    }

    public int getSelectedPosition() {
        return this.mSelectedPosition;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        if (DBG) {
            Log.d(this.TAG, "onLayout in. changed=" + z + ", l=" + i2 + ", t=" + i3 + ", r=" + i4 + ", b=" + i5);
        }
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            i8 += getChildAt(i9).getMeasuredWidth();
        }
        int measuredWidth = getMeasuredWidth();
        int i10 = (measuredWidth - i8) / 2;
        int i11 = 0;
        int i12 = 0;
        while (i11 < getChildCount()) {
            View childAt = getChildAt(i11);
            int measuredWidth2 = childAt.getMeasuredWidth();
            i12 += measuredWidth2;
            int i13 = measuredWidth2 * i11;
            int[] iArr = {i7, i7};
            childAt.getLocationInWindow(iArr);
            if (DBG) {
                String str = this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onLayout . i=");
                sb.append(i11);
                sb.append(", start=");
                sb.append(i10);
                sb.append(", childWidth=");
                sb.append(measuredWidth2);
                sb.append(", parentWidth=");
                sb.append(measuredWidth);
                sb.append(", x=");
                i6 = 0;
                sb.append(iArr[0]);
                sb.append(", y=");
                sb.append(iArr[1]);
                sb.append(", getX=");
                sb.append(childAt.getX());
                sb.append(", dx=");
                sb.append(i13);
                Log.d(str, sb.toString());
            } else {
                i6 = i7;
            }
            childAt.setX(i10);
            childAt.setTranslationX(0.0f);
            i11++;
            i7 = i6;
        }
        ViewParent parent = getParent();
        if (parent instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) parent;
            int measuredWidth3 = frameLayout.getMeasuredWidth();
            int measuredWidth4 = (frameLayout.getMeasuredWidth() - i12) / 2;
            if (DBG) {
                Log.w(this.TAG, "onLayout . dx=" + measuredWidth4 + ", thisWidth = " + i12 + ", parent width = " + measuredWidth3);
            }
            frameLayout.setTranslationX(measuredWidth4);
        }
        super.onLayout(z, i2, i3, i4, i5);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        if (DBG) {
            Log.w(this.TAG, "onMeasure in. widthMeasureSpec = " + i2 + ", heightMeasureSpec=" + i3);
        }
        int size = View.MeasureSpec.getSize(i2);
        if (getChildCount() < 1 || size <= 0) {
            super.onMeasure(i2, i3);
            return;
        }
        if (DBG) {
            Log.d(this.TAG, "<<<parent=" + getMeasuredWidth() + ",mSegmentWidthMax=" + this.mSegmentWidthMax);
        }
        super.onMeasure(i2, i3);
        int childCount = getChildCount();
        int[] iArr = new int[childCount];
        int[] iArr2 = new int[childCount];
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            childAt.measure(View.MeasureSpec.makeMeasureSpec(this.mSegmentItemWidthDefault, 0), i3);
            int measuredWidth = childAt.getMeasuredWidth();
            iArr[i5] = i5;
            iArr2[i5] = measuredWidth;
            if (DBG) {
                Log.d(this.TAG, "measure " + childAt.getId() + ":" + measuredWidth);
            }
        }
        int i6 = 0;
        while (true) {
            i4 = childCount - 1;
            if (i6 >= i4) {
                break;
            }
            i6++;
            for (int i7 = i6; i7 > 0; i7--) {
                int i8 = iArr2[i7];
                int i9 = i7 - 1;
                int i10 = iArr2[i9];
                if (i8 > i10) {
                    iArr2[i7] = i10;
                    iArr2[i9] = i8;
                    int i11 = iArr[i7];
                    iArr[i7] = iArr[i9];
                    iArr[i9] = i11;
                }
            }
        }
        int i12 = 0;
        for (int i13 = 0; i13 < getChildCount(); i13++) {
            View childAt2 = getChildAt(i13);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int i14 = this.mSegmentItemWidthDefault;
            if (measuredWidth2 < i14) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(i14, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), i3);
            } else {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(i14, 0), i3);
            }
            i12 += childAt2.getMeasuredWidth();
            if (DBG) {
                Log.d(this.TAG, "measure " + childAt2.getId() + ":" + childAt2.getMeasuredWidth());
            }
        }
        int i15 = size - i12;
        if (DBG) {
            Log.d(this.TAG, "excessSpace=" + i15);
        }
        if (i15 <= 0) {
            int i16 = size;
            int i17 = i12;
            while (true) {
                if (i4 < 0) {
                    int i18 = size;
                    int i19 = 0;
                    while (true) {
                        if (i19 >= childCount) {
                            break;
                        }
                        int i20 = iArr[i19];
                        View childAt3 = getChildAt(i20);
                        int measuredWidth3 = childAt3.getMeasuredWidth();
                        int i21 = this.mSegmentItemWidthDefault;
                        int i22 = i17 - measuredWidth3;
                        if (i18 - i22 >= i21) {
                            childAt3.measure(View.MeasureSpec.makeMeasureSpec((i18 - i17) + measuredWidth3, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), i3);
                            break;
                        }
                        childAt3.measure(View.MeasureSpec.makeMeasureSpec(i21, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), i3);
                        i18 -= i21;
                        if (DBG) {
                            Log.d(this.TAG, "measure num" + i20 + " width:" + measuredWidth3);
                        }
                        i19++;
                        i17 = i22;
                    }
                } else {
                    View childAt4 = getChildAt(iArr[i4]);
                    int measuredWidth4 = childAt4.getMeasuredWidth();
                    int i23 = i12 - measuredWidth4;
                    int i24 = i16 - i23;
                    int i25 = iArr2[i4];
                    if (i24 >= i25) {
                        childAt4.measure(View.MeasureSpec.makeMeasureSpec((i16 - i12) + measuredWidth4, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), i3);
                        break;
                    }
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec(i25, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), i3);
                    int i26 = iArr2[i4];
                    i16 -= i26;
                    i17 = (i17 - measuredWidth4) + i26;
                    i4--;
                    i12 = i23;
                }
            }
        }
        for (int i27 = 0; i27 < getChildCount(); i27++) {
            View childAt5 = getChildAt(i27);
            if (DBG) {
                Log.d(this.TAG, "measure " + childAt5.getId() + ": last " + childAt5.getMeasuredWidth());
            }
            childAt5.getMeasuredWidth();
        }
        setMeasuredDimension(size, getMeasuredHeight());
        if (DBG) {
            Log.w(this.TAG, "onMeasure out. parentWidth = " + size + ", height=" + getMeasuredHeight());
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    public void setDEBUG(boolean z) {
        DBG = z;
    }

    public void setNightStyle(boolean z) {
        if (this.mNightStyle == z) {
            return;
        }
        this.mNightStyle = z;
        Log.d(this.TAG, "selector setNight in " + z);
    }

    public void setSegmentItemWidthMin(int i2) {
        this.mSegmentItemWidthDefault = i2;
    }

    public void setSegmentWidthMax(int i2) {
        this.mSegmentWidthMax = i2;
    }

    public void setSelectedPosition(int i2) {
        this.mSelectedPosition = i2;
    }
}
