package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CheckedTextView;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.CheckedTextViewCompat;

@RestrictTo
/* loaded from: classes.dex */
class AppCompatCheckedTextViewHelper {

    /* renamed from: a, reason: collision with root package name */
    private final CheckedTextView f734a;

    /* renamed from: b, reason: collision with root package name */
    private ColorStateList f735b = null;

    /* renamed from: c, reason: collision with root package name */
    private PorterDuff.Mode f736c = null;

    /* renamed from: d, reason: collision with root package name */
    private boolean f737d = false;

    /* renamed from: e, reason: collision with root package name */
    private boolean f738e = false;

    /* renamed from: f, reason: collision with root package name */
    private boolean f739f;

    AppCompatCheckedTextViewHelper(CheckedTextView checkedTextView) {
        this.f734a = checkedTextView;
    }

    void a() {
        Drawable a2 = CheckedTextViewCompat.a(this.f734a);
        if (a2 != null) {
            if (this.f737d || this.f738e) {
                Drawable mutate = DrawableCompat.r(a2).mutate();
                if (this.f737d) {
                    DrawableCompat.o(mutate, this.f735b);
                }
                if (this.f738e) {
                    DrawableCompat.p(mutate, this.f736c);
                }
                if (mutate.isStateful()) {
                    mutate.setState(this.f734a.getDrawableState());
                }
                this.f734a.setCheckMarkDrawable(mutate);
            }
        }
    }

    ColorStateList b() {
        return this.f735b;
    }

    PorterDuff.Mode c() {
        return this.f736c;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0064 A[Catch: all -> 0x003d, TryCatch #1 {all -> 0x003d, blocks: (B:3:0x001f, B:5:0x0027, B:8:0x002f, B:9:0x005c, B:11:0x0064, B:12:0x006f, B:14:0x0077, B:21:0x003f, B:23:0x0047, B:25:0x004f), top: B:2:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0077 A[Catch: all -> 0x003d, TRY_LEAVE, TryCatch #1 {all -> 0x003d, blocks: (B:3:0x001f, B:5:0x0027, B:8:0x002f, B:9:0x005c, B:11:0x0064, B:12:0x006f, B:14:0x0077, B:21:0x003f, B:23:0x0047, B:25:0x004f), top: B:2:0x001f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void d(android.util.AttributeSet r11, int r12) {
        /*
            r10 = this;
            android.widget.CheckedTextView r0 = r10.f734a
            android.content.Context r0 = r0.getContext()
            int[] r1 = androidx.appcompat.R.styleable.CheckedTextView
            r2 = 0
            androidx.appcompat.widget.TintTypedArray r0 = androidx.appcompat.widget.TintTypedArray.v(r0, r11, r1, r12, r2)
            android.widget.CheckedTextView r3 = r10.f734a
            android.content.Context r4 = r3.getContext()
            int[] r5 = androidx.appcompat.R.styleable.CheckedTextView
            android.content.res.TypedArray r7 = r0.r()
            r9 = 0
            r6 = r11
            r8 = r12
            androidx.core.view.ViewCompat.g0(r3, r4, r5, r6, r7, r8, r9)
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkCompat     // Catch: java.lang.Throwable -> L3d
            boolean r11 = r0.s(r11)     // Catch: java.lang.Throwable -> L3d
            if (r11 == 0) goto L3f
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkCompat     // Catch: java.lang.Throwable -> L3d
            int r11 = r0.n(r11, r2)     // Catch: java.lang.Throwable -> L3d
            if (r11 == 0) goto L3f
            android.widget.CheckedTextView r12 = r10.f734a     // Catch: java.lang.Throwable -> L3d android.content.res.Resources.NotFoundException -> L3f
            android.content.Context r1 = r12.getContext()     // Catch: java.lang.Throwable -> L3d android.content.res.Resources.NotFoundException -> L3f
            android.graphics.drawable.Drawable r11 = androidx.appcompat.content.res.AppCompatResources.b(r1, r11)     // Catch: java.lang.Throwable -> L3d android.content.res.Resources.NotFoundException -> L3f
            r12.setCheckMarkDrawable(r11)     // Catch: java.lang.Throwable -> L3d android.content.res.Resources.NotFoundException -> L3f
            goto L5c
        L3d:
            r10 = move-exception
            goto L8c
        L3f:
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_android_checkMark     // Catch: java.lang.Throwable -> L3d
            boolean r11 = r0.s(r11)     // Catch: java.lang.Throwable -> L3d
            if (r11 == 0) goto L5c
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_android_checkMark     // Catch: java.lang.Throwable -> L3d
            int r11 = r0.n(r11, r2)     // Catch: java.lang.Throwable -> L3d
            if (r11 == 0) goto L5c
            android.widget.CheckedTextView r12 = r10.f734a     // Catch: java.lang.Throwable -> L3d
            android.content.Context r1 = r12.getContext()     // Catch: java.lang.Throwable -> L3d
            android.graphics.drawable.Drawable r11 = androidx.appcompat.content.res.AppCompatResources.b(r1, r11)     // Catch: java.lang.Throwable -> L3d
            r12.setCheckMarkDrawable(r11)     // Catch: java.lang.Throwable -> L3d
        L5c:
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkTint     // Catch: java.lang.Throwable -> L3d
            boolean r11 = r0.s(r11)     // Catch: java.lang.Throwable -> L3d
            if (r11 == 0) goto L6f
            android.widget.CheckedTextView r11 = r10.f734a     // Catch: java.lang.Throwable -> L3d
            int r12 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkTint     // Catch: java.lang.Throwable -> L3d
            android.content.res.ColorStateList r12 = r0.c(r12)     // Catch: java.lang.Throwable -> L3d
            androidx.core.widget.CheckedTextViewCompat.b(r11, r12)     // Catch: java.lang.Throwable -> L3d
        L6f:
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkTintMode     // Catch: java.lang.Throwable -> L3d
            boolean r11 = r0.s(r11)     // Catch: java.lang.Throwable -> L3d
            if (r11 == 0) goto L88
            android.widget.CheckedTextView r10 = r10.f734a     // Catch: java.lang.Throwable -> L3d
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkTintMode     // Catch: java.lang.Throwable -> L3d
            r12 = -1
            int r11 = r0.k(r11, r12)     // Catch: java.lang.Throwable -> L3d
            r12 = 0
            android.graphics.PorterDuff$Mode r11 = androidx.appcompat.widget.DrawableUtils.d(r11, r12)     // Catch: java.lang.Throwable -> L3d
            androidx.core.widget.CheckedTextViewCompat.c(r10, r11)     // Catch: java.lang.Throwable -> L3d
        L88:
            r0.x()
            return
        L8c:
            r0.x()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatCheckedTextViewHelper.d(android.util.AttributeSet, int):void");
    }

    void e() {
        if (this.f739f) {
            this.f739f = false;
        } else {
            this.f739f = true;
            a();
        }
    }

    void f(ColorStateList colorStateList) {
        this.f735b = colorStateList;
        this.f737d = true;
        a();
    }

    void g(PorterDuff.Mode mode) {
        this.f736c = mode;
        this.f738e = true;
        a();
    }
}
