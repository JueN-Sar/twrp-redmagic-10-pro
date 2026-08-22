package com.zte.mifavor.custom.widget;

import android.content.Context;
import android.widget.ListAdapter;
import androidx.appcompat.widget.MenuPopupWindow;
import com.zte.extres.R;
import com.zte.mifavor.custom.Config;

/* loaded from: classes2.dex */
public class AbsListView {
    private static final String TAG = "AbsListView";

    public static void onInterceptTouchEvent_setMenuItemSelector(Object[] objArr) {
        int intValue;
        if (Config.isMifavorTheme((Context) objArr[0])) {
            android.widget.AbsListView absListView = (android.widget.AbsListView) objArr[1];
            if (!(absListView instanceof MenuPopupWindow.MenuDropDownListView) || (intValue = ((Integer) objArr[2]).intValue()) == -1) {
                return;
            }
            if (intValue == 0) {
                if (intValue == ((ListAdapter) absListView.getAdapter()).getCount() - 1) {
                    absListView.setSelector(R.drawable.popupwindow_list_item_bg_all_corner);
                    return;
                } else {
                    absListView.setSelector(R.drawable.popupwindow_list_item_bg_top_corner);
                    return;
                }
            }
            if (intValue == ((ListAdapter) absListView.getAdapter()).getCount() - 1) {
                absListView.setSelector(R.drawable.popupwindow_list_item_bg_bottom_corner);
            } else {
                absListView.setSelector(R.drawable.popupwindow_list_item_bg);
            }
        }
    }
}
