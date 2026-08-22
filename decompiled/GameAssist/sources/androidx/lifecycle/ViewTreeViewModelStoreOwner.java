package androidx.lifecycle;

import android.view.View;
import androidx.lifecycle.viewmodel.R;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

@Metadata
@JvmName
/* loaded from: classes.dex */
public final class ViewTreeViewModelStoreOwner {
    public static final void a(View view, ViewModelStoreOwner viewModelStoreOwner) {
        Intrinsics.e(view, "<this>");
        view.setTag(R.id.view_tree_view_model_store_owner, viewModelStoreOwner);
    }
}
