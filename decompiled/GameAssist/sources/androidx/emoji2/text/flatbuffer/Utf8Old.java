package androidx.emoji2.text.flatbuffer;

import androidx.emoji2.text.flatbuffer.Utf8Old;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

/* loaded from: classes.dex */
public class Utf8Old extends Utf8 {

    /* renamed from: b, reason: collision with root package name */
    private static final ThreadLocal f3839b = ThreadLocal.withInitial(new Supplier() { // from class: androidx.emoji2.text.flatbuffer.a
        @Override // java.util.function.Supplier
        public final Object get() {
            Utf8Old.Cache c2;
            c2 = Utf8Old.c();
            return c2;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    static class Cache {

        /* renamed from: a, reason: collision with root package name */
        final CharsetEncoder f3840a;

        /* renamed from: b, reason: collision with root package name */
        final CharsetDecoder f3841b;

        /* renamed from: c, reason: collision with root package name */
        CharSequence f3842c = null;

        /* renamed from: d, reason: collision with root package name */
        ByteBuffer f3843d = null;

        Cache() {
            Charset charset = StandardCharsets.UTF_8;
            this.f3840a = charset.newEncoder();
            this.f3841b = charset.newDecoder();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Cache c() {
        return new Cache();
    }
}
