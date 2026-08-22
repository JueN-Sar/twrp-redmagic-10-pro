package androidx.transition;

import android.view.View;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
class ViewUtilsApi23 extends ViewUtilsApi22 {

    /* renamed from: k, reason: collision with root package name */
    private static boolean f5598k = true;

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static void a(View view, int i2) {
            view.setTransitionVisibility(i2);
        }
    }

    ViewUtilsApi23() {
    }

    @Override // androidx.transition.ViewUtilsApi19
    public void h(View view, int i2) {
        if (f5598k) {
            try {
                Api29Impl.a(view, i2);
            } catch (NoSuchMethodError unused) {
                f5598k = false;
            }
        }
    }
}
