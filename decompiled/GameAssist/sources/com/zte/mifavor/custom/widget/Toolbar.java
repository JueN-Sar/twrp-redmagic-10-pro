package com.zte.mifavor.custom.widget;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.zte.extres.R;
import com.zte.mifavor.custom.Config;

/* loaded from: classes2.dex */
public class Toolbar {
    private static final String TAG = "ToolbarMiFavor";

    public static void ensureNavAndCollapseButtonView_setBackground(Object[] objArr) {
        Context context = (Context) objArr[0];
        if (Config.isMifavorTheme(context)) {
            Log.d("Toolbar", "ensureNavAndCollapseButtonView_setBackground in");
            View view = (View) objArr[1];
            if (view != null) {
                view.setBackground(context.getDrawable(R.drawable.image_button_ripple_bg));
            }
        }
    }
}
