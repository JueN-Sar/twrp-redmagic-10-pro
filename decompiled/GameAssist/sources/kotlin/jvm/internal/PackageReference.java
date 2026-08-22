package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public final class PackageReference implements ClassBasedDeclarationContainer {

    /* renamed from: c, reason: collision with root package name */
    private final Class f18563c;

    /* renamed from: h, reason: collision with root package name */
    private final String f18564h;

    public PackageReference(Class jClass, String moduleName) {
        Intrinsics.e(jClass, "jClass");
        Intrinsics.e(moduleName, "moduleName");
        this.f18563c = jClass;
        this.f18564h = moduleName;
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public Class c() {
        return this.f18563c;
    }

    public boolean equals(Object obj) {
        return (obj instanceof PackageReference) && Intrinsics.a(c(), ((PackageReference) obj).c());
    }

    public int hashCode() {
        return c().hashCode();
    }

    public String toString() {
        return c().toString() + " (Kotlin reflection is not available)";
    }
}
