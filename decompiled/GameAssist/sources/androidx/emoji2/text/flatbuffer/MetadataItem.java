package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class MetadataItem extends Table {

    public static final class Vector extends BaseVector {
    }

    public MetadataItem g(int i2, ByteBuffer byteBuffer) {
        h(i2, byteBuffer);
        return this;
    }

    public void h(int i2, ByteBuffer byteBuffer) {
        c(i2, byteBuffer);
    }

    public int i(int i2) {
        int b2 = b(16);
        if (b2 != 0) {
            return this.f3832b.getInt(d(b2) + (i2 * 4));
        }
        return 0;
    }

    public int j() {
        int b2 = b(16);
        if (b2 != 0) {
            return e(b2);
        }
        return 0;
    }

    public boolean k() {
        int b2 = b(6);
        return (b2 == 0 || this.f3832b.get(b2 + this.f3831a) == 0) ? false : true;
    }

    public short l() {
        int b2 = b(14);
        if (b2 != 0) {
            return this.f3832b.getShort(b2 + this.f3831a);
        }
        return (short) 0;
    }

    public int m() {
        int b2 = b(4);
        if (b2 != 0) {
            return this.f3832b.getInt(b2 + this.f3831a);
        }
        return 0;
    }

    public short n() {
        int b2 = b(8);
        if (b2 != 0) {
            return this.f3832b.getShort(b2 + this.f3831a);
        }
        return (short) 0;
    }

    public short o() {
        int b2 = b(12);
        if (b2 != 0) {
            return this.f3832b.getShort(b2 + this.f3831a);
        }
        return (short) 0;
    }
}
