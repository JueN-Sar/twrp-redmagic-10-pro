package com.google.android.material.internal;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.core.math.MathUtils;
import androidx.core.text.TextDirectionHeuristicsCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.StaticLayoutBuilderCompat;
import com.google.android.material.resources.CancelableFontCallback;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.resources.TypefaceUtils;

@RestrictTo
/* loaded from: classes.dex */
public final class CollapsingTextHelper {
    private static final boolean u0 = false;
    private static final Paint v0 = null;
    private Typeface A;
    private Typeface B;
    private Typeface C;
    private CancelableFontCallback D;
    private CancelableFontCallback E;
    private CharSequence G;
    private CharSequence H;
    private boolean I;
    private boolean K;
    private Bitmap L;
    private Paint M;
    private float N;
    private float O;
    private float P;
    private float Q;
    private float R;
    private int S;
    private int[] T;
    private boolean U;
    private final TextPaint V;
    private final TextPaint W;
    private TimeInterpolator X;
    private TimeInterpolator Y;
    private float Z;

    /* renamed from: a, reason: collision with root package name */
    private final View f14692a;
    private float a0;

    /* renamed from: b, reason: collision with root package name */
    private float f14693b;
    private float b0;

    /* renamed from: c, reason: collision with root package name */
    private boolean f14694c;
    private ColorStateList c0;

    /* renamed from: d, reason: collision with root package name */
    private float f14695d;
    private float d0;

    /* renamed from: e, reason: collision with root package name */
    private float f14696e;
    private float e0;

    /* renamed from: f, reason: collision with root package name */
    private int f14697f;
    private float f0;

    /* renamed from: g, reason: collision with root package name */
    private final Rect f14698g;
    private ColorStateList g0;

    /* renamed from: h, reason: collision with root package name */
    private final Rect f14699h;
    private float h0;

    /* renamed from: i, reason: collision with root package name */
    private final RectF f14700i;
    private float i0;
    private float j0;
    private StaticLayout k0;
    private float l0;
    private float m0;

    /* renamed from: n, reason: collision with root package name */
    private ColorStateList f14705n;
    private float n0;

    /* renamed from: o, reason: collision with root package name */
    private ColorStateList f14706o;
    private CharSequence o0;

    /* renamed from: p, reason: collision with root package name */
    private int f14707p;

    /* renamed from: q, reason: collision with root package name */
    private float f14708q;

    /* renamed from: r, reason: collision with root package name */
    private float f14709r;

    /* renamed from: s, reason: collision with root package name */
    private float f14710s;
    private float t;
    private StaticLayoutBuilderConfigurer t0;
    private float u;
    private float v;
    private Typeface w;
    private Typeface x;
    private Typeface y;
    private Typeface z;

    /* renamed from: j, reason: collision with root package name */
    private int f14701j = 16;

    /* renamed from: k, reason: collision with root package name */
    private int f14702k = 16;

    /* renamed from: l, reason: collision with root package name */
    private float f14703l = 15.0f;

    /* renamed from: m, reason: collision with root package name */
    private float f14704m = 15.0f;
    private TextUtils.TruncateAt F = TextUtils.TruncateAt.END;
    private boolean J = true;
    private int p0 = 1;
    private float q0 = 0.0f;
    private float r0 = 1.0f;
    private int s0 = StaticLayoutBuilderCompat.f14764o;

    public CollapsingTextHelper(View view) {
        this.f14692a = view;
        TextPaint textPaint = new TextPaint(129);
        this.V = textPaint;
        this.W = new TextPaint(textPaint);
        this.f14699h = new Rect();
        this.f14698g = new Rect();
        this.f14700i = new RectF();
        this.f14696e = e();
        Y(view.getContext().getResources().getConfiguration());
    }

    private void C0(float f2) {
        h(f2);
        boolean z = u0 && this.N != 1.0f;
        this.K = z;
        if (z) {
            n();
        }
        ViewCompat.Z(this.f14692a);
    }

    private Layout.Alignment M() {
        int b2 = GravityCompat.b(this.f14701j, this.I ? 1 : 0) & 7;
        return b2 != 1 ? b2 != 5 ? this.I ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_NORMAL : this.I ? Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_CENTER;
    }

