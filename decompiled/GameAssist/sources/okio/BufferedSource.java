package okio;

import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;

/* loaded from: classes2.dex */
public interface BufferedSource extends Source, ReadableByteChannel {
    short J();

    void O(long j2);

    long Q(byte b2);

    boolean V();

    Buffer b();

    int b0();

    InputStream g0();

    int i0(Options options);

    long m(ByteString byteString);

    BufferedSource peek();

    long q(ByteString byteString);

    byte readByte();

    short readShort();

    void skip(long j2);

    boolean z(long j2);
}
