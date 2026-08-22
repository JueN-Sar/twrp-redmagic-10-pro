package androidx.appcompat.widget;

import android.view.View;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public class TooltipCompat {

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static void a(View view, CharSequence charSequence) {
            view.setTooltipText(charSequence);
        }
    }

    public static void a(View view, CharSequence charSequence) {
        Api26Impl.a(view, charSequence);
    }
}
