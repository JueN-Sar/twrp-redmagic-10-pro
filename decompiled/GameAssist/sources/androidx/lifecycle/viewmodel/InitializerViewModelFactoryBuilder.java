package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModelProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;

@Metadata
@ViewModelFactoryDsl
@SourceDebugExtension
/* loaded from: classes.dex */
public final class InitializerViewModelFactoryBuilder {

    /* renamed from: a, reason: collision with root package name */
    private final List f4423a = new ArrayList();

    public final void a(KClass clazz, Function1 initializer) {
        Intrinsics.e(clazz, "clazz");
        Intrinsics.e(initializer, "initializer");
        this.f4423a.add(new ViewModelInitializer(JvmClassMappingKt.a(clazz), initializer));
    }

    public final ViewModelProvider.Factory b() {
        ViewModelInitializer[] viewModelInitializerArr = (ViewModelInitializer[]) this.f4423a.toArray(new ViewModelInitializer[0]);
        return new InitializerViewModelFactory((ViewModelInitializer[]) Arrays.copyOf(viewModelInitializerArr, viewModelInitializerArr.length));
    }
}
