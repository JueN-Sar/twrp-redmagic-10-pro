package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;

@RestrictTo
/* loaded from: classes.dex */
public class TintTypedArray {

    /* renamed from: a, reason: collision with root package name */
    private final Context f1020a;

    /* renamed from: b, reason: collision with root package name */
    private final TypedArray f1021b;

    /* renamed from: c, reason: collision with root package name */
    private TypedValue f1022c;

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static int a(TypedArray typedArray) {
            return typedArray.getChangingConfigurations();
        }

        @DoNotInline
        static int b(TypedArray typedArray, int i2) {
            return typedArray.getType(i2);
        }
    }

    private TintTypedArray(Context context, TypedArray typedArray) {
        this.f1020a = context;
        this.f1021b = typedArray;
    }

    public static TintTypedArray t(Context context, int i2, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(i2, iArr));
    }

    public static TintTypedArray u(Context context, AttributeSet attributeSet, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static TintTypedArray v(Context context, AttributeSet attributeSet, int[] iArr, int i2, int i3) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr, i2, i3));
    }

    public boolean a(int i2, boolean z) {
        return this.f1021b.getBoolean(i2, z);
    }

    public int b(int i2, int i3) {
        return this.f1021b.getColor(i2, i3);
    }

    public ColorStateList c(int i2) {
        int resourceId;
        ColorStateList a2;
        return (!this.f1021b.hasValue(i2) || (resourceId = this.f1021b.getResourceId(i2, 0)) == 0 || (a2 = AppCompatResources.a(this.f1020a, resourceId)) == null) ? this.f1021b.getColorStateList(i2) : a2;
    }

    public float d(int i2, float f2) {
        return this.f1021b.getDimension(i2, f2);
    }

    public int e(int i2, int i3) {
        return this.f1021b.getDimensionPixelOffset(i2, i3);
    }

    public int f(int i2, int i3) {
        return this.f1021b.getDimensionPixelSize(i2, i3);
    }

    public Drawable g(int i2) {
        int resourceId;
        return (!this.f1021b.hasValue(i2) || (resourceId = this.f1021b.getResourceId(i2, 0)) == 0) ? this.f1021b.getDrawable(i2) : AppCompatResources.b(this.f1020a, resourceId);
    }

    public Drawable h(int i2) {
        int resourceId;
        if (!this.f1021b.hasValue(i2) || (resourceId = this.f1021b.getResourceId(i2, 0)) == 0) {
            return null;
        }
        return AppCompatDrawableManager.b().d(this.f1020a, resourceId, true);
    }

    public float i(int i2, float f2) {
        return this.f1021b.getFloat(i2, f2);
    }

    public Typeface j(int i2, int i3, ResourcesCompat.FontCallback fontCallback) {
        int resourceId = this.f1021b.getResourceId(i2, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f1022c == null) {
            this.f1022c = new TypedValue();
        }
        return ResourcesCompat.h(this.f1020a, resourceId, this.f1022c, i3, fontCallback);
    }

    public int k(int i2, int i3) {
        return this.f1021b.getInt(i2, i3);
    }

    public int l(int i2, int i3) {
        return this.f1021b.getInteger(i2, i3);
    }

    public int m(int i2, int i3) {
        return this.f1021b.getLayoutDimension(i2, i3);
    }

    public int n(int i2, int i3) {
        return this.f1021b.getResourceId(i2, i3);
    }

    public String o(int i2) {
        return this.f1021b.getString(i2);
    }

    public CharSequence p(int i2) {
        return this.f1021b.getText(i2);
    }

    public CharSequence[] q(int i2) {
        return this.f1021b.getTextArray(i2);
    }

    public TypedArray r() {
        return this.f1021b;
    }

    public boolean s(int i2) {
        return this.f1021b.hasValue(i2);
    }

    public TypedValue w(int i2) {
        return this.f1021b.peekValue(i2);
    }

    public void x() {
        this.f1021b.recycle();
    }
}
