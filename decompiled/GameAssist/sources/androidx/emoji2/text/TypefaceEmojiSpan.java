package androidx.emoji2.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;

@RequiresApi
@RestrictTo
/* loaded from: classes.dex */
public final class TypefaceEmojiSpan extends EmojiSpan {

    /* renamed from: m, reason: collision with root package name */
    private static Paint f3795m;

    /* renamed from: l, reason: collision with root package name */
    private TextPaint f3796l;

    public TypefaceEmojiSpan(TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
        super(typefaceEmojiRasterizer);
    }

    private TextPaint c(CharSequence charSequence, int i2, int i3, Paint paint) {
        if (!(charSequence instanceof Spanned)) {
            if (paint instanceof TextPaint) {
                return (TextPaint) paint;
            }
            return null;
        }
        CharacterStyle[] characterStyleArr = (CharacterStyle[]) ((Spanned) charSequence).getSpans(i2, i3, CharacterStyle.class);
        if (characterStyleArr.length != 0) {
            if (characterStyleArr.length != 1 || characterStyleArr[0] != this) {
                TextPaint textPaint = this.f3796l;
                if (textPaint == null) {
                    textPaint = new TextPaint();
                    this.f3796l = textPaint;
                }
                textPaint.set(paint);
                for (CharacterStyle characterStyle : characterStyleArr) {
                    characterStyle.updateDrawState(textPaint);
                }
                return textPaint;
            }
        }
        if (paint instanceof TextPaint) {
            return (TextPaint) paint;
        }
        return null;
    }

    private static Paint e() {
        if (f3795m == null) {
            TextPaint textPaint = new TextPaint();
            f3795m = textPaint;
            textPaint.setColor(EmojiCompat.c().d());
            f3795m.setStyle(Paint.Style.FILL);
        }
        return f3795m;
    }

    void d(Canvas canvas, TextPaint textPaint, float f2, float f3, float f4, float f5) {
        int color = textPaint.getColor();
        Paint.Style style = textPaint.getStyle();
        textPaint.setColor(textPaint.bgColor);
        textPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(f2, f4, f3, f5, textPaint);
        textPaint.setStyle(style);
        textPaint.setColor(color);
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, Paint paint) {
        Paint paint2 = paint;
        TextPaint c2 = c(charSequence, i2, i3, paint2);
        if (c2 != null && c2.bgColor != 0) {
            d(canvas, c2, f2, f2 + b(), i4, i6);
        }
        if (EmojiCompat.c().j()) {
            canvas.drawRect(f2, i4, f2 + b(), i6, e());
        }
        TypefaceEmojiRasterizer a2 = a();
        float f3 = i5;
        if (c2 != null) {
            paint2 = c2;
        }
        a2.a(canvas, f2, f3, paint2);
    }
}
