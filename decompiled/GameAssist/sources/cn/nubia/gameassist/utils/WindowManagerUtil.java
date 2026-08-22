package cn.nubia.gameassist.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/* loaded from: classes.dex */
public class WindowManagerUtil {

    /* renamed from: a, reason: collision with root package name */
    private WindowManager f7704a;

    public WindowManagerUtil(WindowManager windowManager) {
        this.f7704a = windowManager;
    }

    public void a(View view, ViewGroup.LayoutParams layoutParams) {
        try {
            this.f7704a.addView(view, layoutParams);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void b(View view) {
        try {
            this.f7704a.removeView(view);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void c(View view, ViewGroup.LayoutParams layoutParams) {
        try {
            this.f7704a.updateViewLayout(view, layoutParams);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
