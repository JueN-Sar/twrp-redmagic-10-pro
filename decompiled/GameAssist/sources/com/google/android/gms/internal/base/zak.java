package com.google.android.gms.internal.base;

import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public final class zak extends Drawable implements Drawable.Callback {

    /* renamed from: c, reason: collision with root package name */
    private int f11372c;

    /* renamed from: h, reason: collision with root package name */
    private long f11373h;

    /* renamed from: i, reason: collision with root package name */
    private int f11374i;

    /* renamed from: j, reason: collision with root package name */
    private int f11375j;

    /* renamed from: k, reason: collision with root package name */
    private int f11376k;

    /* renamed from: l, reason: collision with root package name */
    private int f11377l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f11378m;

    /* renamed from: n, reason: collision with root package name */
    private boolean f11379n;

    /* renamed from: o, reason: collision with root package name */
    private zaj f11380o;

    /* renamed from: p, reason: collision with root package name */
    private Drawable f11381p;

    /* renamed from: q, reason: collision with root package name */
    private Drawable f11382q;

    /* renamed from: r, reason: collision with root package name */
    private boolean f11383r;

    /* renamed from: s, reason: collision with root package name */
    private boolean f11384s;
    private boolean t;
    private int u;

    public zak(Drawable drawable, Drawable drawable2) {
        this(null);
        drawable = drawable == null ? zai.f11368a : drawable;
        this.f11381p = drawable;
        drawable.setCallback(this);
        zaj zajVar = this.f11380o;
        zajVar.f11371b = drawable.getChangingConfigurations() | zajVar.f11371b;
        drawable2 = drawable2 == null ? zai.f11368a : drawable2;
        this.f11382q = drawable2;
        drawable2.setCallback(this);
        this.f11380o.f11371b |= drawable2.getChangingConfigurations();
    }

    public final Drawable a() {
        return this.f11382q;
    }

    public final void b(int i2) {
        this.f11374i = this.f11375j;
        this.f11377l = 0;
        this.f11376k = 250;
        this.f11372c = 1;
        invalidateSelf();
    }

    public final boolean c() {
        if (!this.f11383r) {
            boolean z = false;
            if (this.f11381p.getConstantState() != null && this.f11382q.getConstantState() != null) {
                z = true;
            }
            this.f11384s = z;
            this.f11383r = true;
        }
        return this.f11384s;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x004f, code lost:
    
        if (r0 == 0) goto L22;
     */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void draw(android.graphics.Canvas r8) {
        /*
            r7 = this;
            int r0 = r7.f11372c
            r1 = 2
            r2 = 0
            r3 = 1
            if (r0 == r3) goto L3a
            if (r0 == r1) goto Lb
        L9:
            r4 = r3
            goto L43
        Lb:
            long r0 = r7.f11373h
            r4 = 0
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 < 0) goto L9
            long r0 = android.os.SystemClock.uptimeMillis()
            long r4 = r7.f11373h
            long r0 = r0 - r4
            int r4 = r7.f11376k
            float r4 = (float) r4
            float r0 = (float) r0
            float r0 = r0 / r4
            r1 = 1065353216(0x3f800000, float:1.0)
            int r4 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r4 < 0) goto L27
            r4 = r3
            goto L28
        L27:
            r4 = r2
        L28:
            if (r4 == 0) goto L2c
            r7.f11372c = r2
        L2c:
            float r0 = java.lang.Math.min(r0, r1)
            int r1 = r7.f11374i
            float r1 = (float) r1
            float r1 = r1 * r0
            r0 = 0
            float r1 = r1 + r0
            int r0 = (int) r1
            r7.f11377l = r0
            goto L43
        L3a:
            long r4 = android.os.SystemClock.uptimeMillis()
            r7.f11373h = r4
            r7.f11372c = r1
            r4 = r2
        L43:
            int r0 = r7.f11377l
            boolean r1 = r7.f11378m
            android.graphics.drawable.Drawable r5 = r7.f11381p
            android.graphics.drawable.Drawable r6 = r7.f11382q
            if (r4 == 0) goto L62
            if (r1 == 0) goto L52
            if (r0 != 0) goto L57
            goto L53
        L52:
            r2 = r0
        L53:
            r5.draw(r8)
            r0 = r2
        L57:
            int r7 = r7.f11375j
            if (r0 != r7) goto L61
            r6.setAlpha(r7)
            r6.draw(r8)
        L61:
            return
        L62:
            if (r1 == 0) goto L6b
            int r1 = r7.f11375j
            int r1 = r1 - r0
            r5.setAlpha(r1)
            r2 = r3
        L6b:
            r5.draw(r8)
            if (r2 == 0) goto L75
            int r1 = r7.f11375j
            r5.setAlpha(r1)
        L75:
            if (r0 <= 0) goto L82
            r6.setAlpha(r0)
            r6.draw(r8)
            int r8 = r7.f11375j
            r6.setAlpha(r8)
        L82:
            r7.invalidateSelf()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.base.zak.draw(android.graphics.Canvas):void");
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        zaj zajVar = this.f11380o;
        return zajVar.f11371b | changingConfigurations | zajVar.f11370a;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (!c()) {
            return null;
        }
        this.f11380o.f11370a = getChangingConfigurations();
        return this.f11380o;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return Math.max(this.f11381p.getIntrinsicHeight(), this.f11382q.getIntrinsicHeight());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return Math.max(this.f11381p.getIntrinsicWidth(), this.f11382q.getIntrinsicWidth());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        if (!this.t) {
            this.u = Drawable.resolveOpacity(this.f11381p.getOpacity(), this.f11382q.getOpacity());
            this.t = true;
        }
        return this.u;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        if (!this.f11379n && super.mutate() == this) {
            if (!c()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.f11381p.mutate();
            this.f11382q.mutate();
            this.f11379n = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected final void onBoundsChange(Rect rect) {
        this.f11381p.setBounds(rect);
        this.f11382q.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i2) {
        if (this.f11377l == this.f11375j) {
            this.f11377l = i2;
        }
        this.f11375j = i2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f11381p.setColorFilter(colorFilter);
        this.f11382q.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    zak(zaj zajVar) {
        this.f11372c = 0;
        this.f11375j = 255;
        this.f11377l = 0;
        this.f11378m = true;
        this.f11380o = new zaj(zajVar);
    }
}
