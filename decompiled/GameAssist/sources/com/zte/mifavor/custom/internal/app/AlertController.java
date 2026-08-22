package com.zte.mifavor.custom.internal.app;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import com.zte.extres.R;
import com.zte.mifavor.custom.Config;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class AlertController {
    private static final int BIT_BUTTON_NEGATIVE = 2;
    private static final int BIT_BUTTON_NEUTRAL = 4;
    private static final int BIT_BUTTON_POSITIVE = 1;
    private static final String TAG = "AlertControllerCustom";

    public static Object AlertController_getDialogStyle(Object[] objArr) {
        if (!Config.isMifavorTheme((Context) objArr[0])) {
            return null;
        }
        Object[] objArr2 = new Object[7];
        objArr2[0] = Integer.valueOf(R.layout.alert_dialog_material_mfv);
        objArr2[2] = Integer.valueOf(R.layout.select_dialog_material_mfv);
        objArr2[3] = Integer.valueOf(R.layout.select_dialog_multichoice_material_mfv);
        objArr2[4] = Integer.valueOf(R.layout.select_dialog_singlechoice_material_mfv);
        objArr2[5] = Integer.valueOf(R.layout.select_dialog_item_material_mfv);
        Log.d(TAG, "getDialogRes:" + Arrays.toString(objArr2));
        return objArr2;
    }

    public static Object setupView_begin(Object[] objArr) {
        int i2 = 0;
        if (!Config.isMifavorTheme((Context) objArr[0])) {
            return null;
        }
        Log.d(TAG, "setup begin");
        Window window = (Window) objArr[1];
        int[] iArr = {R.id.parentPanel, R.id.topPanel, R.id.contentPanel, R.id.customPanel, R.id.buttonPanel, R.id.title_template, R.id.alertTitle, R.id.titleDividerNoCustom, R.id.scrollView, R.id.textSpacerNoTitle, R.id.textSpacerNoButtons};
        int i3 = 5;
        while (i2 < 11) {
            View findViewById = window.findViewById(iArr[i2]);
            if (findViewById != null) {
                findViewById.setId(((Integer) objArr[i3]).intValue());
            }
            i2++;
            i3++;
        }
        return null;
    }

    public static Object setupView_end(Object[] objArr) {
        Context context = (Context) objArr[0];
        ListView listView = (ListView) objArr[2];
        if (!Config.isMifavorTheme(context)) {
            return null;
        }
        Log.d(TAG, "setup end.");
        if (listView != null) {
            listView.setPadding(listView.getPaddingLeft(), 0, listView.getPaddingRight(), 0);
        }
        return null;
    }
}
