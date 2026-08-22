package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CharIterator;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class CharProgressionIterator extends CharIterator {

    /* renamed from: c, reason: collision with root package name */
    private final int f18595c;

    /* renamed from: h, reason: collision with root package name */
    private final int f18596h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f18597i;

    /* renamed from: j, reason: collision with root package name */
    private int f18598j;

    public CharProgressionIterator(char c2, char c3, int i2) {
        this.f18595c = i2;
        this.f18596h = c3;
        boolean z = false;
        if (i2 <= 0 ? Intrinsics.f(c2, c3) >= 0 : Intrinsics.f(c2, c3) <= 0) {
            z = true;
        }
        this.f18597i = z;
        this.f18598j = z ? c2 : c3;
    }

    @Override // kotlin.collections.CharIterator
    public char b() {
        int i2 = this.f18598j;
        if (i2 != this.f18596h) {
            this.f18598j = this.f18595c + i2;
        } else {
            if (!this.f18597i) {
                throw new NoSuchElementException();
            }
            this.f18597i = false;
        }
        return (char) i2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18597i;
    }
}
