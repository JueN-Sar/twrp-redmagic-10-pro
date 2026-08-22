package androidx.lifecycle;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes.dex */
final class ViewTreeViewModelStoreOwner$findViewTreeViewModelStoreOwner$1 extends Lambda implements Function1<View, View> {
    public static final ViewTreeViewModelStoreOwner$findViewTreeViewModelStoreOwner$1 INSTANCE = new ViewTreeViewModelStoreOwner$findViewTreeViewModelStoreOwner$1();

    ViewTreeViewModelStoreOwner$findViewTreeViewModelStoreOwner$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final View c(View view) {
        Intrinsics.e(view, "view");
        Object parent = view.getParent();
        if (parent instanceof View) {
            return (View) parent;
        }
        return null;
    }
}
