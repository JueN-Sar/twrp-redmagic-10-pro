package androidx.activity;

import android.content.res.Resources;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes.dex */
final class SystemBarStyle$Companion$light$1 extends Lambda implements Function1<Resources, Boolean> {
    public static final SystemBarStyle$Companion$light$1 INSTANCE = new SystemBarStyle$Companion$light$1();

    SystemBarStyle$Companion$light$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Boolean c(Resources resources) {
        Intrinsics.e(resources, "<anonymous parameter 0>");
        return Boolean.FALSE;
    }
}
