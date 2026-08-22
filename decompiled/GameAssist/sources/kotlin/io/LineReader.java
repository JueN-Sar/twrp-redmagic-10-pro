package kotlin.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class LineReader {

    /* renamed from: a, reason: collision with root package name */
    public static final LineReader f18460a = new LineReader();

    /* renamed from: b, reason: collision with root package name */
    private static final byte[] f18461b;

    /* renamed from: c, reason: collision with root package name */
    private static final char[] f18462c;

    /* renamed from: d, reason: collision with root package name */
    private static final ByteBuffer f18463d;

    /* renamed from: e, reason: collision with root package name */
    private static final CharBuffer f18464e;

    /* renamed from: f, reason: collision with root package name */
    private static final StringBuilder f18465f;

    static {
        byte[] bArr = new byte[32];
        f18461b = bArr;
        char[] cArr = new char[32];
        f18462c = cArr;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        Intrinsics.d(wrap, "wrap(bytes)");
        f18463d = wrap;
        CharBuffer wrap2 = CharBuffer.wrap(cArr);
        Intrinsics.d(wrap2, "wrap(chars)");
        f18464e = wrap2;
        f18465f = new StringBuilder();
    }

    private LineReader() {
    }
}
