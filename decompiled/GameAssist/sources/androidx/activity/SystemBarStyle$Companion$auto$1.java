package androidx.activity;

import android.content.res.Resources;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes.dex */
final class SystemBarStyle$Companion$auto$1 extends Lambda implements Function1<Resources, Boolean> {
    public static final SystemBarStyle$Companion$auto$1 INSTANCE = new SystemBarStyle$Companion$auto$1();

    SystemBarStyle$Companion$auto$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Boolean c(Resources resources) {
        Intrinsics.e(resources, "resources");
        return Boolean.valueOf((resources.getConfiguration().uiMode & 48) == 32);
    }
}
