package cn.nubia.componentsdk.pay;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/* loaded from: classes.dex */
public class NoScrollListView extends ListView {
    public NoScrollListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }

    public NoScrollListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
