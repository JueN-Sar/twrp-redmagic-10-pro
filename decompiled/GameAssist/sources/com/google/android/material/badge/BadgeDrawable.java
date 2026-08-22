package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.OptIn;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeState;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;

@OptIn
/* loaded from: classes.dex */
public class BadgeDrawable extends Drawable implements TextDrawableHelper.TextDrawableDelegate {
    private static final int t = R.style.Widget_MaterialComponents_Badge;
    private static final int u = R.attr.badgeStyle;

    /* renamed from: c, reason: collision with root package name */
    private final WeakReference f13908c;

    /* renamed from: h, reason: collision with root package name */
    private final MaterialShapeDrawable f13909h;

    /* renamed from: i, reason: collision with root package name */
    private final TextDrawableHelper f13910i;

    /* renamed from: j, reason: collision with root package name */
    private final Rect f13911j;

    /* renamed from: k, reason: collision with root package name */
    private final BadgeState f13912k;

    /* renamed from: l, reason: collision with root package name */
    private float f13913l;

    /* renamed from: m, reason: collision with root package name */
    private float f13914m;

    /* renamed from: n, reason: collision with root package name */
    private int f13915n;

    /* renamed from: o, reason: collision with root package name */
    private float f13916o;

    /* renamed from: p, reason: collision with root package name */
    private float f13917p;

    /* renamed from: q, reason: collision with root package name */
    private float f13918q;

    /* renamed from: r, reason: collision with root package name */
    private WeakReference f13919r;

    /* renamed from: s, reason: collision with root package name */
    private WeakReference f13920s;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BadgeGravity {
    }

    private BadgeDrawable(Context context, int i2, int i3, int i4, BadgeState.State state) {
        this.f13908c = new WeakReference(context);
        ThemeEnforcement.c(context);
        this.f13911j = new Rect();
        TextDrawableHelper textDrawableHelper = new TextDrawableHelper(this);
        this.f13910i = textDrawableHelper;
        textDrawableHelper.g().setTextAlign(Paint.Align.CENTER);
        BadgeState badgeState = new BadgeState(context, i2, i3, i4, state);
        this.f13912k = badgeState;
        this.f13909h = new MaterialShapeDrawable(ShapeAppearanceModel.b(context, A() ? badgeState.m() : badgeState.i(), A() ? badgeState.l() : badgeState.h()).m());
        N();
    }

    private boolean A() {
        return C() || B();
    }

    private boolean D() {
        FrameLayout j2 = j();
        return j2 != null && j2.getId() == R.id.mtrl_anchor_parent;
    }

    private void E() {
        this.f13910i.g().setAlpha(getAlpha());
        invalidateSelf();
    }

    private void F() {
        ColorStateList valueOf = ColorStateList.valueOf(this.f13912k.e());
        if (this.f13909h.x() != valueOf) {
            this.f13909h.a0(valueOf);
            invalidateSelf();
        }
    }

    private void G() {
        this.f13910i.l(true);
        I();
        T();
        invalidateSelf();
    }

    private void H() {
        WeakReference weakReference = this.f13919r;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        View view = (View) this.f13919r.get();
        WeakReference weakReference2 = this.f13920s;
        S(view, weakReference2 != null ? (FrameLayout) weakReference2.get() : null);
    }

    private void I() {
        Context context = (Context) this.f13908c.get();
        if (context == null) {
            return;
        }
        this.f13909h.setShapeAppearanceModel(ShapeAppearanceModel.b(context, A() ? this.f13912k.m() : this.f13912k.i(), A() ? this.f13912k.l() : this.f13912k.h()).m());
        invalidateSelf();
    }

    private void J() {
        TextAppearance textAppearance;
        Context context = (Context) this.f13908c.get();
        if (context == null || this.f13910i.e() == (textAppearance = new TextAppearance(context, this.f13912k.A()))) {
            return;
        }
        this.f13910i.k(textAppearance, context);
        K();
        T();
        invalidateSelf();
    }

    private void K() {
        this.f13910i.g().setColor(this.f13912k.j());
        invalidateSelf();
    }

    private void L() {
        U();
        this.f13910i.l(true);
        T();
        invalidateSelf();
    }

    private void M() {
        boolean G = this.f13912k.G();
        setVisible(G, false);
        if (!BadgeUtils.f13948a || j() == null || G) {
            return;
        }
        ((ViewGroup) j().getParent()).invalidate();
    }

    private void N() {
        I();
        J();
        L();
        G();
        E();
        F();
        K();
        H();
        T();
        M();
    }

