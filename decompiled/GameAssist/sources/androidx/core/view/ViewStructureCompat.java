package androidx.core.view;

import android.view.ViewStructure;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public class ViewStructureCompat {

    @RequiresApi
    private static class Api23Impl {
        @DoNotInline
        static void a(ViewStructure viewStructure, String str) {
            viewStructure.setClassName(str);
        }

        @DoNotInline
        static void b(ViewStructure viewStructure, CharSequence charSequence) {
            viewStructure.setContentDescription(charSequence);
        }

        @DoNotInline
        static void c(ViewStructure viewStructure, int i2, int i3, int i4, int i5, int i6, int i7) {
            viewStructure.setDimens(i2, i3, i4, i5, i6, i7);
        }

        @DoNotInline
        static void d(ViewStructure viewStructure, CharSequence charSequence) {
            viewStructure.setText(charSequence);
        }
    }
}
