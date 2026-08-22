package androidx.core.view;

import android.graphics.Insets;
import android.graphics.Rect;
import android.view.DisplayCutout;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.core.util.ObjectsCompat;
import java.util.List;

/* loaded from: classes.dex */
public final class DisplayCutoutCompat {

    /* renamed from: a, reason: collision with root package name */
    private final DisplayCutout f3337a;

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static DisplayCutout a(Rect rect, List<Rect> list) {
            return new DisplayCutout(rect, list);
        }

        @DoNotInline
        static List<Rect> b(DisplayCutout displayCutout) {
            return displayCutout.getBoundingRects();
        }

        @DoNotInline
        static int c(DisplayCutout displayCutout) {
            return displayCutout.getSafeInsetBottom();
        }

        @DoNotInline
        static int d(DisplayCutout displayCutout) {
            return displayCutout.getSafeInsetLeft();
        }

        @DoNotInline
        static int e(DisplayCutout displayCutout) {
            return displayCutout.getSafeInsetRight();
        }

        @DoNotInline
        static int f(DisplayCutout displayCutout) {
            return displayCutout.getSafeInsetTop();
        }
    }

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static DisplayCutout a(Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4) {
            return new DisplayCutout(insets, rect, rect2, rect3, rect4);
        }
    }

    @RequiresApi
    static class Api30Impl {
        @DoNotInline
        static DisplayCutout a(Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4, Insets insets2) {
            return new DisplayCutout(insets, rect, rect2, rect3, rect4, insets2);
        }

        @DoNotInline
        static Insets b(DisplayCutout displayCutout) {
            return displayCutout.getWaterfallInsets();
        }
    }

    private DisplayCutoutCompat(DisplayCutout displayCutout) {
        this.f3337a = displayCutout;
    }

    static DisplayCutoutCompat e(DisplayCutout displayCutout) {
        if (displayCutout == null) {
            return null;
        }
        return new DisplayCutoutCompat(displayCutout);
    }

    public int a() {
        return Api28Impl.c(this.f3337a);
    }

    public int b() {
        return Api28Impl.d(this.f3337a);
    }

    public int c() {
        return Api28Impl.e(this.f3337a);
    }

    public int d() {
        return Api28Impl.f(this.f3337a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DisplayCutoutCompat.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.a(this.f3337a, ((DisplayCutoutCompat) obj).f3337a);
    }

    public int hashCode() {
        DisplayCutout displayCutout = this.f3337a;
        if (displayCutout == null) {
            return 0;
        }
        return displayCutout.hashCode();
    }

    public String toString() {
        return "DisplayCutoutCompat{" + this.f3337a + "}";
    }
}
