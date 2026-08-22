package com.zte.mifavor.custom;

import android.content.Context;
import android.util.TypedValue;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class Config {
    public static boolean isMifavorTheme(Context context) {
        return context.getTheme().resolveAttribute(R.attr.versionType, new TypedValue(), false);
    }
}
