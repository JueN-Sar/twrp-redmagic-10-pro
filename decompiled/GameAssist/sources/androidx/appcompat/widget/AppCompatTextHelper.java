package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.LocaleList;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.appcompat.R;
import androidx.core.content.res.ResourcesCompat;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* loaded from: classes.dex */
class AppCompatTextHelper {

    /* renamed from: a, reason: collision with root package name */
    private final TextView f818a;

    /* renamed from: b, reason: collision with root package name */
    private TintInfo f819b;

    /* renamed from: c, reason: collision with root package name */
    private TintInfo f820c;

    /* renamed from: d, reason: collision with root package name */
    private TintInfo f821d;

    /* renamed from: e, reason: collision with root package name */
    private TintInfo f822e;

    /* renamed from: f, reason: collision with root package name */
    private TintInfo f823f;

    /* renamed from: g, reason: collision with root package name */
    private TintInfo f824g;

    /* renamed from: h, reason: collision with root package name */
    private TintInfo f825h;

    /* renamed from: i, reason: collision with root package name */
    private final AppCompatTextViewAutoSizeHelper f826i;

    /* renamed from: j, reason: collision with root package name */
    private int f827j = 0;

    /* renamed from: k, reason: collision with root package name */
    private int f828k = -1;

    /* renamed from: l, reason: collision with root package name */
    private Typeface f829l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f830m;

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static Locale a(String str) {
            return Locale.forLanguageTag(str);
        }
    }

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static LocaleList a(String str) {
            return LocaleList.forLanguageTags(str);
        }

        @DoNotInline
        static void b(TextView textView, LocaleList localeList) {
            textView.setTextLocales(localeList);
        }
    }

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static int a(TextView textView) {
            return textView.getAutoSizeStepGranularity();
        }

        @DoNotInline
        static void b(TextView textView, int i2, int i3, int i4, int i5) {
            textView.setAutoSizeTextTypeUniformWithConfiguration(i2, i3, i4, i5);
        }

        @DoNotInline
        static void c(TextView textView, int[] iArr, int i2) {
            textView.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
        }

        @DoNotInline
        static boolean d(TextView textView, String str) {
            return textView.setFontVariationSettings(str);
        }
    }

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static Typeface a(Typeface typeface, int i2, boolean z) {
            return Typeface.create(typeface, i2, z);
        }
    }

    AppCompatTextHelper(TextView textView) {
        this.f818a = textView;
        this.f826i = new AppCompatTextViewAutoSizeHelper(textView);
    }

    private void B(int i2, float f2) {
        this.f826i.r(i2, f2);
    }

    private void C(Context context, TintTypedArray tintTypedArray) {
        String o2;
        this.f827j = tintTypedArray.k(R.styleable.TextAppearance_android_textStyle, this.f827j);
        int k2 = tintTypedArray.k(R.styleable.TextAppearance_android_textFontWeight, -1);
        this.f828k = k2;
        if (k2 != -1) {
            this.f827j &= 2;
        }
        if (!tintTypedArray.s(R.styleable.TextAppearance_android_fontFamily) && !tintTypedArray.s(R.styleable.TextAppearance_fontFamily)) {
            if (tintTypedArray.s(R.styleable.TextAppearance_android_typeface)) {
                this.f830m = false;
                int k3 = tintTypedArray.k(R.styleable.TextAppearance_android_typeface, 1);
                if (k3 == 1) {
                    this.f829l = Typeface.SANS_SERIF;
                    return;
                } else if (k3 == 2) {
                    this.f829l = Typeface.SERIF;
                    return;
                } else {
                    if (k3 != 3) {
                        return;
                    }
                    this.f829l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.f829l = null;
        int i2 = tintTypedArray.s(R.styleable.TextAppearance_fontFamily) ? R.styleable.TextAppearance_fontFamily : R.styleable.TextAppearance_android_fontFamily;
        final int i3 = this.f828k;
        final int i4 = this.f827j;
        if (!context.isRestricted()) {
            final WeakReference weakReference = new WeakReference(this.f818a);
            try {
                Typeface j2 = tintTypedArray.j(i2, this.f827j, new ResourcesCompat.FontCallback() { // from class: androidx.appcompat.widget.AppCompatTextHelper.1
                    @Override // androidx.core.content.res.ResourcesCompat.FontCallback
                    /* renamed from: h */
                    public void f(int i5) {
                    }

                    @Override // androidx.core.content.res.ResourcesCompat.FontCallback
                    /* renamed from: i */
                    public void g(Typeface typeface) {
                        int i5 = i3;
                        if (i5 != -1) {
                            typeface = Api28Impl.a(typeface, i5, (i4 & 2) != 0);
                        }
                        AppCompatTextHelper.this.n(weakReference, typeface);
                    }
                });
                if (j2 != null) {
                    if (this.f828k != -1) {
                        this.f829l = Api28Impl.a(Typeface.create(j2, 0), this.f828k, (this.f827j & 2) != 0);
                    } else {
                        this.f829l = j2;
                    }
                }
                this.f830m = this.f829l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.f829l != null || (o2 = tintTypedArray.o(i2)) == null) {
            return;
        }
        if (this.f828k != -1) {
            this.f829l = Api28Impl.a(Typeface.create(o2, 0), this.f828k, (this.f827j & 2) != 0);
        } else {
            this.f829l = Typeface.create(o2, this.f827j);
        }
    }

    private void a(Drawable drawable, TintInfo tintInfo) {
        if (drawable == null || tintInfo == null) {
            return;
        }
        AppCompatDrawableManager.i(drawable, tintInfo, this.f818a.getDrawableState());
    }

    private static TintInfo d(Context context, AppCompatDrawableManager appCompatDrawableManager, int i2) {
        ColorStateList f2 = appCompatDrawableManager.f(context, i2);
        if (f2 == null) {
            return null;
        }
        TintInfo tintInfo = new TintInfo();
        tintInfo.f1018d = true;
        tintInfo.f1015a = f2;
        return tintInfo;
    }

    private void y(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        if (drawable5 != null || drawable6 != null) {
            Drawable[] compoundDrawablesRelative = this.f818a.getCompoundDrawablesRelative();
            if (drawable5 == null) {
                drawable5 = compoundDrawablesRelative[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative[1];
            }
            if (drawable6 == null) {
                drawable6 = compoundDrawablesRelative[2];
            }
            TextView textView = this.f818a;
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative[3];
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable5, drawable2, drawable6, drawable4);
            return;
        }
        if (drawable == null && drawable2 == null && drawable3 == null && drawable4 == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative2 = this.f818a.getCompoundDrawablesRelative();
        Drawable drawable7 = compoundDrawablesRelative2[0];
        if (drawable7 != null || compoundDrawablesRelative2[2] != null) {
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative2[1];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative2[3];
            }
            this.f818a.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable7, drawable2, compoundDrawablesRelative2[2], drawable4);
            return;
        }
        Drawable[] compoundDrawables = this.f818a.getCompoundDrawables();
        TextView textView2 = this.f818a;
        if (drawable == null) {
            drawable = compoundDrawables[0];
        }
        if (drawable2 == null) {
            drawable2 = compoundDrawables[1];
        }
        if (drawable3 == null) {
            drawable3 = compoundDrawables[2];
        }
        if (drawable4 == null) {
            drawable4 = compoundDrawables[3];
        }
        textView2.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }

    private void z() {
        TintInfo tintInfo = this.f825h;
        this.f819b = tintInfo;
        this.f820c = tintInfo;
        this.f821d = tintInfo;
        this.f822e = tintInfo;
        this.f823f = tintInfo;
        this.f824g = tintInfo;
    }

    void A(int i2, float f2) {
        if (ViewUtils.f1095a || l()) {
            return;
        }
        B(i2, f2);
    }

    void b() {
        if (this.f819b != null || this.f820c != null || this.f821d != null || this.f822e != null) {
            Drawable[] compoundDrawables = this.f818a.getCompoundDrawables();
            a(compoundDrawables[0], this.f819b);
            a(compoundDrawables[1], this.f820c);
            a(compoundDrawables[2], this.f821d);
            a(compoundDrawables[3], this.f822e);
        }
        if (this.f823f == null && this.f824g == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative = this.f818a.getCompoundDrawablesRelative();
        a(compoundDrawablesRelative[0], this.f823f);
        a(compoundDrawablesRelative[2], this.f824g);
    }

    void c() {
        this.f826i.a();
    }

    int e() {
        return this.f826i.e();
    }

    int f() {
        return this.f826i.f();
    }

    int g() {
        return this.f826i.g();
    }

    int[] h() {
        return this.f826i.h();
    }

    int i() {
        return this.f826i.i();
    }

    ColorStateList j() {
        TintInfo tintInfo = this.f825h;
        if (tintInfo != null) {
            return tintInfo.f1015a;
        }
        return null;
    }

    PorterDuff.Mode k() {
        TintInfo tintInfo = this.f825h;
        if (tintInfo != null) {
            return tintInfo.f1016b;
        }
        return null;
    }

    boolean l() {
        return this.f826i.l();
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:117:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void m(android.util.AttributeSet r17, int r18) {
        /*
            Method dump skipped, instructions count: 684
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatTextHelper.m(android.util.AttributeSet, int):void");
    }

    void n(WeakReference weakReference, final Typeface typeface) {
        if (this.f830m) {
            this.f829l = typeface;
            final TextView textView = (TextView) weakReference.get();
            if (textView != null) {
                if (!textView.isAttachedToWindow()) {
                    textView.setTypeface(typeface, this.f827j);
                } else {
                    final int i2 = this.f827j;
                    textView.post(new Runnable() { // from class: androidx.appcompat.widget.AppCompatTextHelper.2
                        @Override // java.lang.Runnable
                        public void run() {
                            textView.setTypeface(typeface, i2);
                        }
                    });
                }
            }
        }
    }

    void o(boolean z, int i2, int i3, int i4, int i5) {
        if (ViewUtils.f1095a) {
            return;
        }
        c();
    }

    void p() {
        b();
    }

    void q(Context context, int i2) {
        String o2;
        TintTypedArray t = TintTypedArray.t(context, i2, R.styleable.TextAppearance);
        if (t.s(R.styleable.TextAppearance_textAllCaps)) {
            s(t.a(R.styleable.TextAppearance_textAllCaps, false));
        }
        if (t.s(R.styleable.TextAppearance_android_textSize) && t.f(R.styleable.TextAppearance_android_textSize, -1) == 0) {
            this.f818a.setTextSize(0, 0.0f);
        }
        C(context, t);
        if (t.s(R.styleable.TextAppearance_fontVariationSettings) && (o2 = t.o(R.styleable.TextAppearance_fontVariationSettings)) != null) {
            Api26Impl.d(this.f818a, o2);
        }
        t.x();
        Typeface typeface = this.f829l;
        if (typeface != null) {
            this.f818a.setTypeface(typeface, this.f827j);
        }
    }

    void r(TextView textView, InputConnection inputConnection, EditorInfo editorInfo) {
    }

    void s(boolean z) {
        this.f818a.setAllCaps(z);
    }

    void t(int i2, int i3, int i4, int i5) {
        this.f826i.n(i2, i3, i4, i5);
    }

    void u(int[] iArr, int i2) {
        this.f826i.o(iArr, i2);
    }

    void v(int i2) {
        this.f826i.p(i2);
    }

    void w(ColorStateList colorStateList) {
        if (this.f825h == null) {
            this.f825h = new TintInfo();
        }
        TintInfo tintInfo = this.f825h;
        tintInfo.f1015a = colorStateList;
        tintInfo.f1018d = colorStateList != null;
        z();
    }

    void x(PorterDuff.Mode mode) {
        if (this.f825h == null) {
            this.f825h = new TintInfo();
        }
        TintInfo tintInfo = this.f825h;
        tintInfo.f1016b = mode;
        tintInfo.f1017c = mode != null;
        z();
    }
}
