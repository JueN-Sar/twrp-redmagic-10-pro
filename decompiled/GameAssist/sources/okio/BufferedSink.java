package okio;

import java.nio.channels.WritableByteChannel;

/* loaded from: classes2.dex */
public interface BufferedSink extends Sink, WritableByteChannel {
    BufferedSink U(int i2);

    Buffer b();

    @Override // okio.Sink, java.io.Flushable
    void flush();

    BufferedSink r();

    BufferedSink v(String str);

    BufferedSink writeByte(int i2);

    BufferedSink y(String str, int i2, int i3);
}
