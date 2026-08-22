package androidx.savedstate;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

@Metadata
@JvmName
/* loaded from: classes.dex */
public final class ViewTreeSavedStateRegistryOwner {
    public static final void a(View view, SavedStateRegistryOwner savedStateRegistryOwner) {
        Intrinsics.e(view, "<this>");
        view.setTag(R.id.view_tree_saved_state_registry_owner, savedStateRegistryOwner);
    }
}
