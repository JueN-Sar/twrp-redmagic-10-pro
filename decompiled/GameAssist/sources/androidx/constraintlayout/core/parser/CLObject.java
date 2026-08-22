package androidx.constraintlayout.core.parser;

import java.util.Iterator;

/* loaded from: classes.dex */
public class CLObject extends CLContainer implements Iterable<CLKey> {

    private static class CLObjectIterator implements Iterator<CLKey> {

        /* renamed from: c, reason: collision with root package name */
        CLObject f1900c;

        /* renamed from: h, reason: collision with root package name */
        int f1901h = 0;

        CLObjectIterator(CLObject cLObject) {
            this.f1900c = cLObject;
        }

        @Override // java.util.Iterator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public CLKey next() {
            CLKey cLKey = (CLKey) this.f1900c.f1892l.get(this.f1901h);
            this.f1901h++;
            return cLKey;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f1901h < this.f1900c.size();
        }
    }

    @Override // java.lang.Iterable
    public Iterator<CLKey> iterator() {
        return new CLObjectIterator(this);
    }

    @Override // androidx.constraintlayout.core.parser.CLContainer
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public CLObject clone() {
        return (CLObject) super.clone();
    }
}
