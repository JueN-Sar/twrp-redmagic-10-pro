package androidx.activity;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

@Metadata
@JvmName
/* loaded from: classes.dex */
public final class ViewTreeOnBackPressedDispatcherOwner {
    public static final void a(View view, OnBackPressedDispatcherOwner onBackPressedDispatcherOwner) {
        Intrinsics.e(view, "<this>");
        Intrinsics.e(onBackPressedDispatcherOwner, "onBackPressedDispatcherOwner");
        view.setTag(R.id.view_tree_on_back_pressed_dispatcher_owner, onBackPressedDispatcherOwner);
    }
}
