package cn.nubia.gamecenter.settings.compatible;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import cn.nubia.gamecenter.settings.utils.Utils;
import java.util.Hashtable;

/* loaded from: classes.dex */
public class AutoNextLineLinearlayout extends LinearLayout {
    int mBottom;
    private Context mContext;
    int mLeft;
    int mRight;
    private int mRowChildCount;
    private int mRowCount;
    int mTop;
    Hashtable map;

    private class Position {
        int bottom;
        int left;
        int right;
        int top;

        private Position() {
        }
    }

    public AutoNextLineLinearlayout(Context context) {
        super(context);
        this.map = new Hashtable();
        this.mRowCount = 1;
        this.mRowChildCount = 1;
        this.mContext = context;
    }

    public AutoNextLineLinearlayout(Context context, int i, int i2) {
        super(context);
        this.map = new Hashtable();
        this.mRowCount = 1;
        this.mRowChildCount = 1;
        this.mContext = context;
    }

    public AutoNextLineLinearlayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.map = new Hashtable();
        this.mRowCount = 1;
        this.mRowChildCount = 1;
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return new LinearLayout.LayoutParams(0, 0);
    }

    public int getPosition(int i, int i2) {
        if (i <= 0) {
            return getPaddingLeft();
        }
        int i3 = i2 - 1;
        return getPosition(i - 1, i3) + getChildAt(i3).getLayoutParams().width + Utils.dip2px(this.mContext, 6.0f);
    }

    public int getRowCount() {
        return this.mRowCount;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            Position position = (Position) this.map.get(childAt);
            if (position != null) {
                childAt.layout(position.left, position.top, position.right, position.bottom);
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        boolean z;
        int size = View.MeasureSpec.getSize(i);
        int childCount = getChildCount();
        this.mLeft = 0;
        this.mRight = 0;
        this.mTop = 0;
        this.mBottom = 0;
        this.mRowCount = 1;
        this.mRowChildCount = 1;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i3 < childCount) {
            View childAt = getChildAt(i3);
            childAt.measure(0, 0);
            int i8 = childAt.getLayoutParams().width;
            int i9 = childAt.getLayoutParams().height;
            int i10 = i4 + i8;
            Position position = new Position();
            this.mLeft = getPosition(i3 - i5, i3);
            if (childAt.getVisibility() == 8) {
                i6 = i6 + i8 + Utils.dip2px(this.mContext, 6.0f);
                i8 = i10 - i8;
                z = true;
            } else {
                int i11 = this.mLeft - i6;
                this.mLeft = i11;
                this.mRight = i11 + childAt.getLayoutParams().width;
                if ((Utils.dip2px(this.mContext, 6.0f) * this.mRowChildCount) + i10 >= size) {
                    this.mLeft = 0;
                    this.mRight = childAt.getLayoutParams().width;
                    this.mTop = i7 + i9 + Utils.dip2px(this.mContext, 6.0f);
                    this.mRowCount++;
                    this.mRowChildCount = 1;
                    i6 = 0;
                    i5 = i3;
                } else {
                    i8 = i10;
                }
                this.mBottom = this.mTop + childAt.getLayoutParams().height;
                i7 = this.mTop;
                position.left = this.mLeft;
                position.top = this.mTop;
                position.right = this.mRight;
                position.bottom = this.mBottom;
                this.map.put(childAt, position);
                z = true;
                this.mRowChildCount++;
            }
            i3++;
            i4 = i8;
        }
        setMeasuredDimension(size, this.mBottom);
    }
}