    private boolean O0() {
        return this.p0 > 1 && (!this.I || this.f14694c) && !this.K;
    }

    private void P(TextPaint textPaint) {
        textPaint.setTextSize(this.f14704m);
        textPaint.setTypeface(this.w);
        textPaint.setLetterSpacing(this.h0);
    }

    private void Q(TextPaint textPaint) {
        textPaint.setTextSize(this.f14703l);
        textPaint.setTypeface(this.z);
        textPaint.setLetterSpacing(this.i0);
    }

    private void S(float f2) {
        if (this.f14694c) {
            this.f14700i.set(f2 < this.f14696e ? this.f14698g : this.f14699h);
            return;
        }
        this.f14700i.left = X(this.f14698g.left, this.f14699h.left, f2, this.X);
        this.f14700i.top = X(this.f14708q, this.f14709r, f2, this.X);
        this.f14700i.right = X(this.f14698g.right, this.f14699h.right, f2, this.X);
        this.f14700i.bottom = X(this.f14698g.bottom, this.f14699h.bottom, f2, this.X);
    }

    private static boolean T(float f2, float f3) {
        return Math.abs(f2 - f3) < 1.0E-5f;
    }

    private boolean U() {
        return ViewCompat.v(this.f14692a) == 1;
    }

    private boolean W(CharSequence charSequence, boolean z) {
        return (z ? TextDirectionHeuristicsCompat.f3225d : TextDirectionHeuristicsCompat.f3224c).isRtl(charSequence, 0, charSequence.length());
    }

