package androidx.transition;

import android.view.View;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
class ViewUtilsApi22 extends ViewUtilsApi21 {

    /* renamed from: j, reason: collision with root package name */
    private static boolean f5597j = true;

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static void a(View view, int i2, int i3, int i4, int i5) {
            view.setLeftTopRightBottom(i2, i3, i4, i5);
        }
    }

    ViewUtilsApi22() {
    }

    @Override // androidx.transition.ViewUtilsApi19
    public void f(View view, int i2, int i3, int i4, int i5) {
        if (f5597j) {
            try {
                Api29Impl.a(view, i2, i3, i4, i5);
            } catch (NoSuchMethodError unused) {
                f5597j = false;
            }
        }
    }
}
