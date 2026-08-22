package androidx.core.graphics.drawable;

import android.graphics.Rect;
import androidx.core.view.GravityCompat;

/* loaded from: classes.dex */
public final class RoundedBitmapDrawableFactory {

    private static class DefaultRoundedBitmapDrawable extends RoundedBitmapDrawable {
        @Override // androidx.core.graphics.drawable.RoundedBitmapDrawable
        void b(int i2, int i3, int i4, Rect rect, Rect rect2) {
            GravityCompat.a(i2, i3, i4, rect, rect2, 0);
        }
    }
}
