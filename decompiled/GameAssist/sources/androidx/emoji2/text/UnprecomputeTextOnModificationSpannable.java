package androidx.emoji2.text;

import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import androidx.annotation.RequiresApi;
import androidx.core.text.PrecomputedTextCompat;
import java.util.stream.IntStream;

/* loaded from: classes.dex */
class UnprecomputeTextOnModificationSpannable implements Spannable {

    /* renamed from: c, reason: collision with root package name */
    private boolean f3797c = false;

    /* renamed from: h, reason: collision with root package name */
    private Spannable f3798h;

    @RequiresApi
    private static class CharSequenceHelper_API24 {
        static IntStream a(CharSequence charSequence) {
            return charSequence.chars();
        }

        static IntStream b(CharSequence charSequence) {
            return charSequence.codePoints();
        }
    }

    static class PrecomputedTextDetector {
        PrecomputedTextDetector() {
        }

        boolean a(CharSequence charSequence) {
            return charSequence instanceof PrecomputedTextCompat;
        }
    }

    @RequiresApi
    static class PrecomputedTextDetector_28 extends PrecomputedTextDetector {
        PrecomputedTextDetector_28() {
        }

        @Override // androidx.emoji2.text.UnprecomputeTextOnModificationSpannable.PrecomputedTextDetector
        boolean a(CharSequence charSequence) {
            return (charSequence instanceof PrecomputedText) || (charSequence instanceof PrecomputedTextCompat);
        }
    }

    UnprecomputeTextOnModificationSpannable(Spannable spannable) {
        this.f3798h = spannable;
    }

    private void a() {
        Spannable spannable = this.f3798h;
        if (!this.f3797c && c().a(spannable)) {
            this.f3798h = new SpannableString(spannable);
        }
        this.f3797c = true;
    }

    static PrecomputedTextDetector c() {
        return new PrecomputedTextDetector_28();
    }

    Spannable b() {
        return this.f3798h;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i2) {
        return this.f3798h.charAt(i2);
    }

    @Override // java.lang.CharSequence
    public IntStream chars() {
        return CharSequenceHelper_API24.a(this.f3798h);
    }

    @Override // java.lang.CharSequence
    public IntStream codePoints() {
        return CharSequenceHelper_API24.b(this.f3798h);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f3798h.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f3798h.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f3798h.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    public Object[] getSpans(int i2, int i3, Class cls) {
        return this.f3798h.getSpans(i2, i3, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f3798h.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i2, int i3, Class cls) {
        return this.f3798h.nextSpanTransition(i2, i3, cls);
    }

    @Override // android.text.Spannable
    public void removeSpan(Object obj) {
        a();
        this.f3798h.removeSpan(obj);
    }

    @Override // android.text.Spannable
    public void setSpan(Object obj, int i2, int i3, int i4) {
        a();
        this.f3798h.setSpan(obj, i2, i3, i4);
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i2, int i3) {
        return this.f3798h.subSequence(i2, i3);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f3798h.toString();
    }

    UnprecomputeTextOnModificationSpannable(CharSequence charSequence) {
        this.f3798h = new SpannableString(charSequence);
    }
}
