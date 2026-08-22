package androidx.lifecycle;

import android.app.Application;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata
@RestrictTo
/* loaded from: classes.dex */
public final class SavedStateViewModelFactoryKt {

    /* renamed from: a, reason: collision with root package name */
    private static final List f4382a;

    /* renamed from: b, reason: collision with root package name */
    private static final List f4383b;

    static {
        List j2;
        j2 = CollectionsKt__CollectionsKt.j(Application.class, SavedStateHandle.class);
        f4382a = j2;
        f4383b = CollectionsKt__CollectionsJVMKt.e(SavedStateHandle.class);
    }

    public static final Constructor c(Class modelClass, List signature) {
        List W;
        Intrinsics.e(modelClass, "modelClass");
        Intrinsics.e(signature, "signature");
        Constructor<?>[] constructors = modelClass.getConstructors();
        Intrinsics.d(constructors, "modelClass.constructors");
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Intrinsics.d(parameterTypes, "constructor.parameterTypes");
            W = ArraysKt___ArraysKt.W(parameterTypes);
            if (Intrinsics.a(signature, W)) {
                Intrinsics.c(constructor, "null cannot be cast to non-null type java.lang.reflect.Constructor<T of androidx.lifecycle.SavedStateViewModelFactoryKt.findMatchingConstructor>");
                return constructor;
            }
            if (signature.size() == W.size() && W.containsAll(signature)) {
                throw new UnsupportedOperationException("Class " + modelClass.getSimpleName() + " must have parameters in the proper order: " + signature);
            }
        }
        return null;
    }

    public static final ViewModel d(Class modelClass, Constructor constructor, Object... params) {
        Intrinsics.e(modelClass, "modelClass");
        Intrinsics.e(constructor, "constructor");
        Intrinsics.e(params, "params");
        try {
            return (ViewModel) constructor.newInstance(Arrays.copyOf(params, params.length));
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Failed to access " + modelClass, e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException("A " + modelClass + " cannot be instantiated.", e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException("An exception happened in constructor of " + modelClass, e4.getCause());
        }
    }
}
