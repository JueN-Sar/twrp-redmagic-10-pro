package com.zte.mifavor.custom.preference;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;
import com.zte.mifavor.custom.Config;
import com.zte.shared.wrapper.VirtualHandleWrapper;

/* loaded from: classes2.dex */
public class PreferenceScreen {
    private static final String TAG = "PreferenceScreen";

    public static Object showDialog_handleDialog(Object[] objArr) {
        Context context = (Context) objArr[0];
        final Dialog dialog = (Dialog) objArr[1];
        if (!Config.isMifavorTheme(context)) {
            return null;
        }
        Log.d(TAG, "showDialog_handleDialog");
        ActionBar actionBar = dialog.getActionBar();
        if (actionBar != null) {
            View findViewById = dialog.getWindow().findViewById(Resources.getSystem().getIdentifier("action_bar", VirtualHandleWrapper.KEY_ID, "android"));
            if (findViewById instanceof Toolbar) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                ((Toolbar) findViewById).setNavigationOnClickListener(new View.OnClickListener() { // from class: com.zte.mifavor.custom.preference.PreferenceScreen.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        }
        return null;
    }
}
