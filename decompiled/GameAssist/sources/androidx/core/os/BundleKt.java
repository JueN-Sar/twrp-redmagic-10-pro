package androidx.core.os;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class BundleKt {
    public static final Bundle a(Pair... pairArr) {
        Bundle bundle = new Bundle(pairArr.length);
        for (Pair pair : pairArr) {
            String str = (String) pair.a();
            Object b2 = pair.b();
            if (b2 == null) {
                bundle.putString(str, null);
            } else if (b2 instanceof Boolean) {
                bundle.putBoolean(str, ((Boolean) b2).booleanValue());
            } else if (b2 instanceof Byte) {
                bundle.putByte(str, ((Number) b2).byteValue());
            } else if (b2 instanceof Character) {
                bundle.putChar(str, ((Character) b2).charValue());
            } else if (b2 instanceof Double) {
                bundle.putDouble(str, ((Number) b2).doubleValue());
            } else if (b2 instanceof Float) {
                bundle.putFloat(str, ((Number) b2).floatValue());
            } else if (b2 instanceof Integer) {
                bundle.putInt(str, ((Number) b2).intValue());
            } else if (b2 instanceof Long) {
                bundle.putLong(str, ((Number) b2).longValue());
            } else if (b2 instanceof Short) {
                bundle.putShort(str, ((Number) b2).shortValue());
            } else if (b2 instanceof Bundle) {
                bundle.putBundle(str, (Bundle) b2);
            } else if (b2 instanceof CharSequence) {
                bundle.putCharSequence(str, (CharSequence) b2);
            } else if (b2 instanceof Parcelable) {
                bundle.putParcelable(str, (Parcelable) b2);
            } else if (b2 instanceof boolean[]) {
                bundle.putBooleanArray(str, (boolean[]) b2);
            } else if (b2 instanceof byte[]) {
                bundle.putByteArray(str, (byte[]) b2);
            } else if (b2 instanceof char[]) {
                bundle.putCharArray(str, (char[]) b2);
            } else if (b2 instanceof double[]) {
                bundle.putDoubleArray(str, (double[]) b2);
            } else if (b2 instanceof float[]) {
                bundle.putFloatArray(str, (float[]) b2);
            } else if (b2 instanceof int[]) {
                bundle.putIntArray(str, (int[]) b2);
            } else if (b2 instanceof long[]) {
                bundle.putLongArray(str, (long[]) b2);
            } else if (b2 instanceof short[]) {
                bundle.putShortArray(str, (short[]) b2);
            } else if (b2 instanceof Object[]) {
                Class<?> componentType = b2.getClass().getComponentType();
                Intrinsics.b(componentType);
                if (Parcelable.class.isAssignableFrom(componentType)) {
                    Intrinsics.c(b2, "null cannot be cast to non-null type kotlin.Array<android.os.Parcelable>");
                    bundle.putParcelableArray(str, (Parcelable[]) b2);
                } else if (String.class.isAssignableFrom(componentType)) {
                    Intrinsics.c(b2, "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
                    bundle.putStringArray(str, (String[]) b2);
                } else if (CharSequence.class.isAssignableFrom(componentType)) {
                    Intrinsics.c(b2, "null cannot be cast to non-null type kotlin.Array<kotlin.CharSequence>");
                    bundle.putCharSequenceArray(str, (CharSequence[]) b2);
                } else {
                    if (!Serializable.class.isAssignableFrom(componentType)) {
                        throw new IllegalArgumentException("Illegal value array type " + componentType.getCanonicalName() + " for key \"" + str + '\"');
                    }
                    bundle.putSerializable(str, (Serializable) b2);
                }
            } else if (b2 instanceof Serializable) {
                bundle.putSerializable(str, (Serializable) b2);
            } else if (b2 instanceof IBinder) {
                bundle.putBinder(str, (IBinder) b2);
            } else if (b2 instanceof Size) {
                BundleApi21ImplKt.a(bundle, str, (Size) b2);
            } else {
                if (!(b2 instanceof SizeF)) {
                    throw new IllegalArgumentException("Illegal value type " + b2.getClass().getCanonicalName() + " for key \"" + str + '\"');
                }
                BundleApi21ImplKt.b(bundle, str, (SizeF) b2);
            }
        }
        return bundle;
    }
}
