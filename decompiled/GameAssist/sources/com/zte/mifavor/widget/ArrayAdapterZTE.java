package com.zte.mifavor.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class ArrayAdapterZTE<T> extends ArrayAdapter<T> {
    public ArrayAdapterZTE(Context context, int i2, Object[] objArr) {
        super(context, i2, objArr);
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        View dropDownView = super.getDropDownView(i2, view, viewGroup);
        if (dropDownView != null && getCount() > 0) {
            if (getCount() == 1) {
                dropDownView.setBackgroundResource(R.drawable.popupwindow_list_item_bg_all_corner);
            } else if (i2 == 0) {
                dropDownView.setBackgroundResource(R.drawable.popupwindow_list_item_bg_top_corner);
            } else if (i2 == getCount() - 1) {
                dropDownView.setBackgroundResource(R.drawable.popupwindow_list_item_bg_bottom_corner);
            } else {
                dropDownView.setBackgroundResource(R.drawable.popupwindow_list_item_bg);
            }
        }
        return dropDownView;
    }
}
