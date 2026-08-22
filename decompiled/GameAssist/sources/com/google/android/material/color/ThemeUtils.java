package com.google.android.material.color;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.Window;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public final class ThemeUtils {
    public static void a(Context context, int i2) {
        Resources.Theme b2;
        context.getTheme().applyStyle(i2, true);
        if (!(context instanceof Activity) || (b2 = b((Activity) context)) == null) {
            return;
        }
        b2.applyStyle(i2, true);
    }

    private static Resources.Theme b(Activity activity) {
        View peekDecorView;
        Context context;
        Window window = activity.getWindow();
        if (window == null || (peekDecorView = window.peekDecorView()) == null || (context = peekDecorView.getContext()) == null) {
            return null;
        }
        return context.getTheme();
    }
}
