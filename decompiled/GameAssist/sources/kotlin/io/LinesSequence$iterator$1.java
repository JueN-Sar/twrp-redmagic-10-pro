package kotlin.io;

import java.io.BufferedReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes2.dex */
public final class LinesSequence$iterator$1 implements Iterator<String>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private String f18467c;

    /* renamed from: h, reason: collision with root package name */
    private boolean f18468h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ LinesSequence f18469i;

    LinesSequence$iterator$1(LinesSequence linesSequence) {
        this.f18469i = linesSequence;
    }

    @Override // java.util.Iterator
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        String str = this.f18467c;
        this.f18467c = null;
        Intrinsics.b(str);
        return str;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        BufferedReader bufferedReader;
        if (this.f18467c == null && !this.f18468h) {
            bufferedReader = this.f18469i.f18466a;
            String readLine = bufferedReader.readLine();
            this.f18467c = readLine;
            if (readLine == null) {
                this.f18468h = true;
            }
        }
        return this.f18467c != null;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
