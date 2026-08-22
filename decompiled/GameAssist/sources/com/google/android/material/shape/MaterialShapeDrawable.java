package com.google.android.material.shape;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.core.graphics.drawable.TintAwareDrawable;
import androidx.core.util.ObjectsCompat;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.shadow.ShadowRenderer;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.ShapePath;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.BitSet;

/* loaded from: classes.dex */
public class MaterialShapeDrawable extends Drawable implements TintAwareDrawable, Shapeable {
    private static final String D = "MaterialShapeDrawable";
    private static final Paint E;
    private int A;
    private final RectF B;
    private boolean C;

    /* renamed from: c, reason: collision with root package name */
    private MaterialShapeDrawableState f15094c;

    /* renamed from: h, reason: collision with root package name */
    private final ShapePath.ShadowCompatOperation[] f15095h;

    /* renamed from: i, reason: collision with root package name */
    private final ShapePath.ShadowCompatOperation[] f15096i;

    /* renamed from: j, reason: collision with root package name */
    private final BitSet f15097j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f15098k;

    /* renamed from: l, reason: collision with root package name */
    private final Matrix f15099l;

    /* renamed from: m, reason: collision with root package name */
    private final Path f15100m;

    /* renamed from: n, reason: collision with root package name */
    private final Path f15101n;

    /* renamed from: o, reason: collision with root package name */
    private final RectF f15102o;

    /* renamed from: p, reason: collision with root package name */
    private final RectF f15103p;

    /* renamed from: q, reason: collision with root package name */
    private final Region f15104q;

    /* renamed from: r, reason: collision with root package name */
    private final Region f15105r;

    /* renamed from: s, reason: collision with root package name */
    private ShapeAppearanceModel f15106s;
    private final Paint t;
    private final Paint u;
    private final ShadowRenderer v;
    private final ShapeAppearancePathProvider.PathListener w;
    private final ShapeAppearancePathProvider x;
    private PorterDuffColorFilter y;
    private PorterDuffColorFilter z;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CompatibilityShadowMode {
    }

