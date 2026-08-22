package androidx.activity;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

@Metadata
@JvmName
/* loaded from: classes.dex */
public final class ViewTreeFullyDrawnReporterOwner {
    public static final void a(View view, FullyDrawnReporterOwner fullyDrawnReporterOwner) {
        Intrinsics.e(view, "<this>");
        Intrinsics.e(fullyDrawnReporterOwner, "fullyDrawnReporterOwner");
        view.setTag(R.id.report_drawn, fullyDrawnReporterOwner);
    }
}