    private void Q(final View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup == null || viewGroup.getId() != R.id.mtrl_anchor_parent) {
            WeakReference weakReference = this.f13920s;
            if (weakReference == null || weakReference.get() != viewGroup) {
                R(view);
                final FrameLayout frameLayout = new FrameLayout(view.getContext());
                frameLayout.setId(R.id.mtrl_anchor_parent);
                frameLayout.setClipChildren(false);
                frameLayout.setClipToPadding(false);
                frameLayout.setLayoutParams(view.getLayoutParams());
                frameLayout.setMinimumWidth(view.getWidth());
                frameLayout.setMinimumHeight(view.getHeight());
                int indexOfChild = viewGroup.indexOfChild(view);
                viewGroup.removeViewAt(indexOfChild);
                view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                frameLayout.addView(view);
                viewGroup.addView(frameLayout, indexOfChild);
                this.f13920s = new WeakReference(frameLayout);
                frameLayout.post(new Runnable() { // from class: com.google.android.material.badge.BadgeDrawable.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BadgeDrawable.this.S(view, frameLayout);
                    }
                });
            }
        }
    }

    private static void R(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.setClipChildren(false);
        viewGroup.setClipToPadding(false);
    }

    private void T() {
        Context context = (Context) this.f13908c.get();
        WeakReference weakReference = this.f13919r;
        View view = weakReference != null ? (View) weakReference.get() : null;
        if (context == null || view == null) {
            return;
        }
        Rect rect = new Rect();
        rect.set(this.f13911j);
        Rect rect2 = new Rect();
        view.getDrawingRect(rect2);
        WeakReference weakReference2 = this.f13920s;
        ViewGroup viewGroup = weakReference2 != null ? (ViewGroup) weakReference2.get() : null;
        if (viewGroup != null || BadgeUtils.f13948a) {
            if (viewGroup == null) {
                viewGroup = (ViewGroup) view.getParent();
            }
            viewGroup.offsetDescendantRectToMyCoords(view, rect2);
        }
        c(rect2, view);
        BadgeUtils.h(this.f13911j, this.f13913l, this.f13914m, this.f13917p, this.f13918q);
        float f2 = this.f13916o;
        if (f2 != -1.0f) {
            this.f13909h.X(f2);
        }
        if (rect.equals(this.f13911j)) {
            return;
        }
        this.f13909h.setBounds(this.f13911j);
    }

    private void U() {
        if (n() != -2) {
            this.f13915n = ((int) Math.pow(10.0d, n() - 1.0d)) - 1;
        } else {
            this.f13915n = o();
        }
    }

    private void b(View view) {
        float f2;
        float f3;
        View j2 = j();
        if (j2 == null) {
            if (!(view.getParent() instanceof View)) {
                return;
            }
            float y = view.getY();
            f3 = view.getX();
            j2 = (View) view.getParent();
            f2 = y;
        } else if (!D()) {
            f2 = 0.0f;
            f3 = 0.0f;
        } else {
            if (!(j2.getParent() instanceof View)) {
                return;
            }
            f2 = j2.getY();
            f3 = j2.getX();
            j2 = (View) j2.getParent();
        }
        float x = x(j2, f2);
        float m2 = m(j2, f3);
        float h2 = h(j2, f2);
        float s2 = s(j2, f3);
        if (x < 0.0f) {
            this.f13914m += Math.abs(x);
        }
        if (m2 < 0.0f) {
            this.f13913l += Math.abs(m2);
        }
        if (h2 > 0.0f) {
            this.f13914m -= Math.abs(h2);
        }
        if (s2 > 0.0f) {
            this.f13913l -= Math.abs(s2);
        }
    }

    private void c(Rect rect, View view) {
        float f2 = A() ? this.f13912k.f13927d : this.f13912k.f13926c;
        this.f13916o = f2;
        if (f2 != -1.0f) {
            this.f13917p = f2;
            this.f13918q = f2;
        } else {
            this.f13917p = Math.round((A() ? this.f13912k.f13930g : this.f13912k.f13928e) / 2.0f);
            this.f13918q = Math.round((A() ? this.f13912k.f13931h : this.f13912k.f13929f) / 2.0f);
        }
        if (A()) {
            String g2 = g();
            this.f13917p = Math.max(this.f13917p, (this.f13910i.h(g2) / 2.0f) + this.f13912k.g());
            float max = Math.max(this.f13918q, (this.f13910i.f(g2) / 2.0f) + this.f13912k.k());
            this.f13918q = max;
            this.f13917p = Math.max(this.f13917p, max);
        }
        int z = z();
        int f3 = this.f13912k.f();
        if (f3 == 8388691 || f3 == 8388693) {
            this.f13914m = rect.bottom - z;
        } else {
            this.f13914m = rect.top + z;
        }
        int y = y();
        int f4 = this.f13912k.f();
        if (f4 == 8388659 || f4 == 8388691) {
            this.f13913l = ViewCompat.v(view) == 0 ? (rect.left - this.f13917p) + y : (rect.right + this.f13917p) - y;
        } else {
            this.f13913l = ViewCompat.v(view) == 0 ? (rect.right + this.f13917p) - y : (rect.left - this.f13917p) + y;
        }
        if (this.f13912k.F()) {
            b(view);
        }
    }

    public static BadgeDrawable d(Context context) {
        return new BadgeDrawable(context, 0, u, t, null);
    }

    static BadgeDrawable e(Context context, BadgeState.State state) {
        return new BadgeDrawable(context, 0, u, t, state);
    }

    private void f(Canvas canvas) {
        String g2 = g();
        if (g2 != null) {
            Rect rect = new Rect();
            this.f13910i.g().getTextBounds(g2, 0, g2.length(), rect);
            float exactCenterY = this.f13914m - rect.exactCenterY();
            canvas.drawText(g2, this.f13913l, rect.bottom <= 0 ? (int) exactCenterY : Math.round(exactCenterY), this.f13910i.g());
        }
    }

    private String g() {
        if (C()) {
            return v();
        }
        if (B()) {
            return q();
        }
        return null;
    }

    private float h(View view, float f2) {
        if (!(view.getParent() instanceof View)) {
            return 0.0f;
        }
        return ((this.f13914m + this.f13918q) - (((View) view.getParent()).getHeight() - view.getY())) + f2;
    }

    private CharSequence k() {
        return this.f13912k.p();
    }

    private float m(View view, float f2) {
        return (this.f13913l - this.f13917p) + view.getX() + f2;
    }

    private String q() {
        if (this.f13915n == -2 || p() <= this.f13915n) {
            return NumberFormat.getInstance(this.f13912k.x()).format(p());
        }
        Context context = (Context) this.f13908c.get();
        return context == null ? "" : String.format(this.f13912k.x(), context.getString(R.string.mtrl_exceed_max_badge_number_suffix), Integer.valueOf(this.f13915n), "+");
    }

    private String r() {
        Context context;
        if (this.f13912k.q() == 0 || (context = (Context) this.f13908c.get()) == null) {
            return null;
        }
        return (this.f13915n == -2 || p() <= this.f13915n) ? context.getResources().getQuantityString(this.f13912k.q(), p(), Integer.valueOf(p())) : context.getString(this.f13912k.n(), Integer.valueOf(this.f13915n));
    }

    private float s(View view, float f2) {
        if (!(view.getParent() instanceof View)) {
            return 0.0f;
        }
        return ((this.f13913l + this.f13917p) - (((View) view.getParent()).getWidth() - view.getX())) + f2;
    }

    private String v() {
        String u2 = u();
        int n2 = n();
        if (n2 == -2 || u2 == null || u2.length() <= n2) {
            return u2;
        }
        Context context = (Context) this.f13908c.get();
        if (context == null) {
            return "";
        }
        return String.format(context.getString(R.string.m3_exceed_max_badge_text_suffix), u2.substring(0, n2 - 1), "…");
    }

    private CharSequence w() {
        CharSequence o2 = this.f13912k.o();
        return o2 != null ? o2 : u();
    }

    private float x(View view, float f2) {
        return (this.f13914m - this.f13918q) + view.getY() + f2;
    }

    private int y() {
        int r2 = A() ? this.f13912k.r() : this.f13912k.s();
        if (this.f13912k.f13934k == 1) {
            r2 += A() ? this.f13912k.f13933j : this.f13912k.f13932i;
        }
        return r2 + this.f13912k.b();
    }

    private int z() {
        int C = this.f13912k.C();
        if (A()) {
            C = this.f13912k.B();
            Context context = (Context) this.f13908c.get();
            if (context != null) {
                C = AnimationUtils.c(C, C - this.f13912k.t(), AnimationUtils.b(0.0f, 1.0f, 0.3f, 1.0f, MaterialResources.f(context) - 1.0f));
            }
        }
        if (this.f13912k.f13934k == 0) {
            C -= Math.round(this.f13918q);
        }
        return C + this.f13912k.c();
    }

    public boolean B() {
        return !this.f13912k.E() && this.f13912k.D();
    }

    public boolean C() {
        return this.f13912k.E();
    }

    void O(int i2) {
        this.f13912k.I(i2);
        T();
    }

    void P(int i2) {
        this.f13912k.J(i2);
        T();
    }

    public void S(View view, FrameLayout frameLayout) {
        this.f13919r = new WeakReference(view);
        boolean z = BadgeUtils.f13948a;
        if (z && frameLayout == null) {
            Q(view);
        } else {
            this.f13920s = new WeakReference(frameLayout);
        }
        if (!z) {
            R(view);
        }
        T();
        invalidateSelf();
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public void a() {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (getBounds().isEmpty() || getAlpha() == 0 || !isVisible()) {
            return;
        }
        this.f13909h.draw(canvas);
        if (A()) {
            f(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f13912k.d();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f13911j.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f13911j.width();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public CharSequence i() {
        if (isVisible()) {
            return C() ? w() : B() ? r() : k();
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    public FrameLayout j() {
        WeakReference weakReference = this.f13920s;
        if (weakReference != null) {
            return (FrameLayout) weakReference.get();
        }
        return null;
    }

    public int l() {
        return this.f13912k.s();
    }

    public int n() {
        return this.f13912k.u();
    }

    public int o() {
        return this.f13912k.v();
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public int p() {
        if (this.f13912k.D()) {
            return this.f13912k.w();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f13912k.K(i2);
        E();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    BadgeState.State t() {
        return this.f13912k.y();
    }

    public String u() {
        return this.f13912k.z();
    }
}
