package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes.dex */
public final class MetadataList extends Table {

    public static final class Vector extends BaseVector {
    }

    public static MetadataList i(ByteBuffer byteBuffer) {
        return j(byteBuffer, new MetadataList());
    }

    public static MetadataList j(ByteBuffer byteBuffer, MetadataList metadataList) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return metadataList.g(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public MetadataList g(int i2, ByteBuffer byteBuffer) {
        h(i2, byteBuffer);
        return this;
    }

    public void h(int i2, ByteBuffer byteBuffer) {
        c(i2, byteBuffer);
    }

    public MetadataItem k(MetadataItem metadataItem, int i2) {
        int b2 = b(6);
        if (b2 != 0) {
            return metadataItem.g(a(d(b2) + (i2 * 4)), this.f3832b);
        }
        return null;
    }

    public int l() {
        int b2 = b(6);
        if (b2 != 0) {
            return e(b2);
        }
        return 0;
    }

    public int m() {
        int b2 = b(4);
        if (b2 != 0) {
            return this.f3832b.getInt(b2 + this.f3831a);
        }
        return 0;
    }
}
