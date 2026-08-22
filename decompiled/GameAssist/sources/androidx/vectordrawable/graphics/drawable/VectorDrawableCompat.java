package androidx.vectordrawable.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;
import androidx.collection.ArrayMap;
import androidx.core.content.res.ComplexColorCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.PathParser;
import androidx.core.graphics.drawable.DrawableCompat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class VectorDrawableCompat extends VectorDrawableCommon {

    /* renamed from: q, reason: collision with root package name */
    static final PorterDuff.Mode f5654q = PorterDuff.Mode.SRC_IN;

    /* renamed from: h, reason: collision with root package name */
    private VectorDrawableCompatState f5655h;

    /* renamed from: i, reason: collision with root package name */
    private PorterDuffColorFilter f5656i;

    /* renamed from: j, reason: collision with root package name */
    private ColorFilter f5657j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f5658k;

    /* renamed from: l, reason: collision with root package name */
    private boolean f5659l;

    /* renamed from: m, reason: collision with root package name */
    private Drawable.ConstantState f5660m;

    /* renamed from: n, reason: collision with root package name */
    private final float[] f5661n;

    /* renamed from: o, reason: collision with root package name */
    private final Matrix f5662o;

    /* renamed from: p, reason: collision with root package name */
    private final Rect f5663p;

    private static class VClipPath extends VPath {
        VClipPath() {
        }

        private void f(TypedArray typedArray, XmlPullParser xmlPullParser) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.f5690b = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.f5689a = PathParser.d(string2);
            }
            this.f5691c = TypedArrayUtils.k(typedArray, xmlPullParser, "fillType", 2, 0);
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VPath
        public boolean c() {
            return true;
        }

        public void e(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (TypedArrayUtils.r(xmlPullParser, "pathData")) {
                TypedArray s2 = TypedArrayUtils.s(resources, theme, attributeSet, AndroidResources.f5623d);
                f(s2, xmlPullParser);
                s2.recycle();
            }
        }

        VClipPath(VClipPath vClipPath) {
            super(vClipPath);
        }
    }

    private static abstract class VObject {
        private VObject() {
        }

        public boolean a() {
            return false;
        }

        public boolean b(int[] iArr) {
            return false;
        }
    }

    private static class VectorDrawableCompatState extends Drawable.ConstantState {

        /* renamed from: a, reason: collision with root package name */
        int f5710a;

        /* renamed from: b, reason: collision with root package name */
        VPathRenderer f5711b;

        /* renamed from: c, reason: collision with root package name */
        ColorStateList f5712c;

        /* renamed from: d, reason: collision with root package name */
        PorterDuff.Mode f5713d;

        /* renamed from: e, reason: collision with root package name */
        boolean f5714e;

        /* renamed from: f, reason: collision with root package name */
        Bitmap f5715f;

        /* renamed from: g, reason: collision with root package name */
        ColorStateList f5716g;

        /* renamed from: h, reason: collision with root package name */
        PorterDuff.Mode f5717h;

        /* renamed from: i, reason: collision with root package name */
        int f5718i;

        /* renamed from: j, reason: collision with root package name */
        boolean f5719j;

        /* renamed from: k, reason: collision with root package name */
        boolean f5720k;

        /* renamed from: l, reason: collision with root package name */
        Paint f5721l;

        public VectorDrawableCompatState(VectorDrawableCompatState vectorDrawableCompatState) {
            this.f5712c = null;
            this.f5713d = VectorDrawableCompat.f5654q;
            if (vectorDrawableCompatState != null) {
                this.f5710a = vectorDrawableCompatState.f5710a;
                VPathRenderer vPathRenderer = new VPathRenderer(vectorDrawableCompatState.f5711b);
                this.f5711b = vPathRenderer;
                if (vectorDrawableCompatState.f5711b.f5698e != null) {
                    vPathRenderer.f5698e = new Paint(vectorDrawableCompatState.f5711b.f5698e);
                }
                if (vectorDrawableCompatState.f5711b.f5697d != null) {
                    this.f5711b.f5697d = new Paint(vectorDrawableCompatState.f5711b.f5697d);
                }
                this.f5712c = vectorDrawableCompatState.f5712c;
                this.f5713d = vectorDrawableCompatState.f5713d;
                this.f5714e = vectorDrawableCompatState.f5714e;
            }
        }

        public boolean a(int i2, int i3) {
            return i2 == this.f5715f.getWidth() && i3 == this.f5715f.getHeight();
        }

        public boolean b() {
            return !this.f5720k && this.f5716g == this.f5712c && this.f5717h == this.f5713d && this.f5719j == this.f5714e && this.f5718i == this.f5711b.getRootAlpha();
        }

        public void c(int i2, int i3) {
            if (this.f5715f == null || !a(i2, i3)) {
                this.f5715f = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
                this.f5720k = true;
            }
        }

        public void d(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f5715f, (Rect) null, rect, e(colorFilter));
        }

        public Paint e(ColorFilter colorFilter) {
            if (!f() && colorFilter == null) {
                return null;
            }
            if (this.f5721l == null) {
                Paint paint = new Paint();
                this.f5721l = paint;
                paint.setFilterBitmap(true);
            }
            this.f5721l.setAlpha(this.f5711b.getRootAlpha());
            this.f5721l.setColorFilter(colorFilter);
            return this.f5721l;
        }

        public boolean f() {
            return this.f5711b.getRootAlpha() < 255;
        }

        public boolean g() {
            return this.f5711b.f();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f5710a;
        }

        public boolean h(int[] iArr) {
            boolean g2 = this.f5711b.g(iArr);
            this.f5720k |= g2;
            return g2;
        }

        public void i() {
            this.f5716g = this.f5712c;
            this.f5717h = this.f5713d;
            this.f5718i = this.f5711b.getRootAlpha();
            this.f5719j = this.f5714e;
            this.f5720k = false;
        }

        public void j(int i2, int i3) {
            this.f5715f.eraseColor(0);
            this.f5711b.b(new Canvas(this.f5715f), i2, i3, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new VectorDrawableCompat(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new VectorDrawableCompat(this);
        }

        public VectorDrawableCompatState() {
            this.f5712c = null;
            this.f5713d = VectorDrawableCompat.f5654q;
            this.f5711b = new VPathRenderer();
        }
    }

    VectorDrawableCompat() {
        this.f5659l = true;
        this.f5661n = new float[9];
        this.f5662o = new Matrix();
        this.f5663p = new Rect();
        this.f5655h = new VectorDrawableCompatState();
    }

    static int a(int i2, float f2) {
        return (i2 & 16777215) | (((int) (Color.alpha(i2) * f2)) << 24);
    }

    public static VectorDrawableCompat b(Resources resources, int i2, Resources.Theme theme) {
        VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
        vectorDrawableCompat.f5653c = ResourcesCompat.e(resources, i2, theme);
        vectorDrawableCompat.f5660m = new VectorDrawableDelegateState(vectorDrawableCompat.f5653c.getConstantState());
        return vectorDrawableCompat;
    }

    public static VectorDrawableCompat c(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
        vectorDrawableCompat.inflate(resources, xmlPullParser, attributeSet, theme);
        return vectorDrawableCompat;
    }

    private void e(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        VectorDrawableCompatState vectorDrawableCompatState = this.f5655h;
        VPathRenderer vPathRenderer = vectorDrawableCompatState.f5711b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(vPathRenderer.f5701h);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        boolean z = true;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                VGroup vGroup = (VGroup) arrayDeque.peek();
                if ("path".equals(name)) {
                    VFullPath vFullPath = new VFullPath();
                    vFullPath.g(resources, attributeSet, theme, xmlPullParser);
                    vGroup.f5677b.add(vFullPath);
                    if (vFullPath.getPathName() != null) {
                        vPathRenderer.f5709p.put(vFullPath.getPathName(), vFullPath);
                    }
                    vectorDrawableCompatState.f5710a = vFullPath.f5692d | vectorDrawableCompatState.f5710a;
                    z = false;
                } else if ("clip-path".equals(name)) {
                    VClipPath vClipPath = new VClipPath();
                    vClipPath.e(resources, attributeSet, theme, xmlPullParser);
                    vGroup.f5677b.add(vClipPath);
                    if (vClipPath.getPathName() != null) {
                        vPathRenderer.f5709p.put(vClipPath.getPathName(), vClipPath);
                    }
                    vectorDrawableCompatState.f5710a = vClipPath.f5692d | vectorDrawableCompatState.f5710a;
                } else if ("group".equals(name)) {
                    VGroup vGroup2 = new VGroup();
                    vGroup2.c(resources, attributeSet, theme, xmlPullParser);
                    vGroup.f5677b.add(vGroup2);
                    arrayDeque.push(vGroup2);
                    if (vGroup2.getGroupName() != null) {
                        vPathRenderer.f5709p.put(vGroup2.getGroupName(), vGroup2);
                    }
                    vectorDrawableCompatState.f5710a = vGroup2.f5686k | vectorDrawableCompatState.f5710a;
                }
            } else if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                arrayDeque.pop();
            }
            eventType = xmlPullParser.next();
        }
        if (z) {
            throw new XmlPullParserException("no path defined");
        }
    }

    private boolean f() {
        return isAutoMirrored() && DrawableCompat.f(this) == 1;
    }

    private static PorterDuff.Mode g(int i2, PorterDuff.Mode mode) {
        if (i2 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i2 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i2 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i2) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    private void i(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
        VectorDrawableCompatState vectorDrawableCompatState = this.f5655h;
        VPathRenderer vPathRenderer = vectorDrawableCompatState.f5711b;
        vectorDrawableCompatState.f5713d = g(TypedArrayUtils.k(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList g2 = TypedArrayUtils.g(typedArray, xmlPullParser, theme, "tint", 1);
        if (g2 != null) {
            vectorDrawableCompatState.f5712c = g2;
        }
        vectorDrawableCompatState.f5714e = TypedArrayUtils.e(typedArray, xmlPullParser, "autoMirrored", 5, vectorDrawableCompatState.f5714e);
        vPathRenderer.f5704k = TypedArrayUtils.j(typedArray, xmlPullParser, "viewportWidth", 7, vPathRenderer.f5704k);
        float j2 = TypedArrayUtils.j(typedArray, xmlPullParser, "viewportHeight", 8, vPathRenderer.f5705l);
        vPathRenderer.f5705l = j2;
        if (vPathRenderer.f5704k <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (j2 <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
        vPathRenderer.f5702i = typedArray.getDimension(3, vPathRenderer.f5702i);
        float dimension = typedArray.getDimension(2, vPathRenderer.f5703j);
        vPathRenderer.f5703j = dimension;
        if (vPathRenderer.f5702i <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
        }
        if (dimension <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
        }
        vPathRenderer.setAlpha(TypedArrayUtils.j(typedArray, xmlPullParser, "alpha", 4, vPathRenderer.getAlpha()));
        String string = typedArray.getString(0);
        if (string != null) {
            vPathRenderer.f5707n = string;
            vPathRenderer.f5709p.put(string, vPathRenderer);
        }
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f5653c;
        if (drawable == null) {
            return false;
        }
        DrawableCompat.b(drawable);
        return false;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    Object d(String str) {
        return this.f5655h.f5711b.f5709p.get(str);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.f5663p);
        if (this.f5663p.width() <= 0 || this.f5663p.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.f5657j;
        if (colorFilter == null) {
            colorFilter = this.f5656i;
        }
        canvas.getMatrix(this.f5662o);
        this.f5662o.getValues(this.f5661n);
        float abs = Math.abs(this.f5661n[0]);
        float abs2 = Math.abs(this.f5661n[4]);
        float abs3 = Math.abs(this.f5661n[1]);
        float abs4 = Math.abs(this.f5661n[3]);
        if (abs3 != 0.0f || abs4 != 0.0f) {
            abs = 1.0f;
            abs2 = 1.0f;
        }
        int min = Math.min(2048, (int) (this.f5663p.width() * abs));
        int min2 = Math.min(2048, (int) (this.f5663p.height() * abs2));
        if (min <= 0 || min2 <= 0) {
            return;
        }
        int save = canvas.save();
        Rect rect = this.f5663p;
        canvas.translate(rect.left, rect.top);
        if (f()) {
            canvas.translate(this.f5663p.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.f5663p.offsetTo(0, 0);
        this.f5655h.c(min, min2);
        if (!this.f5659l) {
            this.f5655h.j(min, min2);
        } else if (!this.f5655h.b()) {
            this.f5655h.j(min, min2);
            this.f5655h.i();
        }
        this.f5655h.d(canvas, colorFilter, this.f5663p);
        canvas.restoreToCount(save);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f5653c;
        return drawable != null ? DrawableCompat.d(drawable) : this.f5655h.f5711b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return this.f5655h.getChangingConfigurations() | super.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f5653c;
        return drawable != null ? DrawableCompat.e(drawable) : this.f5657j;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.f5653c != null) {
            return new VectorDrawableDelegateState(this.f5653c.getConstantState());
        }
        this.f5655h.f5710a = getChangingConfigurations();
        return this.f5655h;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f5653c;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.f5655h.f5711b.f5703j;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f5653c;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.f5655h.f5711b.f5702i;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    void h(boolean z) {
        this.f5659l = z;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f5653c;
        return drawable != null ? DrawableCompat.h(drawable) : this.f5655h.f5714e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        VectorDrawableCompatState vectorDrawableCompatState;
        ColorStateList colorStateList;
        Drawable drawable = this.f5653c;
        return drawable != null ? drawable.isStateful() : super.isStateful() || ((vectorDrawableCompatState = this.f5655h) != null && (vectorDrawableCompatState.g() || ((colorStateList = this.f5655h.f5712c) != null && colorStateList.isStateful())));
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f5658k && super.mutate() == this) {
            this.f5655h = new VectorDrawableCompatState(this.f5655h);
            this.f5658k = true;
        }
        return this;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean z;
        PorterDuff.Mode mode;
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.f5655h;
        ColorStateList colorStateList = vectorDrawableCompatState.f5712c;
        if (colorStateList == null || (mode = vectorDrawableCompatState.f5713d) == null) {
            z = false;
        } else {
            this.f5656i = updateTintFilter(this.f5656i, colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (!vectorDrawableCompatState.g() || !vectorDrawableCompatState.h(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j2) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j2);
        } else {
            super.scheduleSelf(runnable, j2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            drawable.setAlpha(i2);
        } else if (this.f5655h.f5711b.getRootAlpha() != i2) {
            this.f5655h.f5711b.setRootAlpha(i2);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            DrawableCompat.j(drawable, z);
        } else {
            this.f5655h.f5714e = z;
        }
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i2) {
        super.setChangingConfigurations(i2);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i2, PorterDuff.Mode mode) {
        super.setColorFilter(i2, mode);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i2, int i3, int i4, int i5) {
        super.setHotspotBounds(i2, i3, i4, i5);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i2) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            DrawableCompat.n(drawable, i2);
        } else {
            setTintList(ColorStateList.valueOf(i2));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            DrawableCompat.o(drawable, colorStateList);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.f5655h;
        if (vectorDrawableCompatState.f5712c != colorStateList) {
            vectorDrawableCompatState.f5712c = colorStateList;
            this.f5656i = updateTintFilter(this.f5656i, colorStateList, vectorDrawableCompatState.f5713d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            DrawableCompat.p(drawable, mode);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.f5655h;
        if (vectorDrawableCompatState.f5713d != mode) {
            vectorDrawableCompatState.f5713d = mode;
            this.f5656i = updateTintFilter(this.f5656i, vectorDrawableCompatState.f5712c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f5653c;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    @RequiresApi
    private static class VectorDrawableDelegateState extends Drawable.ConstantState {

        /* renamed from: a, reason: collision with root package name */
        private final Drawable.ConstantState f5722a;

        public VectorDrawableDelegateState(Drawable.ConstantState constantState) {
            this.f5722a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f5722a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f5722a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.f5653c = (VectorDrawable) this.f5722a.newDrawable();
            return vectorDrawableCompat;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.f5653c = (VectorDrawable) this.f5722a.newDrawable(resources);
            return vectorDrawableCompat;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.f5653c = (VectorDrawable) this.f5722a.newDrawable(resources, theme);
            return vectorDrawableCompat;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f5657j = colorFilter;
            invalidateSelf();
        }
    }

    private static abstract class VPath extends VObject {

        /* renamed from: a, reason: collision with root package name */
        protected PathParser.PathDataNode[] f5689a;

        /* renamed from: b, reason: collision with root package name */
        String f5690b;

        /* renamed from: c, reason: collision with root package name */
        int f5691c;

        /* renamed from: d, reason: collision with root package name */
        int f5692d;

        public VPath() {
            super();
            this.f5689a = null;
            this.f5691c = 0;
        }

        public boolean c() {
            return false;
        }

        public void d(Path path) {
            path.reset();
            PathParser.PathDataNode[] pathDataNodeArr = this.f5689a;
            if (pathDataNodeArr != null) {
                PathParser.PathDataNode.i(pathDataNodeArr, path);
            }
        }

        public PathParser.PathDataNode[] getPathData() {
            return this.f5689a;
        }

        public String getPathName() {
            return this.f5690b;
        }

        public void setPathData(PathParser.PathDataNode[] pathDataNodeArr) {
            if (PathParser.b(this.f5689a, pathDataNodeArr)) {
                PathParser.k(this.f5689a, pathDataNodeArr);
            } else {
                this.f5689a = PathParser.f(pathDataNodeArr);
            }
        }

        public VPath(VPath vPath) {
            super();
            this.f5689a = null;
            this.f5691c = 0;
            this.f5690b = vPath.f5690b;
            this.f5692d = vPath.f5692d;
            this.f5689a = PathParser.f(vPath.f5689a);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        Drawable drawable = this.f5653c;
        if (drawable != null) {
            DrawableCompat.g(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.f5655h;
        vectorDrawableCompatState.f5711b = new VPathRenderer();
        TypedArray s2 = TypedArrayUtils.s(resources, theme, attributeSet, AndroidResources.f5620a);
        i(s2, xmlPullParser, theme);
        s2.recycle();
        vectorDrawableCompatState.f5710a = getChangingConfigurations();
        vectorDrawableCompatState.f5720k = true;
        e(resources, xmlPullParser, attributeSet, theme);
        this.f5656i = updateTintFilter(this.f5656i, vectorDrawableCompatState.f5712c, vectorDrawableCompatState.f5713d);
    }

    VectorDrawableCompat(VectorDrawableCompatState vectorDrawableCompatState) {
        this.f5659l = true;
        this.f5661n = new float[9];
        this.f5662o = new Matrix();
        this.f5663p = new Rect();
        this.f5655h = vectorDrawableCompatState;
        this.f5656i = updateTintFilter(this.f5656i, vectorDrawableCompatState.f5712c, vectorDrawableCompatState.f5713d);
    }

    private static class VFullPath extends VPath {

        /* renamed from: e, reason: collision with root package name */
        private int[] f5664e;

        /* renamed from: f, reason: collision with root package name */
        ComplexColorCompat f5665f;

        /* renamed from: g, reason: collision with root package name */
        float f5666g;

        /* renamed from: h, reason: collision with root package name */
        ComplexColorCompat f5667h;

        /* renamed from: i, reason: collision with root package name */
        float f5668i;

        /* renamed from: j, reason: collision with root package name */
        float f5669j;

        /* renamed from: k, reason: collision with root package name */
        float f5670k;

        /* renamed from: l, reason: collision with root package name */
        float f5671l;

        /* renamed from: m, reason: collision with root package name */
        float f5672m;

        /* renamed from: n, reason: collision with root package name */
        Paint.Cap f5673n;

        /* renamed from: o, reason: collision with root package name */
        Paint.Join f5674o;

        /* renamed from: p, reason: collision with root package name */
        float f5675p;

        VFullPath() {
            this.f5666g = 0.0f;
            this.f5668i = 1.0f;
            this.f5669j = 1.0f;
            this.f5670k = 0.0f;
            this.f5671l = 1.0f;
            this.f5672m = 0.0f;
            this.f5673n = Paint.Cap.BUTT;
            this.f5674o = Paint.Join.MITER;
            this.f5675p = 4.0f;
        }

        private Paint.Cap e(int i2, Paint.Cap cap) {
            return i2 != 0 ? i2 != 1 ? i2 != 2 ? cap : Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }

        private Paint.Join f(int i2, Paint.Join join) {
            return i2 != 0 ? i2 != 1 ? i2 != 2 ? join : Paint.Join.BEVEL : Paint.Join.ROUND : Paint.Join.MITER;
        }

        private void h(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.f5664e = null;
            if (TypedArrayUtils.r(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.f5690b = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.f5689a = PathParser.d(string2);
                }
                this.f5667h = TypedArrayUtils.i(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.f5669j = TypedArrayUtils.j(typedArray, xmlPullParser, "fillAlpha", 12, this.f5669j);
                this.f5673n = e(TypedArrayUtils.k(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.f5673n);
                this.f5674o = f(TypedArrayUtils.k(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.f5674o);
                this.f5675p = TypedArrayUtils.j(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.f5675p);
                this.f5665f = TypedArrayUtils.i(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.f5668i = TypedArrayUtils.j(typedArray, xmlPullParser, "strokeAlpha", 11, this.f5668i);
                this.f5666g = TypedArrayUtils.j(typedArray, xmlPullParser, "strokeWidth", 4, this.f5666g);
                this.f5671l = TypedArrayUtils.j(typedArray, xmlPullParser, "trimPathEnd", 6, this.f5671l);
                this.f5672m = TypedArrayUtils.j(typedArray, xmlPullParser, "trimPathOffset", 7, this.f5672m);
                this.f5670k = TypedArrayUtils.j(typedArray, xmlPullParser, "trimPathStart", 5, this.f5670k);
                this.f5691c = TypedArrayUtils.k(typedArray, xmlPullParser, "fillType", 13, this.f5691c);
            }
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VObject
        public boolean a() {
            return this.f5667h.i() || this.f5665f.i();
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VObject
        public boolean b(int[] iArr) {
            return this.f5665f.j(iArr) | this.f5667h.j(iArr);
        }

        public void g(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray s2 = TypedArrayUtils.s(resources, theme, attributeSet, AndroidResources.f5622c);
            h(s2, xmlPullParser, theme);
            s2.recycle();
        }

        float getFillAlpha() {
            return this.f5669j;
        }

        @ColorInt
        int getFillColor() {
            return this.f5667h.e();
        }

        float getStrokeAlpha() {
            return this.f5668i;
        }

        @ColorInt
        int getStrokeColor() {
            return this.f5665f.e();
        }

        float getStrokeWidth() {
            return this.f5666g;
        }

        float getTrimPathEnd() {
            return this.f5671l;
        }

        float getTrimPathOffset() {
            return this.f5672m;
        }

        float getTrimPathStart() {
            return this.f5670k;
        }

        void setFillAlpha(float f2) {
            this.f5669j = f2;
        }

        void setFillColor(int i2) {
            this.f5667h.k(i2);
        }

        void setStrokeAlpha(float f2) {
            this.f5668i = f2;
        }

        void setStrokeColor(int i2) {
            this.f5665f.k(i2);
        }

        void setStrokeWidth(float f2) {
            this.f5666g = f2;
        }

        void setTrimPathEnd(float f2) {
            this.f5671l = f2;
        }

        void setTrimPathOffset(float f2) {
            this.f5672m = f2;
        }

        void setTrimPathStart(float f2) {
            this.f5670k = f2;
        }

        VFullPath(VFullPath vFullPath) {
            super(vFullPath);
            this.f5666g = 0.0f;
            this.f5668i = 1.0f;
            this.f5669j = 1.0f;
            this.f5670k = 0.0f;
            this.f5671l = 1.0f;
            this.f5672m = 0.0f;
            this.f5673n = Paint.Cap.BUTT;
            this.f5674o = Paint.Join.MITER;
            this.f5675p = 4.0f;
            this.f5664e = vFullPath.f5664e;
            this.f5665f = vFullPath.f5665f;
            this.f5666g = vFullPath.f5666g;
            this.f5668i = vFullPath.f5668i;
            this.f5667h = vFullPath.f5667h;
            this.f5691c = vFullPath.f5691c;
            this.f5669j = vFullPath.f5669j;
            this.f5670k = vFullPath.f5670k;
            this.f5671l = vFullPath.f5671l;
            this.f5672m = vFullPath.f5672m;
            this.f5673n = vFullPath.f5673n;
            this.f5674o = vFullPath.f5674o;
            this.f5675p = vFullPath.f5675p;
        }
    }

    private static class VPathRenderer {

        /* renamed from: q, reason: collision with root package name */
        private static final Matrix f5693q = new Matrix();

        /* renamed from: a, reason: collision with root package name */
        private final Path f5694a;

        /* renamed from: b, reason: collision with root package name */
        private final Path f5695b;

        /* renamed from: c, reason: collision with root package name */
        private final Matrix f5696c;

        /* renamed from: d, reason: collision with root package name */
        Paint f5697d;

        /* renamed from: e, reason: collision with root package name */
        Paint f5698e;

        /* renamed from: f, reason: collision with root package name */
        private PathMeasure f5699f;

        /* renamed from: g, reason: collision with root package name */
        private int f5700g;

        /* renamed from: h, reason: collision with root package name */
        final VGroup f5701h;

        /* renamed from: i, reason: collision with root package name */
        float f5702i;

        /* renamed from: j, reason: collision with root package name */
        float f5703j;

        /* renamed from: k, reason: collision with root package name */
        float f5704k;

        /* renamed from: l, reason: collision with root package name */
        float f5705l;

        /* renamed from: m, reason: collision with root package name */
        int f5706m;

        /* renamed from: n, reason: collision with root package name */
        String f5707n;

        /* renamed from: o, reason: collision with root package name */
        Boolean f5708o;

        /* renamed from: p, reason: collision with root package name */
        final ArrayMap f5709p;

        public VPathRenderer() {
            this.f5696c = new Matrix();
            this.f5702i = 0.0f;
            this.f5703j = 0.0f;
            this.f5704k = 0.0f;
            this.f5705l = 0.0f;
            this.f5706m = 255;
            this.f5707n = null;
            this.f5708o = null;
            this.f5709p = new ArrayMap();
            this.f5701h = new VGroup();
            this.f5694a = new Path();
            this.f5695b = new Path();
        }

        private static float a(float f2, float f3, float f4, float f5) {
            return (f2 * f5) - (f3 * f4);
        }

        private void c(VGroup vGroup, Matrix matrix, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            vGroup.f5676a.set(matrix);
            vGroup.f5676a.preConcat(vGroup.f5685j);
            canvas.save();
            for (int i4 = 0; i4 < vGroup.f5677b.size(); i4++) {
                VObject vObject = (VObject) vGroup.f5677b.get(i4);
                if (vObject instanceof VGroup) {
                    c((VGroup) vObject, vGroup.f5676a, canvas, i2, i3, colorFilter);
                } else if (vObject instanceof VPath) {
                    d(vGroup, (VPath) vObject, canvas, i2, i3, colorFilter);
                }
            }
            canvas.restore();
        }

        private void d(VGroup vGroup, VPath vPath, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            float f2 = i2 / this.f5704k;
            float f3 = i3 / this.f5705l;
            float min = Math.min(f2, f3);
            Matrix matrix = vGroup.f5676a;
            this.f5696c.set(matrix);
            this.f5696c.postScale(f2, f3);
            float e2 = e(matrix);
            if (e2 == 0.0f) {
                return;
            }
            vPath.d(this.f5694a);
            Path path = this.f5694a;
            this.f5695b.reset();
            if (vPath.c()) {
                this.f5695b.setFillType(vPath.f5691c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                this.f5695b.addPath(path, this.f5696c);
                canvas.clipPath(this.f5695b);
                return;
            }
            VFullPath vFullPath = (VFullPath) vPath;
            float f4 = vFullPath.f5670k;
            if (f4 != 0.0f || vFullPath.f5671l != 1.0f) {
                float f5 = vFullPath.f5672m;
                float f6 = (f4 + f5) % 1.0f;
                float f7 = (vFullPath.f5671l + f5) % 1.0f;
                if (this.f5699f == null) {
                    this.f5699f = new PathMeasure();
                }
                this.f5699f.setPath(this.f5694a, false);
                float length = this.f5699f.getLength();
                float f8 = f6 * length;
                float f9 = f7 * length;
                path.reset();
                if (f8 > f9) {
                    this.f5699f.getSegment(f8, length, path, true);
                    this.f5699f.getSegment(0.0f, f9, path, true);
                } else {
                    this.f5699f.getSegment(f8, f9, path, true);
                }
                path.rLineTo(0.0f, 0.0f);
            }
            this.f5695b.addPath(path, this.f5696c);
            if (vFullPath.f5667h.l()) {
                ComplexColorCompat complexColorCompat = vFullPath.f5667h;
                if (this.f5698e == null) {
                    Paint paint = new Paint(1);
                    this.f5698e = paint;
                    paint.setStyle(Paint.Style.FILL);
                }
                Paint paint2 = this.f5698e;
                if (complexColorCompat.h()) {
                    Shader f10 = complexColorCompat.f();
                    f10.setLocalMatrix(this.f5696c);
                    paint2.setShader(f10);
                    paint2.setAlpha(Math.round(vFullPath.f5669j * 255.0f));
                } else {
                    paint2.setShader(null);
                    paint2.setAlpha(255);
                    paint2.setColor(VectorDrawableCompat.a(complexColorCompat.e(), vFullPath.f5669j));
                }
                paint2.setColorFilter(colorFilter);
                this.f5695b.setFillType(vFullPath.f5691c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                canvas.drawPath(this.f5695b, paint2);
            }
            if (vFullPath.f5665f.l()) {
                ComplexColorCompat complexColorCompat2 = vFullPath.f5665f;
                if (this.f5697d == null) {
                    Paint paint3 = new Paint(1);
                    this.f5697d = paint3;
                    paint3.setStyle(Paint.Style.STROKE);
                }
                Paint paint4 = this.f5697d;
                Paint.Join join = vFullPath.f5674o;
                if (join != null) {
                    paint4.setStrokeJoin(join);
                }
                Paint.Cap cap = vFullPath.f5673n;
                if (cap != null) {
                    paint4.setStrokeCap(cap);
                }
                paint4.setStrokeMiter(vFullPath.f5675p);
                if (complexColorCompat2.h()) {
                    Shader f11 = complexColorCompat2.f();
                    f11.setLocalMatrix(this.f5696c);
                    paint4.setShader(f11);
                    paint4.setAlpha(Math.round(vFullPath.f5668i * 255.0f));
                } else {
                    paint4.setShader(null);
                    paint4.setAlpha(255);
                    paint4.setColor(VectorDrawableCompat.a(complexColorCompat2.e(), vFullPath.f5668i));
                }
                paint4.setColorFilter(colorFilter);
                paint4.setStrokeWidth(vFullPath.f5666g * min * e2);
                canvas.drawPath(this.f5695b, paint4);
            }
        }

        private float e(Matrix matrix) {
            float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
            matrix.mapVectors(fArr);
            float hypot = (float) Math.hypot(fArr[0], fArr[1]);
            float hypot2 = (float) Math.hypot(fArr[2], fArr[3]);
            float a2 = a(fArr[0], fArr[1], fArr[2], fArr[3]);
            float max = Math.max(hypot, hypot2);
            if (max > 0.0f) {
                return Math.abs(a2) / max;
            }
            return 0.0f;
        }

        public void b(Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            c(this.f5701h, f5693q, canvas, i2, i3, colorFilter);
        }

        public boolean f() {
            if (this.f5708o == null) {
                this.f5708o = Boolean.valueOf(this.f5701h.a());
            }
            return this.f5708o.booleanValue();
        }

        public boolean g(int[] iArr) {
            return this.f5701h.b(iArr);
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.f5706m;
        }

        public void setAlpha(float f2) {
            setRootAlpha((int) (f2 * 255.0f));
        }

        public void setRootAlpha(int i2) {
            this.f5706m = i2;
        }

        public VPathRenderer(VPathRenderer vPathRenderer) {
            this.f5696c = new Matrix();
            this.f5702i = 0.0f;
            this.f5703j = 0.0f;
            this.f5704k = 0.0f;
            this.f5705l = 0.0f;
            this.f5706m = 255;
            this.f5707n = null;
            this.f5708o = null;
            ArrayMap arrayMap = new ArrayMap();
            this.f5709p = arrayMap;
            this.f5701h = new VGroup(vPathRenderer.f5701h, arrayMap);
            this.f5694a = new Path(vPathRenderer.f5694a);
            this.f5695b = new Path(vPathRenderer.f5695b);
            this.f5702i = vPathRenderer.f5702i;
            this.f5703j = vPathRenderer.f5703j;
            this.f5704k = vPathRenderer.f5704k;
            this.f5705l = vPathRenderer.f5705l;
            this.f5700g = vPathRenderer.f5700g;
            this.f5706m = vPathRenderer.f5706m;
            this.f5707n = vPathRenderer.f5707n;
            String str = vPathRenderer.f5707n;
            if (str != null) {
                arrayMap.put(str, this);
            }
            this.f5708o = vPathRenderer.f5708o;
        }
    }

    private static class VGroup extends VObject {

        /* renamed from: a, reason: collision with root package name */
        final Matrix f5676a;

        /* renamed from: b, reason: collision with root package name */
        final ArrayList f5677b;

        /* renamed from: c, reason: collision with root package name */
        float f5678c;

        /* renamed from: d, reason: collision with root package name */
        private float f5679d;

        /* renamed from: e, reason: collision with root package name */
        private float f5680e;

        /* renamed from: f, reason: collision with root package name */
        private float f5681f;

        /* renamed from: g, reason: collision with root package name */
        private float f5682g;

        /* renamed from: h, reason: collision with root package name */
        private float f5683h;

        /* renamed from: i, reason: collision with root package name */
        private float f5684i;

        /* renamed from: j, reason: collision with root package name */
        final Matrix f5685j;

        /* renamed from: k, reason: collision with root package name */
        int f5686k;

        /* renamed from: l, reason: collision with root package name */
        private int[] f5687l;

        /* renamed from: m, reason: collision with root package name */
        private String f5688m;

        public VGroup(VGroup vGroup, ArrayMap arrayMap) {
            super();
            VPath vClipPath;
            this.f5676a = new Matrix();
            this.f5677b = new ArrayList();
            this.f5678c = 0.0f;
            this.f5679d = 0.0f;
            this.f5680e = 0.0f;
            this.f5681f = 1.0f;
            this.f5682g = 1.0f;
            this.f5683h = 0.0f;
            this.f5684i = 0.0f;
            Matrix matrix = new Matrix();
            this.f5685j = matrix;
            this.f5688m = null;
            this.f5678c = vGroup.f5678c;
            this.f5679d = vGroup.f5679d;
            this.f5680e = vGroup.f5680e;
            this.f5681f = vGroup.f5681f;
            this.f5682g = vGroup.f5682g;
            this.f5683h = vGroup.f5683h;
            this.f5684i = vGroup.f5684i;
            this.f5687l = vGroup.f5687l;
            String str = vGroup.f5688m;
            this.f5688m = str;
            this.f5686k = vGroup.f5686k;
            if (str != null) {
                arrayMap.put(str, this);
            }
            matrix.set(vGroup.f5685j);
            ArrayList arrayList = vGroup.f5677b;
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                Object obj = arrayList.get(i2);
                if (obj instanceof VGroup) {
                    this.f5677b.add(new VGroup((VGroup) obj, arrayMap));
                } else {
                    if (obj instanceof VFullPath) {
                        vClipPath = new VFullPath((VFullPath) obj);
                    } else {
                        if (!(obj instanceof VClipPath)) {
                            throw new IllegalStateException("Unknown object in the tree!");
                        }
                        vClipPath = new VClipPath((VClipPath) obj);
                    }
                    this.f5677b.add(vClipPath);
                    Object obj2 = vClipPath.f5690b;
                    if (obj2 != null) {
                        arrayMap.put(obj2, vClipPath);
                    }
                }
            }
        }

        private void d() {
            this.f5685j.reset();
            this.f5685j.postTranslate(-this.f5679d, -this.f5680e);
            this.f5685j.postScale(this.f5681f, this.f5682g);
            this.f5685j.postRotate(this.f5678c, 0.0f, 0.0f);
            this.f5685j.postTranslate(this.f5683h + this.f5679d, this.f5684i + this.f5680e);
        }

        private void e(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.f5687l = null;
            this.f5678c = TypedArrayUtils.j(typedArray, xmlPullParser, "rotation", 5, this.f5678c);
            this.f5679d = typedArray.getFloat(1, this.f5679d);
            this.f5680e = typedArray.getFloat(2, this.f5680e);
            this.f5681f = TypedArrayUtils.j(typedArray, xmlPullParser, "scaleX", 3, this.f5681f);
            this.f5682g = TypedArrayUtils.j(typedArray, xmlPullParser, "scaleY", 4, this.f5682g);
            this.f5683h = TypedArrayUtils.j(typedArray, xmlPullParser, "translateX", 6, this.f5683h);
            this.f5684i = TypedArrayUtils.j(typedArray, xmlPullParser, "translateY", 7, this.f5684i);
            String string = typedArray.getString(0);
            if (string != null) {
                this.f5688m = string;
            }
            d();
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VObject
        public boolean a() {
            for (int i2 = 0; i2 < this.f5677b.size(); i2++) {
                if (((VObject) this.f5677b.get(i2)).a()) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VObject
        public boolean b(int[] iArr) {
            boolean z = false;
            for (int i2 = 0; i2 < this.f5677b.size(); i2++) {
                z |= ((VObject) this.f5677b.get(i2)).b(iArr);
            }
            return z;
        }

        public void c(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray s2 = TypedArrayUtils.s(resources, theme, attributeSet, AndroidResources.f5621b);
            e(s2, xmlPullParser);
            s2.recycle();
        }

        public String getGroupName() {
            return this.f5688m;
        }

        public Matrix getLocalMatrix() {
            return this.f5685j;
        }

        public float getPivotX() {
            return this.f5679d;
        }

        public float getPivotY() {
            return this.f5680e;
        }

        public float getRotation() {
            return this.f5678c;
        }

        public float getScaleX() {
            return this.f5681f;
        }

        public float getScaleY() {
            return this.f5682g;
        }

        public float getTranslateX() {
            return this.f5683h;
        }

        public float getTranslateY() {
            return this.f5684i;
        }

        public void setPivotX(float f2) {
            if (f2 != this.f5679d) {
                this.f5679d = f2;
                d();
            }
        }

        public void setPivotY(float f2) {
            if (f2 != this.f5680e) {
                this.f5680e = f2;
                d();
            }
        }

        public void setRotation(float f2) {
            if (f2 != this.f5678c) {
                this.f5678c = f2;
                d();
            }
        }

        public void setScaleX(float f2) {
            if (f2 != this.f5681f) {
                this.f5681f = f2;
                d();
            }
        }

        public void setScaleY(float f2) {
            if (f2 != this.f5682g) {
                this.f5682g = f2;
                d();
            }
        }

        public void setTranslateX(float f2) {
            if (f2 != this.f5683h) {
                this.f5683h = f2;
                d();
            }
        }

        public void setTranslateY(float f2) {
            if (f2 != this.f5684i) {
                this.f5684i = f2;
                d();
            }
        }

        public VGroup() {
            super();
            this.f5676a = new Matrix();
            this.f5677b = new ArrayList();
            this.f5678c = 0.0f;
            this.f5679d = 0.0f;
            this.f5680e = 0.0f;
            this.f5681f = 1.0f;
            this.f5682g = 1.0f;
            this.f5683h = 0.0f;
            this.f5684i = 0.0f;
            this.f5685j = new Matrix();
            this.f5688m = null;
        }
    }
}
