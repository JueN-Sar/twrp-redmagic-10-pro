package kotlin.collections;

import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysJvmKt$asList$6 extends AbstractList<Double> implements RandomAccess {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ double[] f18319c;

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        return this.f18319c.length;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Double) {
            return d(((Number) obj).doubleValue());
        }
        return false;
    }

    public boolean d(double d2) {
        for (double d3 : this.f18319c) {
            if (Double.doubleToLongBits(d3) == Double.doubleToLongBits(d2)) {
                return true;
            }
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public Double get(int i2) {
        return Double.valueOf(this.f18319c[i2]);
    }

    public int g(double d2) {
        double[] dArr = this.f18319c;
        int length = dArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (Double.doubleToLongBits(dArr[i2]) == Double.doubleToLongBits(d2)) {
                return i2;
            }
        }
        return -1;
    }

    public int h(double d2) {
        double[] dArr = this.f18319c;
        int length = dArr.length - 1;
        if (length < 0) {
            return -1;
        }
        while (true) {
            int i2 = length - 1;
            if (Double.doubleToLongBits(dArr[length]) == Double.doubleToLongBits(d2)) {
                return length;
            }
            if (i2 < 0) {
                return -1;
            }
            length = i2;
        }
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Double) {
            return g(((Number) obj).doubleValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.f18319c.length == 0;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Double) {
            return h(((Number) obj).doubleValue());
        }
        return -1;
    }
}
