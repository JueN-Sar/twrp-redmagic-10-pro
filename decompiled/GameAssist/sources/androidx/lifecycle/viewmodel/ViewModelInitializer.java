package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class ViewModelInitializer<T extends ViewModel> {

    /* renamed from: a, reason: collision with root package name */
    private final Class f4424a;

    /* renamed from: b, reason: collision with root package name */
    private final Function1 f4425b;

    public ViewModelInitializer(Class clazz, Function1 initializer) {
        Intrinsics.e(clazz, "clazz");
        Intrinsics.e(initializer, "initializer");
        this.f4424a = clazz;
        this.f4425b = initializer;
    }

    public final Class a() {
        return this.f4424a;
    }

    public final Function1 b() {
        return this.f4425b;
    }
}
