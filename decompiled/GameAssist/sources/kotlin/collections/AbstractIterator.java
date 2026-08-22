package kotlin.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata
/* loaded from: classes2.dex */
public abstract class AbstractIterator<T> implements Iterator<T>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private State f18290c = State.NotReady;

    /* renamed from: h, reason: collision with root package name */
    private Object f18291h;

    @Metadata
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f18292a;

        static {
            int[] iArr = new int[State.values().length];
            try {
                iArr[State.Done.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[State.Ready.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            f18292a = iArr;
        }
    }

    private final boolean e() {
        this.f18290c = State.Failed;
        b();
        return this.f18290c == State.Ready;
    }

    protected abstract void b();

    protected final void c() {
        this.f18290c = State.Done;
    }

    protected final void d(Object obj) {
        this.f18291h = obj;
        this.f18290c = State.Ready;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        State state = this.f18290c;
        if (state == State.Failed) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int i2 = WhenMappings.f18292a[state.ordinal()];
        if (i2 == 1) {
            return false;
        }
        if (i2 != 2) {
            return e();
        }
        return true;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f18290c = State.NotReady;
        return this.f18291h;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
