package okio;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {

    /* renamed from: c, reason: collision with root package name */
    final ByteString[] f19611c;

    /* renamed from: h, reason: collision with root package name */
    final int[] f19612h;

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.f19611c = byteStringArr;
        this.f19612h = iArr;
    }

    private static void b(long j2, Buffer buffer, int i2, List list, int i3, int i4, List list2) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        Buffer buffer2;
        if (i3 >= i4) {
            throw new AssertionError();
        }
        for (int i10 = i3; i10 < i4; i10++) {
            if (((ByteString) list.get(i10)).r() < i2) {
                throw new AssertionError();
            }
        }
        ByteString byteString = (ByteString) list.get(i3);
        ByteString byteString2 = (ByteString) list.get(i4 - 1);
        if (i2 == byteString.r()) {
            int i11 = i3 + 1;
            i6 = i11;
            i5 = ((Integer) list2.get(i3)).intValue();
            byteString = (ByteString) list.get(i11);
        } else {
            i5 = -1;
            i6 = i3;
        }
        if (byteString.f(i2) == byteString2.f(i2)) {
            int min = Math.min(byteString.r(), byteString2.r());
            int i12 = 0;
            for (int i13 = i2; i13 < min && byteString.f(i13) == byteString2.f(i13); i13++) {
                i12++;
            }
            long f2 = 1 + j2 + f(buffer) + 2 + i12;
            buffer.e0(-i12);
            buffer.e0(i5);
            int i14 = i2;
            while (true) {
                i7 = i2 + i12;
                if (i14 >= i7) {
                    break;
                }
                buffer.e0(byteString.f(i14) & 255);
                i14++;
            }
            if (i6 + 1 == i4) {
                if (i7 != ((ByteString) list.get(i6)).r()) {
                    throw new AssertionError();
                }
                buffer.e0(((Integer) list2.get(i6)).intValue());
                return;
            } else {
                Buffer buffer3 = new Buffer();
                buffer.e0((int) ((f(buffer3) + f2) * (-1)));
                b(f2, buffer3, i7, list, i6, i4, list2);
                buffer.w(buffer3, buffer3.size());
                return;
            }
        }
        int i15 = 1;
        for (int i16 = i6 + 1; i16 < i4; i16++) {
            if (((ByteString) list.get(i16 - 1)).f(i2) != ((ByteString) list.get(i16)).f(i2)) {
                i15++;
            }
        }
        long f3 = j2 + f(buffer) + 2 + (i15 * 2);
        buffer.e0(i15);
        buffer.e0(i5);
        for (int i17 = i6; i17 < i4; i17++) {
            byte f4 = ((ByteString) list.get(i17)).f(i2);
            if (i17 == i6 || f4 != ((ByteString) list.get(i17 - 1)).f(i2)) {
                buffer.e0(f4 & 255);
            }
        }
        Buffer buffer4 = new Buffer();
        int i18 = i6;
        while (i18 < i4) {
            byte f5 = ((ByteString) list.get(i18)).f(i2);
            int i19 = i18 + 1;
            int i20 = i19;
            while (true) {
                if (i20 >= i4) {
                    i8 = i4;
                    break;
                } else {
                    if (f5 != ((ByteString) list.get(i20)).f(i2)) {
                        i8 = i20;
                        break;
                    }
                    i20++;
                }
            }
            if (i19 == i8 && i2 + 1 == ((ByteString) list.get(i18)).r()) {
                buffer.e0(((Integer) list2.get(i18)).intValue());
                i9 = i8;
                buffer2 = buffer4;
            } else {
                buffer.e0((int) ((f(buffer4) + f3) * (-1)));
                i9 = i8;
                buffer2 = buffer4;
                b(f3, buffer4, i2 + 1, list, i18, i8, list2);
            }
            buffer4 = buffer2;
            i18 = i9;
        }
        Buffer buffer5 = buffer4;
        buffer.w(buffer5, buffer5.size());
    }

    private static int f(Buffer buffer) {
        return (int) (buffer.size() / 4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ba, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static okio.Options g(okio.ByteString... r11) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Options.g(okio.ByteString[]):okio.Options");
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public ByteString get(int i2) {
        return this.f19611c[i2];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f19611c.length;
    }
}
