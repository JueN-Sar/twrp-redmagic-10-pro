package androidx.core.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.PointerIcon;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class PointerIconCompat {

    /* renamed from: a, reason: collision with root package name */
    private final PointerIcon f3359a;

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static PointerIcon a(Bitmap bitmap, float f2, float f3) {
            return PointerIcon.create(bitmap, f2, f3);
        }

        @DoNotInline
        static PointerIcon b(Context context, int i2) {
            return PointerIcon.getSystemIcon(context, i2);
        }

        @DoNotInline
        static PointerIcon c(Resources resources, int i2) {
            return PointerIcon.load(resources, i2);
        }
    }

    private PointerIconCompat(PointerIcon pointerIcon) {
        this.f3359a = pointerIcon;
    }

    public static PointerIconCompat b(Context context, int i2) {
        return new PointerIconCompat(Api24Impl.b(context, i2));
    }

    public Object a() {
        return this.f3359a;
    }
}
