package kotlin.collections;

import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysJvmKt$asList$5 extends AbstractList<Float> implements RandomAccess {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ float[] f18318c;

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        return this.f18318c.length;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Float) {
            return d(((Number) obj).floatValue());
        }
        return false;
    }

    public boolean d(float f2) {
        for (float f3 : this.f18318c) {
            if (Float.floatToIntBits(f3) == Float.floatToIntBits(f2)) {
                return true;
            }
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public Float get(int i2) {
        return Float.valueOf(this.f18318c[i2]);
    }

    public int g(float f2) {
        float[] fArr = this.f18318c;
        int length = fArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (Float.floatToIntBits(fArr[i2]) == Float.floatToIntBits(f2)) {
                return i2;
            }
        }
        return -1;
    }

    public int h(float f2) {
        float[] fArr = this.f18318c;
        int length = fArr.length - 1;
        if (length < 0) {
            return -1;
        }
        while (true) {
            int i2 = length - 1;
            if (Float.floatToIntBits(fArr[length]) == Float.floatToIntBits(f2)) {
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
        if (obj instanceof Float) {
            return g(((Number) obj).floatValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.f18318c.length == 0;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Float) {
            return h(((Number) obj).floatValue());
        }
        return -1;
    }
}
