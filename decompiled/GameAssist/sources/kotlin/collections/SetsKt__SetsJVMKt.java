package kotlin.collections;

import java.util.Collections;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.builders.SetBuilder;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
/* loaded from: classes2.dex */
public class SetsKt__SetsJVMKt {
    public static Set a(Set builder) {
        Intrinsics.e(builder, "builder");
        return ((SetBuilder) builder).d();
    }

    public static Set b(int i2) {
        return new SetBuilder(i2);
    }

    public static Set c(Object obj) {
        Set singleton = Collections.singleton(obj);
        Intrinsics.d(singleton, "singleton(element)");
        return singleton;
    }
}
