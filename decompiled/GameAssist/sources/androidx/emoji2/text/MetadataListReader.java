package androidx.emoji2.text;

import androidx.annotation.AnyThread;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.emoji2.text.flatbuffer.MetadataList;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@AnyThread
@RequiresApi
@RestrictTo
/* loaded from: classes.dex */
class MetadataListReader {

    private static class ByteBufferReader implements OpenTypeReader {

        /* renamed from: a, reason: collision with root package name */
        private final ByteBuffer f3774a;

        ByteBufferReader(ByteBuffer byteBuffer) {
            this.f3774a = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public int a() {
            return this.f3774a.getInt();
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public void b(int i2) {
            ByteBuffer byteBuffer = this.f3774a;
            byteBuffer.position(byteBuffer.position() + i2);
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public long c() {
            return MetadataListReader.c(this.f3774a.getInt());
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public long getPosition() {
            return this.f3774a.position();
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public int readUnsignedShort() {
            return MetadataListReader.d(this.f3774a.getShort());
        }
    }

    private static class InputStreamOpenTypeReader implements OpenTypeReader {

        /* renamed from: a, reason: collision with root package name */
        private final byte[] f3775a;

        /* renamed from: b, reason: collision with root package name */
        private final ByteBuffer f3776b;

        /* renamed from: c, reason: collision with root package name */
        private final InputStream f3777c;

        /* renamed from: d, reason: collision with root package name */
        private long f3778d;

        private void d(int i2) {
            if (this.f3777c.read(this.f3775a, 0, i2) != i2) {
                throw new IOException("read failed");
            }
            this.f3778d += i2;
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public int a() {
            this.f3776b.position(0);
            d(4);
            return this.f3776b.getInt();
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public void b(int i2) {
            while (i2 > 0) {
                int skip = (int) this.f3777c.skip(i2);
                if (skip < 1) {
                    throw new IOException("Skip didn't move at least 1 byte forward");
                }
                i2 -= skip;
                this.f3778d += skip;
            }
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public long c() {
            this.f3776b.position(0);
            d(4);
            return MetadataListReader.c(this.f3776b.getInt());
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public long getPosition() {
            return this.f3778d;
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public int readUnsignedShort() {
            this.f3776b.position(0);
            d(2);
            return MetadataListReader.d(this.f3776b.getShort());
        }
    }

    private static class OffsetInfo {

        /* renamed from: a, reason: collision with root package name */
        private final long f3779a;

        /* renamed from: b, reason: collision with root package name */
        private final long f3780b;

        OffsetInfo(long j2, long j3) {
            this.f3779a = j2;
            this.f3780b = j3;
        }

        long a() {
            return this.f3779a;
        }
    }

    private interface OpenTypeReader {
        int a();

        void b(int i2);

        long c();

        long getPosition();

        int readUnsignedShort();
    }

    private static OffsetInfo a(OpenTypeReader openTypeReader) {
        long j2;
        openTypeReader.b(4);
        int readUnsignedShort = openTypeReader.readUnsignedShort();
        if (readUnsignedShort > 100) {
            throw new IOException("Cannot read metadata.");
        }
        openTypeReader.b(6);
        int i2 = 0;
        while (true) {
            if (i2 >= readUnsignedShort) {
                j2 = -1;
                break;
            }
            int a2 = openTypeReader.a();
            openTypeReader.b(4);
            j2 = openTypeReader.c();
            openTypeReader.b(4);
            if (1835365473 == a2) {
                break;
            }
            i2++;
        }
        if (j2 != -1) {
            openTypeReader.b((int) (j2 - openTypeReader.getPosition()));
            openTypeReader.b(12);
            long c2 = openTypeReader.c();
            for (int i3 = 0; i3 < c2; i3++) {
                int a3 = openTypeReader.a();
                long c3 = openTypeReader.c();
                long c4 = openTypeReader.c();
                if (1164798569 == a3 || 1701669481 == a3) {
                    return new OffsetInfo(c3 + j2, c4);
                }
            }
        }
        throw new IOException("Cannot read metadata.");
    }

    static MetadataList b(ByteBuffer byteBuffer) {
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.position((int) a(new ByteBufferReader(duplicate)).a());
        return MetadataList.i(duplicate);
    }

    static long c(int i2) {
        return i2 & 4294967295L;
    }

    static int d(short s2) {
        return s2 & 65535;
    }
}
