package androidx.core.widget;

import android.view.View;
import android.widget.PopupWindow;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class PopupWindowCompat {

    @RequiresApi
    static class Api23Impl {
        @DoNotInline
        static boolean a(PopupWindow popupWindow) {
            return popupWindow.getOverlapAnchor();
        }

        @DoNotInline
        static int b(PopupWindow popupWindow) {
            return popupWindow.getWindowLayoutType();
        }

        @DoNotInline
        static void c(PopupWindow popupWindow, boolean z) {
            popupWindow.setOverlapAnchor(z);
        }

        @DoNotInline
        static void d(PopupWindow popupWindow, int i2) {
            popupWindow.setWindowLayoutType(i2);
        }
    }

    public static void a(PopupWindow popupWindow, boolean z) {
        Api23Impl.c(popupWindow, z);
    }

    public static void b(PopupWindow popupWindow, int i2) {
        Api23Impl.d(popupWindow, i2);
    }

    public static void c(PopupWindow popupWindow, View view, int i2, int i3, int i4) {
        popupWindow.showAsDropDown(view, i2, i3, i4);
    }
}
