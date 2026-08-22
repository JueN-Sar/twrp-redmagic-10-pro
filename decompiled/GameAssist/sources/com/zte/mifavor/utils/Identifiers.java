package com.zte.mifavor.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class Identifiers {

    /* renamed from: a, reason: collision with root package name */
    private static HashMap f17424a = new HashMap();

    public static int a(Context context, String str) {
        if (f17424a.containsKey(str)) {
            return ((Integer) f17424a.get(str)).intValue();
        }
        if (context == null) {
            return 0;
        }
        int identifier = context.getResources().getIdentifier(str, VirtualHandleWrapper.KEY_ID, "android");
        f17424a.put(str, Integer.valueOf(identifier));
        Log.d("Identifiers", "add " + str + " to buffer.");
        return identifier;
    }

    public static View b(Activity activity, String str) {
        int a2 = a(activity, str);
        if (a2 > 0) {
            return activity.findViewById(a2);
        }
        return null;
    }
}