    private static float X(float f2, float f3, float f4, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f4 = timeInterpolator.getInterpolation(f4);
        }
        return AnimationUtils.a(f2, f3, f4);
    }

    private float Z(TextPaint textPaint, CharSequence charSequence) {
        return textPaint.measureText(charSequence, 0, charSequence.length());
    }

    private static int a(int i2, int i3, float f2) {
        float f3 = 1.0f - f2;
        return Color.argb(Math.round((Color.alpha(i2) * f3) + (Color.alpha(i3) * f2)), Math.round((Color.red(i2) * f3) + (Color.red(i3) * f2)), Math.round((Color.green(i2) * f3) + (Color.green(i3) * f2)), Math.round((Color.blue(i2) * f3) + (Color.blue(i3) * f2)));
    }

    private void b(boolean z) {
        StaticLayout staticLayout;
        i(1.0f, z);
        CharSequence charSequence = this.H;
        if (charSequence != null && (staticLayout = this.k0) != null) {
            this.o0 = TextUtils.ellipsize(charSequence, this.V, staticLayout.getWidth(), this.F);
        }
        CharSequence charSequence2 = this.o0;
        float f2 = 0.0f;
        if (charSequence2 != null) {
            this.l0 = Z(this.V, charSequence2);
        } else {
            this.l0 = 0.0f;
        }
        int b2 = GravityCompat.b(this.f14702k, this.I ? 1 : 0);
        int i2 = b2 & 112;
        if (i2 == 48) {
            this.f14709r = this.f14699h.top;
        } else if (i2 != 80) {
            this.f14709r = this.f14699h.centerY() - ((this.V.descent() - this.V.ascent()) / 2.0f);
        } else {
            this.f14709r = this.f14699h.bottom + this.V.ascent();
        }
        int i3 = b2 & 8388615;
        if (i3 == 1) {
            this.t = this.f14699h.centerX() - (this.l0 / 2.0f);
        } else if (i3 != 5) {
            this.t = this.f14699h.left;
        } else {
            this.t = this.f14699h.right - this.l0;
        }
        i(0.0f, z);
        float height = this.k0 != null ? r10.getHeight() : 0.0f;
        StaticLayout staticLayout2 = this.k0;
        if (staticLayout2 == null || this.p0 <= 1) {
            CharSequence charSequence3 = this.H;
            if (charSequence3 != null) {
                f2 = Z(this.V, charSequence3);
            }
        } else {
            f2 = staticLayout2.getWidth();
        }
        StaticLayout staticLayout3 = this.k0;
        this.f14707p = staticLayout3 != null ? staticLayout3.getLineCount() : 0;
        int b3 = GravityCompat.b(this.f14701j, this.I ? 1 : 0);
        int i4 = b3 & 112;
        if (i4 == 48) {
            this.f14708q = this.f14698g.top;
        } else if (i4 != 80) {
            this.f14708q = this.f14698g.centerY() - (height / 2.0f);
        } else {
            this.f14708q = (this.f14698g.bottom - height) + this.V.descent();
        }
        int i5 = b3 & 8388615;
        if (i5 == 1) {
            this.f14710s = this.f14698g.centerX() - (f2 / 2.0f);
        } else if (i5 != 5) {
            this.f14710s = this.f14698g.left;
        } else {
            this.f14710s = this.f14698g.right - f2;
        }
        j();
        C0(this.f14693b);
    }

    private void c() {
        g(this.f14693b);
    }

    private static boolean c0(Rect rect, int i2, int i3, int i4, int i5) {
        return rect.left == i2 && rect.top == i3 && rect.right == i4 && rect.bottom == i5;
    }

    private float d(float f2) {
        float f3 = this.f14696e;
        return f2 <= f3 ? AnimationUtils.b(1.0f, 0.0f, this.f14695d, f3, f2) : AnimationUtils.b(0.0f, 1.0f, f3, 1.0f, f2);
    }

    private float e() {
        float f2 = this.f14695d;
        return f2 + ((1.0f - f2) * 0.5f);
    }

    private boolean f(CharSequence charSequence) {
        boolean U = U();
        return this.J ? W(charSequence, U) : U;
    }

    private void g(float f2) {
        float f3;
        S(f2);
        if (!this.f14694c) {
            this.u = X(this.f14710s, this.t, f2, this.X);
            this.v = X(this.f14708q, this.f14709r, f2, this.X);
            C0(f2);
            f3 = f2;
        } else if (f2 < this.f14696e) {
            this.u = this.f14710s;
            this.v = this.f14708q;
            C0(0.0f);
            f3 = 0.0f;
        } else {
            this.u = this.t;
            this.v = this.f14709r - Math.max(0, this.f14697f);
            C0(1.0f);
            f3 = 1.0f;
        }
        TimeInterpolator timeInterpolator = AnimationUtils.f13815b;
        h0(1.0f - X(0.0f, 1.0f, 1.0f - f2, timeInterpolator));
        s0(X(1.0f, 0.0f, f2, timeInterpolator));
        if (this.f14706o != this.f14705n) {
            this.V.setColor(a(y(), w(), f3));
        } else {
            this.V.setColor(w());
        }
        float f4 = this.h0;
        float f5 = this.i0;
        if (f4 != f5) {
            this.V.setLetterSpacing(X(f5, f4, f2, timeInterpolator));
        } else {
            this.V.setLetterSpacing(f4);
        }
        this.P = X(this.d0, this.Z, f2, null);
        this.Q = X(this.e0, this.a0, f2, null);
        this.R = X(this.f0, this.b0, f2, null);
        int a2 = a(x(this.g0), x(this.c0), f2);
        this.S = a2;
        this.V.setShadowLayer(this.P, this.Q, this.R, a2);
        if (this.f14694c) {
            this.V.setAlpha((int) (d(f2) * this.V.getAlpha()));
            TextPaint textPaint = this.V;
            textPaint.setShadowLayer(this.P, this.Q, this.R, MaterialColors.a(this.S, textPaint.getAlpha()));
        }
        ViewCompat.Z(this.f14692a);
    }

    private void h(float f2) {
        i(f2, false);
    }

    private void h0(float f2) {
        this.m0 = f2;
        ViewCompat.Z(this.f14692a);
    }

    private void i(float f2, boolean z) {
        float f3;
        float f4;
        Typeface typeface;
        if (this.G == null) {
            return;
        }
        float width = this.f14699h.width();
        float width2 = this.f14698g.width();
        if (T(f2, 1.0f)) {
            f3 = this.f14704m;
            f4 = this.h0;
            this.N = 1.0f;
            typeface = this.w;
        } else {
            float f5 = this.f14703l;
            float f6 = this.i0;
            Typeface typeface2 = this.z;
            if (T(f2, 0.0f)) {
                this.N = 1.0f;
            } else {
                this.N = X(this.f14703l, this.f14704m, f2, this.Y) / this.f14703l;
            }
            float f7 = this.f14704m / this.f14703l;
            width = (z || this.f14694c || width2 * f7 <= width) ? width2 : Math.min(width / f7, width2);
            f3 = f5;
            f4 = f6;
            typeface = typeface2;
        }
        if (width > 0.0f) {
            boolean z2 = this.O != f3;
            boolean z3 = this.j0 != f4;
            boolean z4 = this.C != typeface;
            StaticLayout staticLayout = this.k0;
            boolean z5 = z2 || z3 || (staticLayout != null && (width > ((float) staticLayout.getWidth()) ? 1 : (width == ((float) staticLayout.getWidth()) ? 0 : -1)) != 0) || z4 || this.U;
            this.O = f3;
            this.j0 = f4;
            this.C = typeface;
            this.U = false;
            this.V.setLinearText(this.N != 1.0f);
            r5 = z5;
        }
        if (this.H == null || r5) {
            this.V.setTextSize(this.O);
            this.V.setTypeface(this.C);
            this.V.setLetterSpacing(this.j0);
            this.I = f(this.G);
            StaticLayout k2 = k(O0() ? this.p0 : 1, width, this.I);
            this.k0 = k2;
            this.H = k2.getText();
        }
    }

    private void j() {
        Bitmap bitmap = this.L;
        if (bitmap != null) {
            bitmap.recycle();
            this.L = null;
        }
    }

    private StaticLayout k(int i2, float f2, boolean z) {
        StaticLayout staticLayout;
        try {
            staticLayout = StaticLayoutBuilderCompat.b(this.G, this.V, (int) f2).d(this.F).g(z).c(i2 == 1 ? Layout.Alignment.ALIGN_NORMAL : M()).f(false).i(i2).h(this.q0, this.r0).e(this.s0).j(this.t0).a();
        } catch (StaticLayoutBuilderCompat.StaticLayoutBuilderCompatException e2) {
            Log.e("CollapsingTextHelper", e2.getCause().getMessage(), e2);
            staticLayout = null;
        }
        return (StaticLayout) Preconditions.h(staticLayout);
    }

    private void m(Canvas canvas, float f2, float f3) {
        int alpha = this.V.getAlpha();
        canvas.translate(f2, f3);
        if (!this.f14694c) {
            this.V.setAlpha((int) (this.n0 * alpha));
            TextPaint textPaint = this.V;
            textPaint.setShadowLayer(this.P, this.Q, this.R, MaterialColors.a(this.S, textPaint.getAlpha()));
            this.k0.draw(canvas);
        }
        if (!this.f14694c) {
            this.V.setAlpha((int) (this.m0 * alpha));
        }
        TextPaint textPaint2 = this.V;
        textPaint2.setShadowLayer(this.P, this.Q, this.R, MaterialColors.a(this.S, textPaint2.getAlpha()));
        int lineBaseline = this.k0.getLineBaseline(0);
        CharSequence charSequence = this.o0;
        float f4 = lineBaseline;
        canvas.drawText(charSequence, 0, charSequence.length(), 0.0f, f4, this.V);
        this.V.setShadowLayer(this.P, this.Q, this.R, this.S);
        if (this.f14694c) {
            return;
        }
        String trim = this.o0.toString().trim();
        if (trim.endsWith("…")) {
            trim = trim.substring(0, trim.length() - 1);
        }
        String str = trim;
        this.V.setAlpha(alpha);
        canvas.drawText(str, 0, Math.min(this.k0.getLineEnd(0), str.length()), 0.0f, f4, (Paint) this.V);
    }

    private boolean m0(Typeface typeface) {
        CancelableFontCallback cancelableFontCallback = this.E;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.c();
        }
        if (this.y == typeface) {
            return false;
        }
        this.y = typeface;
        Typeface b2 = TypefaceUtils.b(this.f14692a.getContext().getResources().getConfiguration(), typeface);
        this.x = b2;
        if (b2 == null) {
            b2 = this.y;
        }
        this.w = b2;
        return true;
    }

    private void n() {
        if (this.L != null || this.f14698g.isEmpty() || TextUtils.isEmpty(this.H)) {
            return;
        }
        g(0.0f);
        int width = this.k0.getWidth();
        int height = this.k0.getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        this.L = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.k0.draw(new Canvas(this.L));
        if (this.M == null) {
            this.M = new Paint(3);
        }
    }

    private float s(int i2, int i3) {
        return (i3 == 17 || (i3 & 7) == 1) ? (i2 / 2.0f) - (this.l0 / 2.0f) : ((i3 & 8388613) == 8388613 || (i3 & 5) == 5) ? this.I ? this.f14699h.left : this.f14699h.right - this.l0 : this.I ? this.f14699h.right - this.l0 : this.f14699h.left;
    }

    private void s0(float f2) {
        this.n0 = f2;
        ViewCompat.Z(this.f14692a);
    }

    private float t(RectF rectF, int i2, int i3) {
        if (i3 == 17 || (i3 & 7) == 1) {
            return (i2 / 2.0f) + (this.l0 / 2.0f);
        }
        if ((i3 & 8388613) == 8388613 || (i3 & 5) == 5) {
            return this.I ? rectF.left + this.l0 : this.f14699h.right;
        }
        if (this.I) {
            return this.f14699h.right;
        }
        return this.l0 + rectF.left;
    }

    private int x(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.T;
        return iArr != null ? colorStateList.getColorForState(iArr, 0) : colorStateList.getDefaultColor();
    }

    private boolean x0(Typeface typeface) {
        CancelableFontCallback cancelableFontCallback = this.D;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.c();
        }
        if (this.B == typeface) {
            return false;
        }
        this.B = typeface;
        Typeface b2 = TypefaceUtils.b(this.f14692a.getContext().getResources().getConfiguration(), typeface);
        this.A = b2;
        if (b2 == null) {
            b2 = this.B;
        }
        this.z = b2;
        return true;
    }

    private int y() {
        return x(this.f14705n);
    }

    public float A() {
        Q(this.W);
        return (-this.W.ascent()) + this.W.descent();
    }

    public void A0(float f2) {
        this.f14695d = f2;
        this.f14696e = e();
    }

    public int B() {
        return this.f14701j;
    }

    public void B0(int i2) {
        this.s0 = i2;
    }

    public float C() {
        Q(this.W);
        return -this.W.ascent();
    }

    public float D() {
        return this.f14703l;
    }

    public void D0(float f2) {
        this.q0 = f2;
    }

    public Typeface E() {
        Typeface typeface = this.z;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public void E0(float f2) {
        this.r0 = f2;
    }

    public float F() {
        return this.f14693b;
    }

    public void F0(int i2) {
        if (i2 != this.p0) {
            this.p0 = i2;
            j();
            a0();
        }
    }

    public float G() {
        return this.f14696e;
    }

    public void G0(TimeInterpolator timeInterpolator) {
        this.X = timeInterpolator;
        a0();
    }

    public int H() {
        return this.s0;
    }

    public void H0(boolean z) {
        this.J = z;
    }

    public int I() {
        StaticLayout staticLayout = this.k0;
        if (staticLayout != null) {
            return staticLayout.getLineCount();
        }
        return 0;
    }

    public final boolean I0(int[] iArr) {
        this.T = iArr;
        if (!V()) {
            return false;
        }
        a0();
        return true;
    }

    public float J() {
        return this.k0.getSpacingAdd();
    }

    public void J0(StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer) {
        if (this.t0 != staticLayoutBuilderConfigurer) {
            this.t0 = staticLayoutBuilderConfigurer;
            b0(true);
        }
    }

    public float K() {
        return this.k0.getSpacingMultiplier();
    }

    public void K0(CharSequence charSequence) {
        if (charSequence == null || !TextUtils.equals(this.G, charSequence)) {
            this.G = charSequence;
            this.H = null;
            j();
            a0();
        }
    }

    public int L() {
        return this.p0;
    }

    public void L0(TimeInterpolator timeInterpolator) {
        this.Y = timeInterpolator;
        a0();
    }

    public void M0(TextUtils.TruncateAt truncateAt) {
        this.F = truncateAt;
        a0();
    }

    public TimeInterpolator N() {
        return this.X;
    }

    public void N0(Typeface typeface) {
        boolean m0 = m0(typeface);
        boolean x0 = x0(typeface);
        if (m0 || x0) {
            a0();
        }
    }

    public CharSequence O() {
        return this.G;
    }

    public TextUtils.TruncateAt R() {
        return this.F;
    }

    public final boolean V() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.f14706o;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.f14705n) != null && colorStateList.isStateful());
    }

    public void Y(Configuration configuration) {
        Typeface typeface = this.y;
        if (typeface != null) {
            this.x = TypefaceUtils.b(configuration, typeface);
        }
        Typeface typeface2 = this.B;
        if (typeface2 != null) {
            this.A = TypefaceUtils.b(configuration, typeface2);
        }
        Typeface typeface3 = this.x;
        if (typeface3 == null) {
            typeface3 = this.y;
        }
        this.w = typeface3;
        Typeface typeface4 = this.A;
        if (typeface4 == null) {
            typeface4 = this.B;
        }
        this.z = typeface4;
        b0(true);
    }

    public void a0() {
        b0(false);
    }

    public void b0(boolean z) {
        if ((this.f14692a.getHeight() <= 0 || this.f14692a.getWidth() <= 0) && !z) {
            return;
        }
        b(z);
        c();
    }

    public void d0(ColorStateList colorStateList) {
        if (this.f14706o == colorStateList && this.f14705n == colorStateList) {
            return;
        }
        this.f14706o = colorStateList;
        this.f14705n = colorStateList;
        a0();
    }

    public void e0(int i2, int i3, int i4, int i5) {
        if (c0(this.f14699h, i2, i3, i4, i5)) {
            return;
        }
        this.f14699h.set(i2, i3, i4, i5);
        this.U = true;
    }

    public void f0(Rect rect) {
        e0(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void g0(int i2) {
        TextAppearance textAppearance = new TextAppearance(this.f14692a.getContext(), i2);
        if (textAppearance.h() != null) {
            this.f14706o = textAppearance.h();
        }
        if (textAppearance.i() != 0.0f) {
            this.f14704m = textAppearance.i();
        }
        ColorStateList colorStateList = textAppearance.f14955c;
        if (colorStateList != null) {
            this.c0 = colorStateList;
        }
        this.a0 = textAppearance.f14960h;
        this.b0 = textAppearance.f14961i;
        this.Z = textAppearance.f14962j;
        this.h0 = textAppearance.f14964l;
        CancelableFontCallback cancelableFontCallback = this.E;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.c();
        }
        this.E = new CancelableFontCallback(new CancelableFontCallback.ApplyFont() { // from class: com.google.android.material.internal.CollapsingTextHelper.1
            @Override // com.google.android.material.resources.CancelableFontCallback.ApplyFont
            public void a(Typeface typeface) {
                CollapsingTextHelper.this.l0(typeface);
            }
        }, textAppearance.e());
        textAppearance.g(this.f14692a.getContext(), this.E);
        a0();
    }

    public void i0(ColorStateList colorStateList) {
        if (this.f14706o != colorStateList) {
            this.f14706o = colorStateList;
            a0();
        }
    }

    public void j0(int i2) {
        if (this.f14702k != i2) {
            this.f14702k = i2;
            a0();
        }
    }

    public void k0(float f2) {
        if (this.f14704m != f2) {
            this.f14704m = f2;
            a0();
        }
    }

    public void l(Canvas canvas) {
        int save = canvas.save();
        if (this.H == null || this.f14700i.width() <= 0.0f || this.f14700i.height() <= 0.0f) {
            return;
        }
        this.V.setTextSize(this.O);
        float f2 = this.u;
        float f3 = this.v;
        boolean z = this.K && this.L != null;
        float f4 = this.N;
        if (f4 != 1.0f && !this.f14694c) {
            canvas.scale(f4, f4, f2, f3);
        }
        if (z) {
            canvas.drawBitmap(this.L, f2, f3, this.M);
            canvas.restoreToCount(save);
            return;
        }
        if (!O0() || (this.f14694c && this.f14693b <= this.f14696e)) {
            canvas.translate(f2, f3);
            this.k0.draw(canvas);
        } else {
            m(canvas, this.u - this.k0.getLineStart(0), f3);
        }
        canvas.restoreToCount(save);
    }

    public void l0(Typeface typeface) {
        if (m0(typeface)) {
            a0();
        }
    }

    public void n0(int i2) {
        this.f14697f = i2;
    }

    public void o(RectF rectF, int i2, int i3) {
        this.I = f(this.G);
        rectF.left = Math.max(s(i2, i3), this.f14699h.left);
        rectF.top = this.f14699h.top;
        rectF.right = Math.min(t(rectF, i2, i3), this.f14699h.right);
        rectF.bottom = this.f14699h.top + r();
    }

    public void o0(int i2, int i3, int i4, int i5) {
        if (c0(this.f14698g, i2, i3, i4, i5)) {
            return;
        }
        this.f14698g.set(i2, i3, i4, i5);
        this.U = true;
    }

    public ColorStateList p() {
        return this.f14706o;
    }

    public void p0(Rect rect) {
        o0(rect.left, rect.top, rect.right, rect.bottom);
    }

    public int q() {
        return this.f14702k;
    }

    public void q0(float f2) {
        if (this.i0 != f2) {
            this.i0 = f2;
            a0();
        }
    }

    public float r() {
        P(this.W);
        return -this.W.ascent();
    }

    public void r0(int i2) {
        TextAppearance textAppearance = new TextAppearance(this.f14692a.getContext(), i2);
        if (textAppearance.h() != null) {
            this.f14705n = textAppearance.h();
        }
        if (textAppearance.i() != 0.0f) {
            this.f14703l = textAppearance.i();
        }
        ColorStateList colorStateList = textAppearance.f14955c;
        if (colorStateList != null) {
            this.g0 = colorStateList;
        }
        this.e0 = textAppearance.f14960h;
        this.f0 = textAppearance.f14961i;
        this.d0 = textAppearance.f14962j;
        this.i0 = textAppearance.f14964l;
        CancelableFontCallback cancelableFontCallback = this.D;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.c();
        }
        this.D = new CancelableFontCallback(new CancelableFontCallback.ApplyFont() { // from class: com.google.android.material.internal.CollapsingTextHelper.2
            @Override // com.google.android.material.resources.CancelableFontCallback.ApplyFont
            public void a(Typeface typeface) {
                CollapsingTextHelper.this.w0(typeface);
            }
        }, textAppearance.e());
        textAppearance.g(this.f14692a.getContext(), this.D);
        a0();
    }

    public void t0(ColorStateList colorStateList) {
        if (this.f14705n != colorStateList) {
            this.f14705n = colorStateList;
            a0();
        }
    }

    public float u() {
        return this.f14704m;
    }

    public void u0(int i2) {
        if (this.f14701j != i2) {
            this.f14701j = i2;
            a0();
        }
    }

    public Typeface v() {
        Typeface typeface = this.w;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public void v0(float f2) {
        if (this.f14703l != f2) {
            this.f14703l = f2;
            a0();
        }
    }

    public int w() {
        return x(this.f14706o);
    }

    public void w0(Typeface typeface) {
        if (x0(typeface)) {
            a0();
        }
    }

    public void y0(float f2) {
        float a2 = MathUtils.a(f2, 0.0f, 1.0f);
        if (a2 != this.f14693b) {
            this.f14693b = a2;
            c();
        }
    }

    public int z() {
        return this.f14707p;
    }

    public void z0(boolean z) {
        this.f14694c = z;
    }
}
