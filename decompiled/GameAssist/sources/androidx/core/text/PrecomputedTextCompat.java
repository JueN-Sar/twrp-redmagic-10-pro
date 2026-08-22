package androidx.core.text;

import android.os.Trace;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* loaded from: classes.dex */
public class PrecomputedTextCompat implements Spannable {

    /* renamed from: k, reason: collision with root package name */
    private static final Object f3210k = new Object();

    /* renamed from: c, reason: collision with root package name */
    private final Spannable f3211c;

    /* renamed from: h, reason: collision with root package name */
    private final Params f3212h;

    /* renamed from: i, reason: collision with root package name */
    private final int[] f3213i;

    /* renamed from: j, reason: collision with root package name */
    private final PrecomputedText f3214j;

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static Spannable a(PrecomputedText precomputedText) {
            return precomputedText;
        }
    }

    public static final class Params {

        /* renamed from: a, reason: collision with root package name */
        private final TextPaint f3215a;

        /* renamed from: b, reason: collision with root package name */
        private final TextDirectionHeuristic f3216b;

        /* renamed from: c, reason: collision with root package name */
        private final int f3217c;

        /* renamed from: d, reason: collision with root package name */
        private final int f3218d;

        /* renamed from: e, reason: collision with root package name */
        final PrecomputedText.Params f3219e;

        public static class Builder {
        }

        public Params(PrecomputedText.Params params) {
            this.f3215a = params.getTextPaint();
            this.f3216b = params.getTextDirection();
            this.f3217c = params.getBreakStrategy();
            this.f3218d = params.getHyphenationFrequency();
            this.f3219e = params;
        }

        public boolean a(Params params) {
            if (this.f3217c == params.b() && this.f3218d == params.c() && this.f3215a.getTextSize() == params.e().getTextSize() && this.f3215a.getTextScaleX() == params.e().getTextScaleX() && this.f3215a.getTextSkewX() == params.e().getTextSkewX() && this.f3215a.getLetterSpacing() == params.e().getLetterSpacing() && TextUtils.equals(this.f3215a.getFontFeatureSettings(), params.e().getFontFeatureSettings()) && this.f3215a.getFlags() == params.e().getFlags() && this.f3215a.getTextLocales().equals(params.e().getTextLocales())) {
                return this.f3215a.getTypeface() == null ? params.e().getTypeface() == null : this.f3215a.getTypeface().equals(params.e().getTypeface());
            }
            return false;
        }

        public int b() {
            return this.f3217c;
        }

        public int c() {
            return this.f3218d;
        }

        public TextDirectionHeuristic d() {
            return this.f3216b;
        }

        public TextPaint e() {
            return this.f3215a;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Params)) {
                return false;
            }
            Params params = (Params) obj;
            return a(params) && this.f3216b == params.d();
        }

        public int hashCode() {
            return ObjectsCompat.b(Float.valueOf(this.f3215a.getTextSize()), Float.valueOf(this.f3215a.getTextScaleX()), Float.valueOf(this.f3215a.getTextSkewX()), Float.valueOf(this.f3215a.getLetterSpacing()), Integer.valueOf(this.f3215a.getFlags()), this.f3215a.getTextLocales(), this.f3215a.getTypeface(), Boolean.valueOf(this.f3215a.isElegantTextHeight()), this.f3216b, Integer.valueOf(this.f3217c), Integer.valueOf(this.f3218d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f3215a.getTextSize());
            sb.append(", textScaleX=" + this.f3215a.getTextScaleX());
            sb.append(", textSkewX=" + this.f3215a.getTextSkewX());
            sb.append(", letterSpacing=" + this.f3215a.getLetterSpacing());
            sb.append(", elegantTextHeight=" + this.f3215a.isElegantTextHeight());
            sb.append(", textLocale=" + this.f3215a.getTextLocales());
            sb.append(", typeface=" + this.f3215a.getTypeface());
            sb.append(", variationSettings=" + this.f3215a.getFontVariationSettings());
            sb.append(", textDir=" + this.f3216b);
            sb.append(", breakStrategy=" + this.f3217c);
            sb.append(", hyphenationFrequency=" + this.f3218d);
            sb.append("}");
            return sb.toString();
        }
    }

    private static class PrecomputedTextFutureTask extends FutureTask<PrecomputedTextCompat> {

        private static class PrecomputedTextCallback implements Callable<PrecomputedTextCompat> {

            /* renamed from: a, reason: collision with root package name */
            private Params f3220a;

            /* renamed from: b, reason: collision with root package name */
            private CharSequence f3221b;

            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public PrecomputedTextCompat call() {
                return PrecomputedTextCompat.a(this.f3221b, this.f3220a);
            }
        }
    }

    private PrecomputedTextCompat(CharSequence charSequence, Params params, int[] iArr) {
        this.f3211c = new SpannableString(charSequence);
        this.f3212h = params;
        this.f3213i = iArr;
        this.f3214j = null;
    }

    public static PrecomputedTextCompat a(CharSequence charSequence, Params params) {
        Preconditions.h(charSequence);
        Preconditions.h(params);
        try {
            Trace.beginSection("PrecomputedText");
            PrecomputedText.Params params2 = params.f3219e;
            if (params2 != null) {
                return new PrecomputedTextCompat(PrecomputedText.create(charSequence, params2), params);
            }
            ArrayList arrayList = new ArrayList();
            int length = charSequence.length();
            int i2 = 0;
            while (i2 < length) {
                int indexOf = TextUtils.indexOf(charSequence, '\n', i2, length);
                i2 = indexOf < 0 ? length : indexOf + 1;
                arrayList.add(Integer.valueOf(i2));
            }
            int[] iArr = new int[arrayList.size()];
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                iArr[i3] = ((Integer) arrayList.get(i3)).intValue();
            }
            StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), params.e(), Api.BaseClientBuilder.API_PRIORITY_OTHER).setBreakStrategy(params.b()).setHyphenationFrequency(params.c()).setTextDirection(params.d()).build();
            return new PrecomputedTextCompat(charSequence, params, iArr);
        } finally {
            Trace.endSection();
        }
    }

    public PrecomputedText b() {
        Spannable spannable = this.f3211c;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i2) {
        return this.f3211c.charAt(i2);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f3211c.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f3211c.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f3211c.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    public Object[] getSpans(int i2, int i3, Class cls) {
        return this.f3214j.getSpans(i2, i3, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f3211c.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i2, int i3, Class cls) {
        return this.f3211c.nextSpanTransition(i2, i3, cls);
    }

    @Override // android.text.Spannable
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        this.f3214j.removeSpan(obj);
    }

    @Override // android.text.Spannable
    public void setSpan(Object obj, int i2, int i3, int i4) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        this.f3214j.setSpan(obj, i2, i3, i4);
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i2, int i3) {
        return this.f3211c.subSequence(i2, i3);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f3211c.toString();
    }

    private PrecomputedTextCompat(PrecomputedText precomputedText, Params params) {
        this.f3211c = Api28Impl.a(precomputedText);
        this.f3212h = params;
        this.f3213i = null;
        this.f3214j = precomputedText;
    }
}
