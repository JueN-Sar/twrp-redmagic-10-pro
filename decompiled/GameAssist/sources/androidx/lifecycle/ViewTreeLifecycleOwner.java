package androidx.lifecycle;

import android.view.View;
import androidx.lifecycle.runtime.R;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

@Metadata
@JvmName
/* loaded from: classes.dex */
public final class ViewTreeLifecycleOwner {
    public static final void a(View view, LifecycleOwner lifecycleOwner) {
        Intrinsics.e(view, "<this>");
        view.setTag(R.id.view_tree_lifecycle_owner, lifecycleOwner);
    }
}
