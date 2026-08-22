package com.zte.mifavor.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class ZTEPopupWindow extends PopupWindow {

    private static class ZTEPopupWindowItemAdapter extends ArrayAdapter<CharSequence> {
        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i2, view, viewGroup);
            if (view2 != null && getCount() > 0) {
                if (getCount() == 1) {
                    view2.setBackgroundResource(R.drawable.popupwindow_list_item_bg_all_corner);
                } else if (i2 == 0) {
                    view2.setBackgroundResource(R.drawable.popupwindow_list_item_bg_top_corner);
                } else if (i2 == getCount() - 1) {
                    view2.setBackgroundResource(R.drawable.popupwindow_list_item_bg_bottom_corner);
                } else {
                    view2.setBackgroundResource(R.drawable.popupwindow_list_item_bg);
                }
            }
            return view2;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }
}
