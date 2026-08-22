package androidx.emoji2.text;

import android.text.TextPaint;
import androidx.annotation.AnyThread;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.PaintCompat;
import androidx.emoji2.text.EmojiCompat;

@AnyThread
@RestrictTo
/* loaded from: classes.dex */
class DefaultGlyphChecker implements EmojiCompat.GlyphChecker {

    /* renamed from: b, reason: collision with root package name */
    private static final ThreadLocal f3693b = new ThreadLocal();

    /* renamed from: a, reason: collision with root package name */
    private final TextPaint f3694a;

    DefaultGlyphChecker() {
        TextPaint textPaint = new TextPaint();
        this.f3694a = textPaint;
        textPaint.setTextSize(10.0f);
    }

    private static StringBuilder b() {
        ThreadLocal threadLocal = f3693b;
        if (threadLocal.get() == null) {
            threadLocal.set(new StringBuilder());
        }
        return (StringBuilder) threadLocal.get();
    }

    @Override // androidx.emoji2.text.EmojiCompat.GlyphChecker
    public boolean a(CharSequence charSequence, int i2, int i3, int i4) {
        StringBuilder b2 = b();
        b2.setLength(0);
        while (i2 < i3) {
            b2.append(charSequence.charAt(i2));
            i2++;
        }
        return PaintCompat.a(this.f3694a, b2.toString());
    }
}
