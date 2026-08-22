package cn.nubia.projection.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;
import com.zte.shared.wrapper.ContextWrapper;

/* loaded from: classes.dex */
public class ProjectionUtil {
    public static String a(String str) {
        if (str == null || str.length() <= 1) {
            return null;
        }
        return str.substring(0, str.indexOf("/"));
    }

    public static PackageInfo b(Context context, String str) {
        if (str == null) {
            PLog.a("component null");
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(a(str), 0);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Toast c(Context context, int i2, int i3) {
        ContextWrapper.updateDisplay(context);
        return d(context, context.getString(i2), i3);
    }

    public static Toast d(Context context, CharSequence charSequence, int i2) {
        return Toast.makeText(context, charSequence, i2);
    }
}
