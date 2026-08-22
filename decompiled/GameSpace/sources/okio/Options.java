package okio;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {
    final ByteString[] byteStrings;
    final int[] trie;

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.byteStrings = byteStringArr;
        this.trie = iArr;
    }

    private static void buildTrieRecursive(long j, Buffer buffer, int i, List<ByteString> list, int i2, int i3, List<Integer> list2) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        Buffer buffer2;
        if (i2 >= i3) {
            throw new AssertionError();
        }
        for (int i9 = i2; i9 < i3; i9++) {
            if (list.get(i9).size() < i) {
                throw new AssertionError();
            }
        }
        ByteString byteString = list.get(i2);
        ByteString byteString2 = list.get(i3 - 1);
        if (i == byteString.size()) {
            int i10 = i2 + 1;
            i5 = i10;
            i4 = list2.get(i2).intValue();
            byteString = list.get(i10);
        } else {
            i4 = -1;
            i5 = i2;
        }
        if (byteString.getByte(i) == byteString2.getByte(i)) {
            int min = Math.min(byteString.size(), byteString2.size());
            int i11 = 0;
            for (int i12 = i; i12 < min && byteString.getByte(i12) == byteString2.getByte(i12); i12++) {
                i11++;
            }
            long intCount = 1 + j + intCount(buffer) + 2 + i11;
            buffer.writeInt(-i11);
            buffer.writeInt(i4);
            int i13 = i;
            while (true) {
                i6 = i + i11;
                if (i13 >= i6) {
                    break;
                }
                buffer.writeInt(byteString.getByte(i13) & 255);
                i13++;
            }
            if (i5 + 1 == i3) {
                if (i6 != list.get(i5).size()) {
                    throw new AssertionError();
                }
                buffer.writeInt(list2.get(i5).intValue());
                return;
            } else {
                Buffer buffer3 = new Buffer();
                buffer.writeInt((int) ((intCount(buffer3) + intCount) * (-1)));
                buildTrieRecursive(intCount, buffer3, i6, list, i5, i3, list2);
                buffer.write(buffer3, buffer3.size());
                return;
            }
        }
        int i14 = 1;
        for (int i15 = i5 + 1; i15 < i3; i15++) {
            if (list.get(i15 - 1).getByte(i) != list.get(i15).getByte(i)) {
                i14++;
            }
        }
        long intCount2 = j + intCount(buffer) + 2 + (i14 * 2);
        buffer.writeInt(i14);
        buffer.writeInt(i4);
        for (int i16 = i5; i16 < i3; i16++) {
            byte b = list.get(i16).getByte(i);
            if (i16 == i5 || b != list.get(i16 - 1).getByte(i)) {
                buffer.writeInt(b & 255);
            }
        }
        Buffer buffer4 = new Buffer();
        int i17 = i5;
        while (i17 < i3) {
            byte b2 = list.get(i17).getByte(i);
            int i18 = i17 + 1;
            int i19 = i18;
            while (true) {
                if (i19 >= i3) {
                    i7 = i3;
                    break;
                } else {
                    if (b2 != list.get(i19).getByte(i)) {
                        i7 = i19;
                        break;
                    }
                    i19++;
                }
            }
            if (i18 == i7 && i + 1 == list.get(i17).size()) {
                buffer.writeInt(list2.get(i17).intValue());
                i8 = i7;
                buffer2 = buffer4;
            } else {
                buffer.writeInt((int) ((intCount(buffer4) + intCount2) * (-1)));
                i8 = i7;
                buffer2 = buffer4;
                buildTrieRecursive(intCount2, buffer4, i + 1, list, i17, i7, list2);
            }
            buffer4 = buffer2;
            i17 = i8;
        }
        Buffer buffer5 = buffer4;
        buffer.write(buffer5, buffer5.size());
    }

    private static int intCount(Buffer buffer) {
        return (int) (buffer.size() / 4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b8, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static okio.Options of(okio.ByteString... r11) {
        /*
            Method dump skipped, instructions count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Options.of(okio.ByteString[]):okio.Options");
    }

    @Override // java.util.AbstractList, java.util.List
    public ByteString get(int i) {
        return this.byteStrings[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.byteStrings.length;
    }
}
