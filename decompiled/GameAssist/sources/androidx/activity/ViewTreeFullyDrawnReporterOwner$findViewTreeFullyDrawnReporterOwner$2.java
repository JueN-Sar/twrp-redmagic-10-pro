package androidx.activity;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes.dex */
final class ViewTreeFullyDrawnReporterOwner$findViewTreeFullyDrawnReporterOwner$2 extends Lambda implements Function1<View, FullyDrawnReporterOwner> {
    public static final ViewTreeFullyDrawnReporterOwner$findViewTreeFullyDrawnReporterOwner$2 INSTANCE = new ViewTreeFullyDrawnReporterOwner$findViewTreeFullyDrawnReporterOwner$2();

    ViewTreeFullyDrawnReporterOwner$findViewTreeFullyDrawnReporterOwner$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final FullyDrawnReporterOwner c(View it) {
        Intrinsics.e(it, "it");
        Object tag = it.getTag(R.id.report_drawn);
        if (tag instanceof FullyDrawnReporterOwner) {
            return (FullyDrawnReporterOwner) tag;
        }
        return null;
    }
}
