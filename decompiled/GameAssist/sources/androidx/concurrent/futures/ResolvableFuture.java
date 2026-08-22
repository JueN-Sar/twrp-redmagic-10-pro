package androidx.concurrent.futures;

import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public final class ResolvableFuture<V> extends AbstractResolvableFuture<V> {
    private ResolvableFuture() {
    }

    public static ResolvableFuture r() {
        return new ResolvableFuture();
    }

    @Override // androidx.concurrent.futures.AbstractResolvableFuture
    public boolean o(Object obj) {
        return super.o(obj);
    }

    @Override // androidx.concurrent.futures.AbstractResolvableFuture
    public boolean p(Throwable th) {
        return super.p(th);
    }
}
