package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.util.Comparator;

/* loaded from: classes.dex */
public class Table {

    /* renamed from: a, reason: collision with root package name */
    protected int f3831a;

    /* renamed from: b, reason: collision with root package name */
    protected ByteBuffer f3832b;

    /* renamed from: c, reason: collision with root package name */
    private int f3833c;

    /* renamed from: d, reason: collision with root package name */
    private int f3834d;

    /* renamed from: e, reason: collision with root package name */
    Utf8 f3835e = Utf8.a();

    /* renamed from: androidx.emoji2.text.flatbuffer.Table$1, reason: invalid class name */
    class AnonymousClass1 implements Comparator<Integer> {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ByteBuffer f3836c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ Table f3837h;

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Integer num, Integer num2) {
            return this.f3837h.f(num, num2, this.f3836c);
        }
    }

    protected int a(int i2) {
        return i2 + this.f3832b.getInt(i2);
    }

    protected int b(int i2) {
        if (i2 < this.f3834d) {
            return this.f3832b.getShort(this.f3833c + i2);
        }
        return 0;
    }

    protected void c(int i2, ByteBuffer byteBuffer) {
        this.f3832b = byteBuffer;
        if (byteBuffer == null) {
            this.f3831a = 0;
            this.f3833c = 0;
            this.f3834d = 0;
        } else {
            this.f3831a = i2;
            int i3 = i2 - byteBuffer.getInt(i2);
            this.f3833c = i3;
            this.f3834d = this.f3832b.getShort(i3);
        }
    }

    protected int d(int i2) {
        int i3 = i2 + this.f3831a;
        return i3 + this.f3832b.getInt(i3) + 4;
    }

    protected int e(int i2) {
        int i3 = i2 + this.f3831a;
        return this.f3832b.getInt(i3 + this.f3832b.getInt(i3));
    }

    protected int f(Integer num, Integer num2, ByteBuffer byteBuffer) {
        return 0;
    }
}
