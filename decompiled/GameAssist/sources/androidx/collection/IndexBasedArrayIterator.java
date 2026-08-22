package androidx.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableIterator;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public abstract class IndexBasedArrayIterator<T> implements Iterator<T>, KMutableIterator {

    /* renamed from: c, reason: collision with root package name */
    private int f1221c;

    /* renamed from: h, reason: collision with root package name */
    private int f1222h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f1223i;

    public IndexBasedArrayIterator(int i2) {
        this.f1221c = i2;
    }

    protected abstract Object b(int i2);

    protected abstract void c(int i2);

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f1222h < this.f1221c;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object b2 = b(this.f1222h);
        this.f1222h++;
        this.f1223i = true;
        return b2;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (!this.f1223i) {
            throw new IllegalStateException("Call next() before removing an element.".toString());
        }
        int i2 = this.f1222h - 1;
        this.f1222h = i2;
        c(i2);
        this.f1221c--;
        this.f1223i = false;
    }
}
