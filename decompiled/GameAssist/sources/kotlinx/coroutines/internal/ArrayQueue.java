package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;

@Metadata
/* loaded from: classes2.dex */
public class ArrayQueue<T> {

    /* renamed from: a, reason: collision with root package name */
    private Object[] f19335a = new Object[16];

    /* renamed from: b, reason: collision with root package name */
    private int f19336b;

    /* renamed from: c, reason: collision with root package name */
    private int f19337c;

    private final void b() {
        Object[] objArr = this.f19335a;
        int length = objArr.length;
        Object[] objArr2 = new Object[length << 1];
        ArraysKt___ArraysJvmKt.i(objArr, objArr2, 0, this.f19336b, 0, 10, null);
        Object[] objArr3 = this.f19335a;
        int length2 = objArr3.length;
        int i2 = this.f19336b;
        ArraysKt___ArraysJvmKt.i(objArr3, objArr2, length2 - i2, 0, i2, 4, null);
        this.f19335a = objArr2;
        this.f19336b = 0;
        this.f19337c = length;
    }

    public final void a(Object obj) {
        Object[] objArr = this.f19335a;
        int i2 = this.f19337c;
        objArr[i2] = obj;
        int length = (objArr.length - 1) & (i2 + 1);
        this.f19337c = length;
        if (length == this.f19336b) {
            b();
        }
    }

    public final boolean c() {
        return this.f19336b == this.f19337c;
    }

    public final Object d() {
        int i2 = this.f19336b;
        if (i2 == this.f19337c) {
            return null;
        }
        Object[] objArr = this.f19335a;
        Object obj = objArr[i2];
        objArr[i2] = null;
        this.f19336b = (i2 + 1) & (objArr.length - 1);
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("null cannot be cast to non-null type T of kotlinx.coroutines.internal.ArrayQueue");
    }
}
