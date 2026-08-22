package androidx.emoji2.text;

import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;

@RequiresApi
/* loaded from: classes.dex */
public abstract class EmojiSpan extends ReplacementSpan {

    /* renamed from: h, reason: collision with root package name */
    private final TypefaceEmojiRasterizer f3755h;

    /* renamed from: c, reason: collision with root package name */
    private final Paint.FontMetricsInt f3754c = new Paint.FontMetricsInt();

    /* renamed from: i, reason: collision with root package name */
    private short f3756i = -1;

    /* renamed from: j, reason: collision with root package name */
    private short f3757j = -1;

    /* renamed from: k, reason: collision with root package name */
    private float f3758k = 1.0f;

    EmojiSpan(TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
        Preconditions.i(typefaceEmojiRasterizer, "rasterizer cannot be null");
        this.f3755h = typefaceEmojiRasterizer;
    }

    public final TypefaceEmojiRasterizer a() {
        return this.f3755h;
    }

    final int b() {
        return this.f3756i;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i2, int i3, Paint.FontMetricsInt fontMetricsInt) {
        paint.getFontMetricsInt(this.f3754c);
        Paint.FontMetricsInt fontMetricsInt2 = this.f3754c;
        this.f3758k = (Math.abs(fontMetricsInt2.descent - fontMetricsInt2.ascent) * 1.0f) / this.f3755h.e();
        this.f3757j = (short) (this.f3755h.e() * this.f3758k);
        short i4 = (short) (this.f3755h.i() * this.f3758k);
        this.f3756i = i4;
        if (fontMetricsInt != null) {
            Paint.FontMetricsInt fontMetricsInt3 = this.f3754c;
            fontMetricsInt.ascent = fontMetricsInt3.ascent;
            fontMetricsInt.descent = fontMetricsInt3.descent;
            fontMetricsInt.top = fontMetricsInt3.top;
            fontMetricsInt.bottom = fontMetricsInt3.bottom;
        }
        return i4;
    }
}