    static {
        Paint paint = new Paint(1);
        E = paint;
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public MaterialShapeDrawable() {
        this(new ShapeAppearanceModel());
    }

    private float F() {
        if (O()) {
            return this.u.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    private boolean M() {
        MaterialShapeDrawableState materialShapeDrawableState = this.f15094c;
        int i2 = materialShapeDrawableState.f15126q;
        return i2 != 1 && materialShapeDrawableState.f15127r > 0 && (i2 == 2 || W());
    }

    private boolean N() {
        Paint.Style style = this.f15094c.v;
        return style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL;
    }

    private boolean O() {
        Paint.Style style = this.f15094c.v;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.u.getStrokeWidth() > 0.0f;
    }

    private void Q() {
        super.invalidateSelf();
    }

    private void T(Canvas canvas) {
        if (M()) {
            canvas.save();
            V(canvas);
            if (!this.C) {
                n(canvas);
                canvas.restore();
                return;
            }
            int width = (int) (this.B.width() - getBounds().width());
            int height = (int) (this.B.height() - getBounds().height());
            if (width < 0 || height < 0) {
                throw new IllegalStateException("Invalid shadow bounds. Check that the treatments result in a valid path.");
            }
            Bitmap createBitmap = Bitmap.createBitmap(((int) this.B.width()) + (this.f15094c.f15127r * 2) + width, ((int) this.B.height()) + (this.f15094c.f15127r * 2) + height, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap);
            float f2 = (getBounds().left - this.f15094c.f15127r) - width;
            float f3 = (getBounds().top - this.f15094c.f15127r) - height;
            canvas2.translate(-f2, -f3);
            n(canvas2);
            canvas.drawBitmap(createBitmap, f2, f3, (Paint) null);
            createBitmap.recycle();
            canvas.restore();
        }
    }

    private static int U(int i2, int i3) {
        return (i2 * (i3 + (i3 >>> 7))) >>> 8;
    }

    private void V(Canvas canvas) {
        canvas.translate(B(), C());
    }

    private PorterDuffColorFilter f(Paint paint, boolean z) {
        if (!z) {
            return null;
        }
        int color = paint.getColor();
        int l2 = l(color);
        this.A = l2;
        if (l2 != color) {
            return new PorterDuffColorFilter(l2, PorterDuff.Mode.SRC_IN);
        }
        return null;
    }

    private void g(RectF rectF, Path path) {
        h(rectF, path);
        if (this.f15094c.f15119j != 1.0f) {
            this.f15099l.reset();
            Matrix matrix = this.f15099l;
            float f2 = this.f15094c.f15119j;
            matrix.setScale(f2, f2, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.f15099l);
        }
        path.computeBounds(this.B, true);
    }

    private void i() {
        final float f2 = -F();
        ShapeAppearanceModel y = getShapeAppearanceModel().y(new ShapeAppearanceModel.CornerSizeUnaryOperator() { // from class: com.google.android.material.shape.MaterialShapeDrawable.2
            @Override // com.google.android.material.shape.ShapeAppearanceModel.CornerSizeUnaryOperator
            public CornerSize a(CornerSize cornerSize) {
                return cornerSize instanceof RelativeCornerSize ? cornerSize : new AdjustedCornerSize(f2, cornerSize);
            }
        });
        this.f15106s = y;
        this.x.d(y, this.f15094c.f15120k, v(), this.f15101n);
    }

    private PorterDuffColorFilter j(ColorStateList colorStateList, PorterDuff.Mode mode, boolean z) {
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z) {
            colorForState = l(colorForState);
        }
        this.A = colorForState;
        return new PorterDuffColorFilter(colorForState, mode);
    }

    private PorterDuffColorFilter k(ColorStateList colorStateList, PorterDuff.Mode mode, Paint paint, boolean z) {
        return (colorStateList == null || mode == null) ? f(paint, z) : j(colorStateList, mode, z);
    }

    public static MaterialShapeDrawable m(Context context, float f2, ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(MaterialColors.c(context, R.attr.colorSurface, MaterialShapeDrawable.class.getSimpleName()));
        }
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        materialShapeDrawable.P(context);
        materialShapeDrawable.a0(colorStateList);
        materialShapeDrawable.Z(f2);
        return materialShapeDrawable;
    }

    private void n(Canvas canvas) {
        if (this.f15097j.cardinality() > 0) {
            Log.w(D, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.f15094c.f15128s != 0) {
            canvas.drawPath(this.f15100m, this.v.d());
        }
        for (int i2 = 0; i2 < 4; i2++) {
            this.f15095h[i2].b(this.v, this.f15094c.f15127r, canvas);
            this.f15096i[i2].b(this.v, this.f15094c.f15127r, canvas);
        }
        if (this.C) {
            int B = B();
            int C = C();
            canvas.translate(-B, -C);
            canvas.drawPath(this.f15100m, E);
            canvas.translate(B, C);
        }
    }

    private boolean n0(int[] iArr) {
        boolean z;
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        if (this.f15094c.f15113d == null || color2 == (colorForState2 = this.f15094c.f15113d.getColorForState(iArr, (color2 = this.t.getColor())))) {
            z = false;
        } else {
            this.t.setColor(colorForState2);
            z = true;
        }
        if (this.f15094c.f15114e == null || color == (colorForState = this.f15094c.f15114e.getColorForState(iArr, (color = this.u.getColor())))) {
            return z;
        }
        this.u.setColor(colorForState);
        return true;
    }

    private void o(Canvas canvas) {
        q(canvas, this.t, this.f15100m, this.f15094c.f15110a, u());
    }

    private boolean o0() {
        PorterDuffColorFilter porterDuffColorFilter = this.y;
        PorterDuffColorFilter porterDuffColorFilter2 = this.z;
        MaterialShapeDrawableState materialShapeDrawableState = this.f15094c;
        this.y = k(materialShapeDrawableState.f15116g, materialShapeDrawableState.f15117h, this.t, true);
        MaterialShapeDrawableState materialShapeDrawableState2 = this.f15094c;
        this.z = k(materialShapeDrawableState2.f15115f, materialShapeDrawableState2.f15117h, this.u, false);
        MaterialShapeDrawableState materialShapeDrawableState3 = this.f15094c;
        if (materialShapeDrawableState3.u) {
            this.v.e(materialShapeDrawableState3.f15116g.getColorForState(getState(), 0));
        }
        return (ObjectsCompat.a(porterDuffColorFilter, this.y) && ObjectsCompat.a(porterDuffColorFilter2, this.z)) ? false : true;
    }

    private void p0() {
        float L = L();
        this.f15094c.f15127r = (int) Math.ceil(0.75f * L);
        this.f15094c.f15128s = (int) Math.ceil(L * 0.25f);
        o0();
        Q();
    }

    private void q(Canvas canvas, Paint paint, Path path, ShapeAppearanceModel shapeAppearanceModel, RectF rectF) {
        if (!shapeAppearanceModel.u(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float a2 = shapeAppearanceModel.t().a(rectF) * this.f15094c.f15120k;
            canvas.drawRoundRect(rectF, a2, a2, paint);
        }
    }

    private RectF v() {
        this.f15103p.set(u());
        float F = F();
        this.f15103p.inset(F, F);
        return this.f15103p;
    }

    public int A() {
        return this.A;
    }

    public int B() {
        MaterialShapeDrawableState materialShapeDrawableState = this.f15094c;
        return (int) (materialShapeDrawableState.f15128s * Math.sin(Math.toRadians(materialShapeDrawableState.t)));
    }

    public int C() {
        MaterialShapeDrawableState materialShapeDrawableState = this.f15094c;
        return (int) (materialShapeDrawableState.f15128s * Math.cos(Math.toRadians(materialShapeDrawableState.t)));
    }

    public int D() {
        return this.f15094c.f15127r;
    }

    public ColorStateList E() {
        return this.f15094c.f15114e;
    }

    public float G() {
        return this.f15094c.f15121l;
    }

    public ColorStateList H() {
        return this.f15094c.f15116g;
    }

    public float I() {
        return this.f15094c.f15110a.r().a(u());
    }

    public float J() {
        return this.f15094c.f15110a.t().a(u());
    }

    public float K() {
        return this.f15094c.f15125p;
    }

    public float L() {
        return w() + K();
    }

    public void P(Context context) {
        this.f15094c.f15111b = new ElevationOverlayProvider(context);
        p0();
    }

    public boolean R() {
        ElevationOverlayProvider elevationOverlayProvider = this.f15094c.f15111b;
        return elevationOverlayProvider != null && elevationOverlayProvider.e();
    }

    public boolean S() {
        return this.f15094c.f15110a.u(u());
    }

    public boolean W() {
        if (S()) {
            return false;
        }
        this.f15100m.isConvex();
        return false;
    }

    public void X(float f2) {
        setShapeAppearanceModel(this.f15094c.f15110a.w(f2));
    }

    public void Y(CornerSize cornerSize) {
        setShapeAppearanceModel(this.f15094c.f15110a.x(cornerSize));
    }

    public void Z(float f2) {
        MaterialShapeDrawableState materialShapeDrawableState = this.f15094c;
        if (materialShapeDrawableState.f15124o != f2) {
            materialShapeDrawableState.f15124o = f2;
            p0();
        }
    }

    public void a0(ColorStateList colorStateList) {
        MaterialShapeDrawableState materialShapeDrawableState = this.f15094c;
        if (materialShapeDrawableState.f15113d != colorStateList) {
            materialShapeDrawableState.f15113d = colorStateList;
            onStateChange(getState());
        }
    }

    public void b0(float f2) {
        MaterialShapeDrawableState materialShapeDrawableState = this.f15094c;
        if (materialShapeDrawableState.f15120k != f2) {
            materialShapeDrawableState.f15120k = f2;
            this.f15098k = true;
            invalidateSelf();
        }
    }

    public void c0(int i2, int i3, int i4, int i5) {
        MaterialShapeDrawableState materialShapeDrawableState = this.f15094c;
        if (materialShapeDrawableState.f15118i == null) {
            materialShapeDrawableState.f15118i = new Rect();
        }
        this.f15094c.f15118i.set(i2, i3, i4, i5);
        invalidateSelf();
    }

    public void d0(Paint.Style style) {
        this.f15094c.v = style;
        Q();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.t.setColorFilter(this.y);
        int alpha = this.t.getAlpha();
        this.t.setAlpha(U(alpha, this.f15094c.f15122m));
        this.u.setColorFilter(this.z);
        this.u.setStrokeWidth(this.f15094c.f15121l);
        int alpha2 = this.u.getAlpha();
        this.u.setAlpha(U(alpha2, this.f15094c.f15122m));
        if (this.f15098k) {
            i();
            g(u(), this.f15100m);
            this.f15098k = false;
        }
        T(canvas);
        if (N()) {
            o(canvas);
        }
        if (O()) {
            r(canvas);
        }
        this.t.setAlpha(alpha);
        this.u.setAlpha(alpha2);
    }

    public void e0(float f2) {
        MaterialShapeDrawableState materialShapeDrawableState = this.f15094c;
        if (materialShapeDrawableState.f15123n != f2) {
            materialShapeDrawableState.f15123n = f2;
            p0();
        }
    }

    public void f0(boolean z) {
        this.C = z;
    }

    public void g0(int i2) {
        this.v.e(i2);
        this.f15094c.u = false;
        Q();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f15094c.f15122m;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f15094c;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        if (this.f15094c.f15126q == 2) {
            return;
        }
        if (S()) {
            outline.setRoundRect(getBounds(), I() * this.f15094c.f15120k);
        } else {
            g(u(), this.f15100m);
            DrawableUtils.l(outline, this.f15100m);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        Rect rect2 = this.f15094c.f15118i;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    @Override // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.f15094c.f15110a;
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        this.f15104q.set(getBounds());
        g(u(), this.f15100m);
        this.f15105r.setPath(this.f15100m, this.f15104q);
        this.f15104q.op(this.f15105r, Region.Op.DIFFERENCE);
        return this.f15104q;
    }

    protected final void h(RectF rectF, Path path) {
        ShapeAppearancePathProvider shapeAppearancePathProvider = this.x;
        MaterialShapeDrawableState materialShapeDrawableState = this.f15094c;
        shapeAppearancePathProvider.e(materialShapeDrawableState.f15110a, materialShapeDrawableState.f15120k, rectF, this.w, path);
    }

    public void h0(int i2) {
        MaterialShapeDrawableState materialShapeDrawableState = this.f15094c;
        if (materialShapeDrawableState.t != i2) {
            materialShapeDrawableState.t = i2;
            Q();
        }
    }

    public void i0(int i2) {
        MaterialShapeDrawableState materialShapeDrawableState = this.f15094c;
        if (materialShapeDrawableState.f15126q != i2) {
            materialShapeDrawableState.f15126q = i2;
            Q();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.f15098k = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.f15094c.f15116g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.f15094c.f15115f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.f15094c.f15114e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.f15094c.f15113d) != null && colorStateList4.isStateful())));
    }

    public void j0(float f2, int i2) {
        m0(f2);
        l0(ColorStateList.valueOf(i2));
    }

    public void k0(float f2, ColorStateList colorStateList) {
        m0(f2);
        l0(colorStateList);
    }

    protected int l(int i2) {
        float L = L() + z();
        ElevationOverlayProvider elevationOverlayProvider = this.f15094c.f15111b;
        return elevationOverlayProvider != null ? elevationOverlayProvider.c(i2, L) : i2;
    }

    public void l0(ColorStateList colorStateList) {
        MaterialShapeDrawableState materialShapeDrawableState = this.f15094c;
        if (materialShapeDrawableState.f15114e != colorStateList) {
            materialShapeDrawableState.f15114e = colorStateList;
            onStateChange(getState());
        }
    }

    public void m0(float f2) {
        this.f15094c.f15121l = f2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.f15094c = new MaterialShapeDrawableState(this.f15094c);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.f15098k = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    protected boolean onStateChange(int[] iArr) {
        boolean z = n0(iArr) || o0();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    protected void p(Canvas canvas, Paint paint, Path path, RectF rectF) {
        q(canvas, paint, path, this.f15094c.f15110a, rectF);
    }

    protected void r(Canvas canvas) {
        q(canvas, this.u, this.f15101n, this.f15106s, v());
    }

    public float s() {
        return this.f15094c.f15110a.j().a(u());
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        MaterialShapeDrawableState materialShapeDrawableState = this.f15094c;
        if (materialShapeDrawableState.f15122m != i2) {
            materialShapeDrawableState.f15122m = i2;
            Q();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f15094c.f15112c = colorFilter;
        Q();
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        this.f15094c.f15110a = shapeAppearanceModel;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i2) {
        setTintList(ColorStateList.valueOf(i2));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.f15094c.f15116g = colorStateList;
        o0();
        Q();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        MaterialShapeDrawableState materialShapeDrawableState = this.f15094c;
        if (materialShapeDrawableState.f15117h != mode) {
            materialShapeDrawableState.f15117h = mode;
            o0();
            Q();
        }
    }

    public float t() {
        return this.f15094c.f15110a.l().a(u());
    }

    protected RectF u() {
        this.f15102o.set(getBounds());
        return this.f15102o;
    }

    public float w() {
        return this.f15094c.f15124o;
    }

    public ColorStateList x() {
        return this.f15094c.f15113d;
    }

    public float y() {
        return this.f15094c.f15120k;
    }

    public float z() {
        return this.f15094c.f15123n;
    }

    public MaterialShapeDrawable(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        this(ShapeAppearanceModel.e(context, attributeSet, i2, i3).m());
    }

    public MaterialShapeDrawable(ShapeAppearanceModel shapeAppearanceModel) {
        this(new MaterialShapeDrawableState(shapeAppearanceModel, null));
    }

    protected MaterialShapeDrawable(MaterialShapeDrawableState materialShapeDrawableState) {
        ShapeAppearancePathProvider shapeAppearancePathProvider;
        this.f15095h = new ShapePath.ShadowCompatOperation[4];
        this.f15096i = new ShapePath.ShadowCompatOperation[4];
        this.f15097j = new BitSet(8);
        this.f15099l = new Matrix();
        this.f15100m = new Path();
        this.f15101n = new Path();
        this.f15102o = new RectF();
        this.f15103p = new RectF();
        this.f15104q = new Region();
        this.f15105r = new Region();
        Paint paint = new Paint(1);
        this.t = paint;
        Paint paint2 = new Paint(1);
        this.u = paint2;
        this.v = new ShadowRenderer();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            shapeAppearancePathProvider = ShapeAppearancePathProvider.k();
        } else {
            shapeAppearancePathProvider = new ShapeAppearancePathProvider();
        }
        this.x = shapeAppearancePathProvider;
        this.B = new RectF();
        this.C = true;
        this.f15094c = materialShapeDrawableState;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        o0();
        n0(getState());
        this.w = new ShapeAppearancePathProvider.PathListener() { // from class: com.google.android.material.shape.MaterialShapeDrawable.1
            @Override // com.google.android.material.shape.ShapeAppearancePathProvider.PathListener
            public void a(ShapePath shapePath, Matrix matrix, int i2) {
                MaterialShapeDrawable.this.f15097j.set(i2, shapePath.e());
                MaterialShapeDrawable.this.f15095h[i2] = shapePath.f(matrix);
            }

            @Override // com.google.android.material.shape.ShapeAppearancePathProvider.PathListener
            public void b(ShapePath shapePath, Matrix matrix, int i2) {
                MaterialShapeDrawable.this.f15097j.set(i2 + 4, shapePath.e());
                MaterialShapeDrawable.this.f15096i[i2] = shapePath.f(matrix);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @RestrictTo
    public static class MaterialShapeDrawableState extends Drawable.ConstantState {

        /* renamed from: a, reason: collision with root package name */
        ShapeAppearanceModel f15110a;

        /* renamed from: b, reason: collision with root package name */
        ElevationOverlayProvider f15111b;

        /* renamed from: c, reason: collision with root package name */
        ColorFilter f15112c;

        /* renamed from: d, reason: collision with root package name */
        ColorStateList f15113d;

        /* renamed from: e, reason: collision with root package name */
        ColorStateList f15114e;

        /* renamed from: f, reason: collision with root package name */
        ColorStateList f15115f;

        /* renamed from: g, reason: collision with root package name */
        ColorStateList f15116g;

        /* renamed from: h, reason: collision with root package name */
        PorterDuff.Mode f15117h;

        /* renamed from: i, reason: collision with root package name */
        Rect f15118i;

        /* renamed from: j, reason: collision with root package name */
        float f15119j;

        /* renamed from: k, reason: collision with root package name */
        float f15120k;

        /* renamed from: l, reason: collision with root package name */
        float f15121l;

        /* renamed from: m, reason: collision with root package name */
        int f15122m;

        /* renamed from: n, reason: collision with root package name */
        float f15123n;

        /* renamed from: o, reason: collision with root package name */
        float f15124o;

        /* renamed from: p, reason: collision with root package name */
        float f15125p;

        /* renamed from: q, reason: collision with root package name */
        int f15126q;

        /* renamed from: r, reason: collision with root package name */
        int f15127r;

        /* renamed from: s, reason: collision with root package name */
        int f15128s;
        int t;
        boolean u;
        Paint.Style v;

        public MaterialShapeDrawableState(ShapeAppearanceModel shapeAppearanceModel, ElevationOverlayProvider elevationOverlayProvider) {
            this.f15113d = null;
            this.f15114e = null;
            this.f15115f = null;
            this.f15116g = null;
            this.f15117h = PorterDuff.Mode.SRC_IN;
            this.f15118i = null;
            this.f15119j = 1.0f;
            this.f15120k = 1.0f;
            this.f15122m = 255;
            this.f15123n = 0.0f;
            this.f15124o = 0.0f;
            this.f15125p = 0.0f;
            this.f15126q = 0;
            this.f15127r = 0;
            this.f15128s = 0;
            this.t = 0;
            this.u = false;
            this.v = Paint.Style.FILL_AND_STROKE;
            this.f15110a = shapeAppearanceModel;
            this.f15111b = elevationOverlayProvider;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this);
            materialShapeDrawable.f15098k = true;
            return materialShapeDrawable;
        }

        public MaterialShapeDrawableState(MaterialShapeDrawableState materialShapeDrawableState) {
            this.f15113d = null;
            this.f15114e = null;
            this.f15115f = null;
            this.f15116g = null;
            this.f15117h = PorterDuff.Mode.SRC_IN;
            this.f15118i = null;
            this.f15119j = 1.0f;
            this.f15120k = 1.0f;
            this.f15122m = 255;
            this.f15123n = 0.0f;
            this.f15124o = 0.0f;
            this.f15125p = 0.0f;
            this.f15126q = 0;
            this.f15127r = 0;
            this.f15128s = 0;
            this.t = 0;
            this.u = false;
            this.v = Paint.Style.FILL_AND_STROKE;
            this.f15110a = materialShapeDrawableState.f15110a;
            this.f15111b = materialShapeDrawableState.f15111b;
            this.f15121l = materialShapeDrawableState.f15121l;
            this.f15112c = materialShapeDrawableState.f15112c;
            this.f15113d = materialShapeDrawableState.f15113d;
            this.f15114e = materialShapeDrawableState.f15114e;
            this.f15117h = materialShapeDrawableState.f15117h;
            this.f15116g = materialShapeDrawableState.f15116g;
            this.f15122m = materialShapeDrawableState.f15122m;
            this.f15119j = materialShapeDrawableState.f15119j;
            this.f15128s = materialShapeDrawableState.f15128s;
            this.f15126q = materialShapeDrawableState.f15126q;
            this.u = materialShapeDrawableState.u;
            this.f15120k = materialShapeDrawableState.f15120k;
            this.f15123n = materialShapeDrawableState.f15123n;
            this.f15124o = materialShapeDrawableState.f15124o;
            this.f15125p = materialShapeDrawableState.f15125p;
            this.f15127r = materialShapeDrawableState.f15127r;
            this.t = materialShapeDrawableState.t;
            this.f15115f = materialShapeDrawableState.f15115f;
            this.v = materialShapeDrawableState.v;
            if (materialShapeDrawableState.f15118i != null) {
                this.f15118i = new Rect(materialShapeDrawableState.f15118i);
            }
        }
    }
}
