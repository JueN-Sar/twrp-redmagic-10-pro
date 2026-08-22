package com.zte.mifavor.custom.internal.view;

import android.R;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.RequiresApi;
import com.android.internal.view.menu.MenuAdapter;
import com.zte.mifavor.custom.Config;

/* loaded from: classes2.dex */
public class MenuAdapterMifavor {
    private static final String TAG = "MenuAdapterMifavor";

    @RequiresApi
    public static void getView_itemBg(Object[] objArr) {
        ColorDrawable colorDrawable;
        if (objArr == null || objArr.length >= 4) {
            MenuAdapter menuAdapter = (MenuAdapter) objArr[0];
            int intValue = ((Integer) objArr[1]).intValue();
            View view = (View) objArr[2];
            ViewGroup viewGroup = (ViewGroup) objArr[3];
            if (menuAdapter == null || view == null || viewGroup == null || view.getContext() == null || !Config.isMifavorTheme(view.getContext()) || view.getBackground() == null) {
                return;
            }
            if (intValue == 0 || intValue == menuAdapter.getCount() - 1) {
                if (viewGroup instanceof ListView) {
                    ListView listView = (ListView) viewGroup;
                    if (listView.getSelector() instanceof ColorDrawable) {
                        colorDrawable = (ColorDrawable) listView.getSelector();
                        Drawable background = view.getBackground();
                        if (colorDrawable == null && colorDrawable.getColor() == 0 && (background instanceof StateListDrawable)) {
                            StateListDrawable stateListDrawable = (StateListDrawable) background;
                            Drawable stateDrawable = stateListDrawable.getStateDrawable(stateListDrawable.findStateDrawableIndex(new int[]{R.attr.state_pressed}));
                            if (stateDrawable instanceof GradientDrawable) {
                                GradientDrawable gradientDrawable = (GradientDrawable) stateDrawable;
                                float dimension = view.getContext().getResources().getDimension(com.zte.extres.R.dimen.mfvc_topLeftRadius);
                                float dimension2 = view.getContext().getResources().getDimension(com.zte.extres.R.dimen.mfvc_topRightRadius);
                                float dimension3 = view.getContext().getResources().getDimension(com.zte.extres.R.dimen.mfvc_bottomRightRadius);
                                float dimension4 = view.getContext().getResources().getDimension(com.zte.extres.R.dimen.mfvc_bottomLeftRadius);
                                int count = menuAdapter.getCount() - 1;
                                if (count == 0) {
                                    gradientDrawable.setCornerRadii(new float[]{dimension, dimension, dimension2, dimension2, dimension3, dimension3, dimension4, dimension4});
                                    return;
                                } else if (intValue == 0) {
                                    gradientDrawable.setCornerRadii(new float[]{dimension, dimension, dimension2, dimension2, 0.0f, 0.0f, 0.0f, 0.0f});
                                    return;
                                } else {
                                    if (intValue == count) {
                                        gradientDrawable.setCornerRadii(new float[]{0.0f, 0.0f, 0.0f, 0.0f, dimension3, dimension3, dimension4, dimension4});
                                        return;
                                    }
                                    return;
                                }
                            }
                            return;
                        }
                        return;
                    }
                }
                colorDrawable = null;
                Drawable background2 = view.getBackground();
                if (colorDrawable == null) {
                }
            }
        }
    }
}
