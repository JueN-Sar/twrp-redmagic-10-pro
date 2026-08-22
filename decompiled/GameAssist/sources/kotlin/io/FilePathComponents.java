package kotlin.io;

import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class FilePathComponents {

    /* renamed from: a, reason: collision with root package name */
    private final File f18437a;

    /* renamed from: b, reason: collision with root package name */
    private final List f18438b;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FilePathComponents)) {
            return false;
        }
        FilePathComponents filePathComponents = (FilePathComponents) obj;
        return Intrinsics.a(this.f18437a, filePathComponents.f18437a) && Intrinsics.a(this.f18438b, filePathComponents.f18438b);
    }

    public int hashCode() {
        return (this.f18437a.hashCode() * 31) + this.f18438b.hashCode();
    }

    public String toString() {
        return "FilePathComponents(root=" + this.f18437a + ", segments=" + this.f18438b + ')';
    }
}
