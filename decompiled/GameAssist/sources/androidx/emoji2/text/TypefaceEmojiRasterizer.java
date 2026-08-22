package androidx.emoji2.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.annotation.AnyThread;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.emoji2.text.flatbuffer.MetadataItem;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@AnyThread
@RequiresApi
/* loaded from: classes.dex */
public class TypefaceEmojiRasterizer {

    /* renamed from: d, reason: collision with root package name */
    private static final ThreadLocal f3791d = new ThreadLocal();

    /* renamed from: a, reason: collision with root package name */
    private final int f3792a;

    /* renamed from: b, reason: collision with root package name */
    private final MetadataRepo f3793b;

    /* renamed from: c, reason: collision with root package name */
    private volatile int f3794c = 0;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface HasGlyph {
    }

    TypefaceEmojiRasterizer(MetadataRepo metadataRepo, int i2) {
        this.f3793b = metadataRepo;
        this.f3792a = i2;
    }

    private MetadataItem g() {
        ThreadLocal threadLocal = f3791d;
        MetadataItem metadataItem = (MetadataItem) threadLocal.get();
        if (metadataItem == null) {
            metadataItem = new MetadataItem();
            threadLocal.set(metadataItem);
        }
        this.f3793b.d().k(metadataItem, this.f3792a);
        return metadataItem;
    }

    public void a(Canvas canvas, float f2, float f3, Paint paint) {
        Typeface g2 = this.f3793b.g();
        Typeface typeface = paint.getTypeface();
        paint.setTypeface(g2);
        canvas.drawText(this.f3793b.c(), this.f3792a * 2, 2, f2, f3, paint);
        paint.setTypeface(typeface);
    }

    public int b(int i2) {
        return g().i(i2);
    }

    public int c() {
        return g().j();
    }

    public int d() {
        return this.f3794c & 3;
    }

    public int e() {
        return g().l();
    }

    public int f() {
        return g().m();
    }

    public short h() {
        return g().n();
    }

    public int i() {
        return g().o();
    }

    public boolean j() {
        return g().k();
    }

    public boolean k() {
        return (this.f3794c & 4) > 0;
    }

    public void l(boolean z) {
        int d2 = d();
        if (z) {
            this.f3794c = d2 | 4;
        } else {
            this.f3794c = d2;
        }
    }

    public void m(boolean z) {
        int i2 = this.f3794c & 4;
        this.f3794c = z ? i2 | 2 : i2 | 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", id:");
        sb.append(Integer.toHexString(f()));
        sb.append(", codepoints:");
        int c2 = c();
        for (int i2 = 0; i2 < c2; i2++) {
            sb.append(Integer.toHexString(b(i2)));
            sb.append(" ");
        }
        return sb.toString();
    }
}
