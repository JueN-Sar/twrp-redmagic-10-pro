package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.FragmentTransaction;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public abstract class ActionBar {

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface DisplayOptions {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface NavigationMode {
    }

    public interface OnMenuVisibilityListener {
        void onMenuVisibilityChanged(boolean z);
    }

    @Deprecated
    public interface OnNavigationListener {
        boolean onNavigationItemSelected(int i2, long j2);
    }

    @Deprecated
    public static abstract class Tab {
        public abstract CharSequence a();

        public abstract View b();

        public abstract Drawable c();

        public abstract int d();

        public abstract CharSequence e();

        public abstract void f();
    }

    @Deprecated
    public interface TabListener {
        void a(Tab tab, FragmentTransaction fragmentTransaction);

        void b(Tab tab, FragmentTransaction fragmentTransaction);

        void c(Tab tab, FragmentTransaction fragmentTransaction);
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public void c(boolean z) {
    }

    public abstract int d();

    public Context e() {
        return null;
    }

    public boolean f() {
        return false;
    }

    public void g(Configuration configuration) {
    }

    void h() {
    }

    public boolean i(int i2, KeyEvent keyEvent) {
        return false;
    }

    public boolean j(KeyEvent keyEvent) {
        return false;
    }

    public boolean k() {
        return false;
    }

    public void l(boolean z) {
    }

    public abstract void m(boolean z);

    public void n(int i2) {
    }

    public void o(boolean z) {
    }

    public void p(CharSequence charSequence) {
    }

    public ActionMode q(ActionMode.Callback callback) {
        return null;
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {

        /* renamed from: a, reason: collision with root package name */
        public int f143a;

        public LayoutParams(@NonNull Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f143a = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ActionBarLayout);
            this.f143a = obtainStyledAttributes.getInt(R.styleable.ActionBarLayout_android_layout_gravity, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.f143a = 8388627;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.f143a = 0;
            this.f143a = layoutParams.f143a;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f143a = 0;
        }
    }
}
