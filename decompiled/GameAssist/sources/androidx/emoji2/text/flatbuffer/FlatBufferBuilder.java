package androidx.emoji2.text.flatbuffer;

import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class FlatBufferBuilder {

    static class ByteBufferBackedInputStream extends InputStream {

        /* renamed from: c, reason: collision with root package name */
        ByteBuffer f3808c;

        @Override // java.io.InputStream
        public int read() {
            try {
                return this.f3808c.get() & 255;
            } catch (BufferUnderflowException unused) {
                return -1;
            }
        }
    }

    public static abstract class ByteBufferFactory {
    }

    public static final class HeapByteBufferFactory extends ByteBufferFactory {

        /* renamed from: a, reason: collision with root package name */
        public static final HeapByteBufferFactory f3809a = new HeapByteBufferFactory();
    }
}
