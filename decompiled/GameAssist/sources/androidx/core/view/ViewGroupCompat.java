package androidx.core.view;

import android.view.ViewGroup;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class ViewGroupCompat {

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static int a(ViewGroup viewGroup) {
            return viewGroup.getNestedScrollAxes();
        }

        @DoNotInline
        static boolean b(ViewGroup viewGroup) {
            return viewGroup.isTransitionGroup();
        }

        @DoNotInline
        static void c(ViewGroup viewGroup, boolean z) {
            viewGroup.setTransitionGroup(z);
        }
    }

    public static boolean a(ViewGroup viewGroup) {
        return Api21Impl.b(viewGroup);
    }
}
