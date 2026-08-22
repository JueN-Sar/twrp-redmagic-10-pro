package com.zte.mifavor.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public final class UIUtils {

    /* renamed from: a, reason: collision with root package name */
    private static int f17460a = 850;

    /* renamed from: b, reason: collision with root package name */
    private static int f17461b = 1250;

    /* renamed from: com.zte.mifavor.utils.UIUtils$1, reason: invalid class name */
    class AnonymousClass1 extends AnimatorListenerAdapter {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ViewGroup f17462c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ int f17463h;

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            this.f17462c.setVisibility(this.f17463h);
        }
    }

    public static void a(Activity activity) {
        View b2 = Identifiers.b(activity, "action_mode_close_button");
        if (b2 != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) b2.getLayoutParams();
            marginLayoutParams.rightMargin = 0;
            b2.setLayoutParams(marginLayoutParams);
        }
    }

    private static View b(Activity activity) {
        return activity.findViewById(Identifiers.a(activity, "action_bar"));
    }

    public static Activity c(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return c(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    public static float d(Context context) {
        try {
            return Settings.Global.getFloat(context.getContentResolver(), "global_animation_scale", 1.0f);
        } catch (Exception e2) {
            Log.e("UIUtils", "getAnimationScale error:" + e2);
            return 1.0f;
        }
    }

    public static int e() {
        return k(com.zte.mifavor.widget.Utils.h()) ? 64 : 0;
    }

    public static int f(Context context) {
        if (k(com.zte.mifavor.widget.Utils.h())) {
            return com.zte.mifavor.widget.Utils.x(context) ? 84 : 64;
        }
        return 0;
    }

    public static boolean g(Context context) {
        String h2 = com.zte.mifavor.widget.Utils.h();
        if (h2 != null) {
            return h2.toLowerCase().contains("pq85a02") || h2.toLowerCase().contains("nerida");
        }
        return false;
    }

    public static boolean h(Context context) {
        Activity c2;
        if (context == null || (c2 = c(context)) == null) {
            Log.d("UIUtils", "isInMultiWindowMode return false. context=" + context);
            return false;
        }
        boolean isInMultiWindowMode = c2.isInMultiWindowMode();
        Log.d("UIUtils", "isInMultiWindowMode isInMulti=" + isInMultiWindowMode);
        return isInMultiWindowMode;
    }

    public static boolean i(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.isCardTheme, typedValue, false);
        return "true".equalsIgnoreCase((String) typedValue.string);
    }

    public static boolean j(Context context) {
        if (context == null || "com.android.messaging".equals(context.getPackageName())) {
            return false;
        }
        String h2 = com.zte.mifavor.widget.Utils.h();
        boolean z = h2.contains("Z8900") || h2.contains("P678A01") || h2.contains("P678F01") || h2.contains("PQ84A31") || h2.contains("METAFLIP2");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (displayMetrics != null && z) {
            int i2 = displayMetrics.widthPixels;
            int i3 = f17460a;
            if (i2 < i3 || displayMetrics.heightPixels < i3) {
                return true;
            }
        }
        if (displayMetrics != null && h2.contains("Z8700")) {
            int i4 = displayMetrics.widthPixels;
            int i5 = f17461b;
            if (i4 < i5 && displayMetrics.heightPixels < i5) {
                return true;
            }
        }
        return false;
    }

    public static boolean k(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.contains("P780S03") || str.contains("P658F01");
    }

    public static void l(Activity activity, Menu menu) {
        m(activity, menu, activity.getColor(R.color.mfv_common_acb_icon), false);
    }

    private static void m(Activity activity, Menu menu, int i2, boolean z) {
        if (z) {
            for (int i3 = 0; i3 < menu.size(); i3++) {
                menu.getItem(i3).setIconTintList(ColorStateList.valueOf(i2));
            }
            n(activity, i2);
        }
    }

    public static void n(Activity activity, int i2) {
        Toolbar toolbar;
        Drawable overflowIcon;
        View b2 = b(activity);
        if (b2 == null || !(b2 instanceof Toolbar) || (overflowIcon = (toolbar = (Toolbar) b2).getOverflowIcon()) == null) {
            return;
        }
        overflowIcon.setTint(i2);
        toolbar.setOverflowIcon(overflowIcon);
    }
}
