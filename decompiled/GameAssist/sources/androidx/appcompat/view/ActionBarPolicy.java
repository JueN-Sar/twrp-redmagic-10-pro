package androidx.appcompat.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;

@RestrictTo
/* loaded from: classes.dex */
public class ActionBarPolicy {

    /* renamed from: a, reason: collision with root package name */
    private Context f420a;

    private ActionBarPolicy(Context context) {
        this.f420a = context;
    }

    public static ActionBarPolicy b(Context context) {
        return new ActionBarPolicy(context);
    }

    public boolean a() {
        return this.f420a.getApplicationInfo().targetSdkVersion < 14;
    }

    public int c() {
        return this.f420a.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public int d() {
        Configuration configuration = this.f420a.getResources().getConfiguration();
        int i2 = configuration.screenWidthDp;
        int i3 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i2 > 600) {
            return 5;
        }
        if (i2 > 960 && i3 > 720) {
            return 5;
        }
        if (i2 > 720 && i3 > 960) {
            return 5;
        }
        if (i2 >= 500) {
            return 4;
        }
        if (i2 > 640 && i3 > 480) {
            return 4;
        }
        if (i2 <= 480 || i3 <= 640) {
            return i2 >= 360 ? 3 : 2;
        }
        return 4;
    }

    public int e() {
        return this.f420a.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_stacked_tab_max_width);
    }

    public int f() {
        TypedArray obtainStyledAttributes = this.f420a.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(R.styleable.ActionBar_height, 0);
        Resources resources = this.f420a.getResources();
        if (!g()) {
            layoutDimension = Math.min(layoutDimension, resources.getDimensionPixelSize(R.dimen.abc_action_bar_stacked_max_height));
        }
        obtainStyledAttributes.recycle();
        return layoutDimension;
    }

    public boolean g() {
        return this.f420a.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs);
    }

    public boolean h() {
        return true;
    }
}
