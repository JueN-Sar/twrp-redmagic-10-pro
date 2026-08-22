package kotlin.io;

import java.io.BufferedInputStream;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.ByteIterator;

@Metadata
/* loaded from: classes2.dex */
public final class ByteStreamsKt$iterator$1 extends ByteIterator {

    /* renamed from: c, reason: collision with root package name */
    private int f18433c;

    /* renamed from: h, reason: collision with root package name */
    private boolean f18434h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f18435i;

    /* renamed from: j, reason: collision with root package name */
    final /* synthetic */ BufferedInputStream f18436j;

    private final void c() {
        if (this.f18434h || this.f18435i) {
            return;
        }
        int read = this.f18436j.read();
        this.f18433c = read;
        this.f18434h = true;
        this.f18435i = read == -1;
    }

    @Override // kotlin.collections.ByteIterator
    public byte b() {
        c();
        if (this.f18435i) {
            throw new NoSuchElementException("Input stream is over.");
        }
        byte b2 = (byte) this.f18433c;
        this.f18434h = false;
        return b2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        c();
        return !this.f18435i;
    }
}
