package com.zte.mifavor.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.text.TextUtilsCompat;
import com.zte.extres.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
public class FlowLayout extends ViewGroup {
    private static final int CENTER = 0;
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private static final String TAG = "FlowLayout";
    private List<View> lineViews;
    protected List<List<View>> mAllViews;
    private int mGravity;
    protected List<Integer> mLineHeight;
    protected List<Integer> mLineWidth;
    protected int mMaxLines;

    public FlowLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMaxLines = 100;
        this.mAllViews = new ArrayList();
        this.mLineHeight = new ArrayList();
        this.mLineWidth = new ArrayList();
        this.lineViews = new ArrayList();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TagFlowLayout);
        this.mGravity = obtainStyledAttributes.getInt(R.styleable.TagFlowLayout_tag_gravity, -1);
        if (TextUtilsCompat.a(Locale.getDefault()) == 1) {
            if (this.mGravity == -1) {
                this.mGravity = 1;
            } else {
                this.mGravity = -1;
            }
        }
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.mAllViews.clear();
        this.mLineHeight.clear();
        this.mLineWidth.clear();
        this.lineViews.clear();
        int width = getWidth();
        int childCount = getChildCount();
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                if (measuredWidth + i7 + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin > (width - getPaddingLeft()) - getPaddingRight()) {
                    this.mLineHeight.add(Integer.valueOf(i6));
                    this.mAllViews.add(this.lineViews);
                    this.mLineWidth.add(Integer.valueOf(i7));
                    i6 = marginLayoutParams.topMargin + measuredHeight + marginLayoutParams.bottomMargin;
                    this.lineViews = new ArrayList();
                    i7 = 0;
                }
                i7 += measuredWidth + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                i6 = Math.max(i6, measuredHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin);
                this.lineViews.add(childAt);
            }
        }
        this.mLineHeight.add(Integer.valueOf(i6));
        this.mLineWidth.add(Integer.valueOf(i7));
        this.mAllViews.add(this.lineViews);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int min = Math.min(this.mMaxLines, this.mAllViews.size());
        Log.w(TAG, "onLayout lineNum=" + min);
        for (int i9 = 0; i9 < min; i9++) {
            this.lineViews = this.mAllViews.get(i9);
            int intValue = this.mLineHeight.get(i9).intValue();
            int intValue2 = this.mLineWidth.get(i9).intValue();
            int i10 = this.mGravity;
            if (i10 == -1) {
                paddingLeft = getPaddingLeft();
            } else if (i10 == 0) {
                paddingLeft = ((width - intValue2) / 2) + getPaddingLeft();
            } else if (i10 == 1) {
                paddingLeft = (width - (intValue2 + getPaddingLeft())) - getPaddingRight();
                Collections.reverse(this.lineViews);
            }
            for (int i11 = 0; i11 < this.lineViews.size(); i11++) {
                View view = this.lineViews.get(i11);
                if (view.getVisibility() != 8) {
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                    int i12 = marginLayoutParams2.leftMargin + paddingLeft;
                    int i13 = marginLayoutParams2.topMargin + paddingTop;
                    view.layout(i12, i13, view.getMeasuredWidth() + i12, view.getMeasuredHeight() + i13);
                    paddingLeft += view.getMeasuredWidth() + marginLayoutParams2.leftMargin + marginLayoutParams2.rightMargin;
                }
            }
            paddingTop += intValue;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        int size = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        int mode2 = View.MeasureSpec.getMode(i3);
        int childCount = getChildCount();
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i5 < childCount) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() == 8) {
                if (i5 == childCount - 1) {
                    i6 = Math.max(i7, i6);
                    i9 += i8;
                }
                i4 = size2;
            } else {
                measureChild(childAt, i2, i3);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                i4 = size2;
                int measuredWidth = childAt.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                int measuredHeight = childAt.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                int i10 = i7 + measuredWidth;
                if (i10 > (size - getPaddingLeft()) - getPaddingRight()) {
                    i6 = Math.max(i6, i7);
                    i9 += i8;
                } else {
                    measuredHeight = Math.max(i8, measuredHeight);
                    measuredWidth = i10;
                }
                if (i5 == childCount - 1) {
                    i6 = Math.max(measuredWidth, i6);
                    i9 += measuredHeight;
                }
                i8 = measuredHeight;
                i7 = measuredWidth;
            }
            i5++;
            size2 = i4;
        }
        int i11 = size2;
        if (mode != 1073741824) {
            size = getPaddingRight() + i6 + getPaddingLeft();
        }
        setMeasuredDimension(size, mode2 == 1073741824 ? i11 : i9 + getPaddingTop() + getPaddingBottom());
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
