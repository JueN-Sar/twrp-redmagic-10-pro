package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class CompoundButtonCompat {

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static ColorStateList a(CompoundButton compoundButton) {
            return compoundButton.getButtonTintList();
        }

        @DoNotInline
        static PorterDuff.Mode b(CompoundButton compoundButton) {
            return compoundButton.getButtonTintMode();
        }

        @DoNotInline
        static void c(CompoundButton compoundButton, ColorStateList colorStateList) {
            compoundButton.setButtonTintList(colorStateList);
        }

        @DoNotInline
        static void d(CompoundButton compoundButton, PorterDuff.Mode mode) {
            compoundButton.setButtonTintMode(mode);
        }
    }

    @RequiresApi
    static class Api23Impl {
        @DoNotInline
        static Drawable a(CompoundButton compoundButton) {
            return compoundButton.getButtonDrawable();
        }
    }

    public static Drawable a(CompoundButton compoundButton) {
        return Api23Impl.a(compoundButton);
    }

    public static ColorStateList b(CompoundButton compoundButton) {
        return Api21Impl.a(compoundButton);
    }

    public static PorterDuff.Mode c(CompoundButton compoundButton) {
        return Api21Impl.b(compoundButton);
    }

    public static void d(CompoundButton compoundButton, ColorStateList colorStateList) {
        Api21Impl.c(compoundButton, colorStateList);
    }

    public static void e(CompoundButton compoundButton, PorterDuff.Mode mode) {
        Api21Impl.d(compoundButton, mode);
    }
}
