package androidx.transition;

import android.view.ViewGroup;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
class ViewGroupUtils {

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static int a(ViewGroup viewGroup, int i2) {
            return viewGroup.getChildDrawingOrder(i2);
        }

        @DoNotInline
        static void b(ViewGroup viewGroup, boolean z) {
            viewGroup.suppressLayout(z);
        }
    }

    static int a(ViewGroup viewGroup, int i2) {
        return Api29Impl.a(viewGroup, i2);
    }

    static void b(ViewGroup viewGroup, boolean z) {
        Api29Impl.b(viewGroup, z);
    }
}
